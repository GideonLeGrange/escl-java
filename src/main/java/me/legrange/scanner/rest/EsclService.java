package me.legrange.scanner.rest;

import me.legrange.scanner.escl.ScanSettings;
import me.legrange.scanner.escl.ScannerCapabilities;
import me.legrange.scanner.escl.ScannerStatus;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Streaming;

public interface EsclService {

    @GET("ScannerCapabilities")
    Call<ScannerCapabilities> getScannerCapabilities();

    @GET("ScannerStatus")
    Call<ScannerStatus> getScannerStatus();

    @POST("ScanJobs")
    Call<Void> createScanJob(@Body ScanSettings settings);

    @GET("ScanJobs/{jobId}/NextDocument")
    @Headers({"TE: chunked"})
    @Streaming
    Call<ResponseBody> getDocument(@Path("jobId") String jobId);

    @DELETE("ScanJobs/{jobId}")
    Call<Void> cancelScanJob(@Path("jobId") String jobId);
}
