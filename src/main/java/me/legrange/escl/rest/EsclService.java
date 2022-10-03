package me.legrange.escl.rest;

import me.legrange.escl.ScanSettings;
import me.legrange.escl.ScannerCapabilities;
import me.legrange.escl.ScannerStatus;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface EsclService {

    @GET("ScannerCapabilities")
    Call<ScannerCapabilities> getScannerCapabilities();

    @GET("ScannerStatus")
    Call<ScannerStatus> getScannerStatus();

    @POST("ScanJobs")
    Call<Void> createScanJob(@Body ScanSettings settings);
}
