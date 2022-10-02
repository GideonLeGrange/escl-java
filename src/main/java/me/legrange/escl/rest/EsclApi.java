package me.legrange.escl.rest;

import me.legrange.escl.ScanSettings;
import me.legrange.escl.ScannerCapabilities;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import static java.lang.String.format;

public class EsclApi {

    private final EsclService escl;

    public static void main(String[] args) throws EsclException {
 //       EsclApi api = new EsclApi("http://192.168.1.30/eSCL/");
        EsclApi api = new EsclApi("http://192.168.1.198/eSCL/");
//     System.out.println(api.getScannerCapabilities());
      System.out.println(api.getScannerStatus());
//        var settings = new ScanSettings();
//        settings.setInputSource("Platen");
//        api.createScanJob(settings);
    }

    public EsclApi(String baseUrl) {
        var client = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .callTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .build();
        var retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(client)
                .addConverterFactory(SimpleXmlConverterFactory.createNonStrict())
                .build();
        escl = retrofit.create(EsclService.class);
    }

    public ScannerCapabilities getScannerCapabilities() throws EsclException {
        return call(escl.getScannerCapabilities());
    }

    public ScannerStatus getScannerStatus() throws EsclException {
        return call(escl.getScannerStatus());
    }

    public void createScanJob(ScanSettings settings) throws EsclException {
        call(escl.createScanJob(settings));
    }

    private <T> T call(Call<T> call) throws EsclException {
        try {
            Response<T> res = call.execute();
            if (!res.isSuccessful()) {
                ResponseBody err = res.errorBody();
                if (err == null) {
                    throw new EsclException(format("Unexpected error %d", res.code()));
                }
                throw new EsclException(format("eSCL Error (%s)", err.string()));
            }
            return res.body();
        } catch (IOException ex) {
            throw new EsclException(format("IO error calling eSCL (%s)", ex.getMessage()), ex);
        }

    }


}
