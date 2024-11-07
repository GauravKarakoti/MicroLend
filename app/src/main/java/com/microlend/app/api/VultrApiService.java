package com.microlend.app.api;



import com.microlend.app.model.YourResponseType; // Adjust import based on your model
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

import com.microlend.app.model.YourDataModel;
import java.util.List;



public interface VultrApiService {

    // Example endpoint to get instances
    @GET("instances")
    Call<YourResponseType> getInstances(@Header("Authorization") String authHeader);

    // Additional endpoints can be defined here
    @GET("plans")
    Call<YourResponseType> getPlans(@Header("Authorization") String authHeader);

    // Other endpoints as needed
    @GET("api/data")
    Call<List<YourDataModel>> getData();
}

