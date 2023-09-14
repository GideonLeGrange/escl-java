package me.legrange.scanner.rest;

import com.google.gson.GsonBuilder;
import me.legrange.log.Level;
import me.legrange.log.Log;
import me.legrange.scanner.escl.JobInfo;
import me.legrange.scanner.escl.ScanRegion;
import me.legrange.scanner.escl.ScanSettings;
import me.legrange.scanner.escl.ScannerCapabilities;
import me.legrange.scanner.escl.ScannerStatus;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import static java.lang.String.format;
import static me.legrange.log.Log.debug;

public class EsclApi {

    private final EsclService escl;

    private static void print(Object object) {
        System.out.println(new GsonBuilder().setPrettyPrinting().create().toJson(object));
    }

    public static void main(String[] args) throws EsclException, InterruptedException, IOException {
        Log.setDefaultLevel(Level.DEBUG);
//        EsclApi api = new EsclApi("http://192.168.1.30/eSCL/");
        EsclApi api = new EsclApi("http://192.168.1.148/eSCL/");
        var status = api.getScannerStatus();
        if (!status.getState().equals("Idle")) {
            var zombies = status.getJobs().stream()
                    .filter(job -> !job.getState().equals("Canceled")).toList();
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

    private static void scan(EsclApi api, ScannerCapabilities caps) throws EsclException, IOException {
        var settings = new ScanSettings();
        var platen = caps.getPlaten();

//        settings.setInputSource("Platen");
        settings.setVersion("2.6");
        settings.setIntent("Document");
        settings.setInputSource("Adf");
        settings.setScanRegions(List.of(
                new ScanRegion(
                        platen.getPlatenInputCaps().getMaxWidth(),
                        platen.getPlatenInputCaps().getMaxHeight(),
                        0, 0)));
        settings.setColorMode("Grayscale8");
        settings.setInputSource("Platen");
        var jobId = api.createScanJob(settings);
        var buf = api.downloadPage(jobId);
        var page = 1;
        try (var out = new FileOutputStream("/Users/gideon/scan.pdf")) {
            while (buf.isPresent()) {
                debug("Got page %d", page);
                out.write(buf.get());
                page++;
                buf = api.downloadPage(jobId);
            }
        }
    }

    private Optional<byte[]> downloadPage(String jobId) {
        HttpClient http = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_1_1)
                .build();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(jobId + "/NextDocument"))
                .timeout(Duration.ofMinutes(2))
                .header("TE", "chunked")
                .GET()
                .build();
        try {
            var res = http.send(request, HttpResponse.BodyHandlers.ofByteArray());
            if (res.statusCode() == 404) {
                return Optional.empty();
            }
            return Optional.of(res.body());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
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

    private static boolean waitingForScan(ScannerStatus status) {
        debug("waitingForScan(%s)", status);
        return switch (status.getState()) {
            case "Pending" -> true;
            default -> false;
        };
    }

    private static boolean doneScanning(ScannerStatus status) {
        debug("doneScanning(%s)", status);
        return switch (status.getState()) {
            case "Canceled", "Aborted", "Completed" -> true;
            default -> false;
        };
    }

    public ScannerCapabilities getScannerCapabilities() throws EsclException {
        return call(escl.getScannerCapabilities());
    }

    public ScannerStatus getScannerStatus() throws EsclException {
        return call(escl.getScannerStatus());
    }

    private String createScanJob(ScanSettings settings) throws EsclException {
        try {
            var res = escl.createScanJob(settings).execute();
            if (!res.isSuccessful()) {
                try (var err = res.errorBody()) {
                    if (err == null) {
                        throw new EsclException(format("Unexpected error %d", res.code()));
                    }
                    throw new EsclException(format("eSCL Error (%s)", err.string()));
                }
            }
            return res.headers().get("Location");
        } catch (IOException ex) {
            throw new EsclException(format("IO error calling eSCL (%s)", ex.getMessage()), ex);
        }
    }

    /**
     * Cancel a scan job
     *
     * @param job The job to cancel
     * @throws EsclException Thrown if there is an error.
     */
    public void cancelScanJob(JobInfo job) throws EsclException {
        call(escl.cancelScanJob(job.getUuid()));
    }


    /**
     * Call a Retrofit service method
     *
     * @param call The call
     * @param <T>  The type of return daya
     * @return The result
     * @throws EsclException Thrown if there is an error.
     */
    private <T> T call(Call<T> call) throws EsclException {
        try {
            Response<T> res = call.execute();
            if (!res.isSuccessful()) {
                try (var err = res.errorBody()) {
                    if (err == null) {
                        throw new EsclException(format("Unexpected error %d", res.code()));
                    }
                    throw new EsclException(format("eSCL Error (%s)", err.string()));
                }
            }
            return res.body();
        } catch (IOException ex) {
            throw new EsclException(format("IO error calling eSCL (%s)", ex.getMessage()), ex);
        }
    }


}
