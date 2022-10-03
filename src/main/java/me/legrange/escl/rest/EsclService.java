package me.legrange.escl.rest;

import me.legrange.escl.ScanSettings;
import me.legrange.escl.ScannerCapabilities;
import me.legrange.escl.ScannerStatus;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Streaming;

import java.io.File;
import java.io.InputStream;

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
    Call<InputStream> getDocument(@Path("jobId") String jobId);

    @DELETE("ScanJobs/{jobId}")
    Call<Void> cancelScanJob(@Path("jobId") String jobId);
}
