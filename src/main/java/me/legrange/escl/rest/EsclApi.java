package me.legrange.escl.rest;

import com.google.gson.GsonBuilder;
import me.legrange.escl.JobInfo;
import me.legrange.escl.Platen;
import me.legrange.escl.ScanRegion;
import me.legrange.escl.ScanSettings;
import me.legrange.escl.ScannerCapabilities;
import me.legrange.escl.ScannerStatus;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static java.lang.String.format;

public class EsclApi {

    private final EsclService escl;

    private static void print(Object object) {
        System.out.println(new GsonBuilder().setPrettyPrinting().create().toJson(object));
    }

    public static void main(String[] args) throws EsclException, InterruptedException {
//        EsclApi api = new EsclApi("http://192.168.1.30/eSCL/");
        EsclApi api = new EsclApi("http://192.168.1.198/eSCL/");
        var status = api.getScannerStatus();
        if (!status.getState().equals("Idle")) {
            var zombies = status.getJobs().stream()
                    .filter(job -> !job.getState().equals("Canceled"))
                    .collect(Collectors.toList());
            for (var zombie : zombies) {
                System.out.printf("Cancelling job %s\n", zombie.getUuid());
                api.cancelScanJob(zombie);
            }
        }
        // print(api.getScannerCapabilities());
//      System.out.println(api.getScannerStatus());
        var caps = api.getScannerCapabilities();
//        print(caps);
        scan(api, caps);
        print(api.getScannerStatus());
        Thread.sleep(60, TimeUnit.SECONDS.ordinal());
    }

    private static void scan(EsclApi api, ScannerCapabilities caps) throws EsclException {
        var settings = new ScanSettings();
        var platen = caps.getPlaten();

        settings.setInputSource("Platen");
        settings.setVersion("2.6");
        settings.setIntent("Document");
        settings.setScanRegions(List.of(new ScanRegion(
                platen.getPlatenInputCaps().getMaxWidth(),
                platen.getPlatenInputCaps().getMaxHeight(),
                0, 0)));
        settings.setColorMode("Grayscale8");
        settings.setInputSource("Platen");
        api.createScanJob(settings);

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
        try {
            var res = escl.createScanJob(settings).execute();
            if (!res.isSuccessful()) {
                ResponseBody err = res.errorBody();
                if (err == null) {
                    throw new EsclException(format("Unexpected error %d", res.code()));
                }
                throw new EsclException(format("eSCL Error (%s)", err.string()));
            }
            HttpClient http = HttpClient.newBuilder()
                    .version(HttpClient.Version.HTTP_1_1)
                    .build();
            String location = res.headers().get("Location");
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(location +"/NextDocument"))
                    .timeout(Duration.ofMinutes(2))
                    .header("TE", "chunked")
                    .GET()
                    .build();
            http.send(request, HttpResponse.BodyHandlers.ofFile(Path.of("/Users/gideon/scan.pdf")));
            System.out.println();
        } catch (IOException | InterruptedException ex) {
            throw new EsclException(format("IO error calling eSCL (%s)", ex.getMessage()), ex);
        }
    }

    public void cancelScanJob(JobInfo job) throws EsclException {
        call(escl.cancelScanJob(job.getUuid()));
    }

    private String extractJobId(String header) throws EsclException {
        int idx = header.lastIndexOf("/");
        if (idx < 0) {
            throw new EsclException(format("Cannot find job ID in '%s'", header));
        }
        return header.substring(idx + 1);
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
