package com.microlend.app;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;
import java.util.Map;
import retrofit2.http.DELETE;
import retrofit2.http.Path;
import retrofit2.http.PUT;
import retrofit2.http.GET;
import com.microlend.app.model.YourDataModel;
import com.microlend.app.model.YourResponseType;
import com.microlend.app.model.RequestType;

import java.util.List;



public interface ApiService {
    @POST("add")
    Call<Void> addData(@Body Map<String, String> data);

    @DELETE("remove/{id}")
    Call<Void> removeData(@Path("id") int id);

    @PUT("update/{id}")
    Call<Void> updateData(@Path("id") int id, @Body Map<String, String> data);

    @GET("read")
    Call<List<YourDataModel>> readData();
    // Example endpoint to get instances
    @GET("instances")
    Call<YourResponseType> getInstances(@Header("Authorization") String authHeader);

    // Additional endpoints can be defined here
    @GET("plans")
    Call<YourResponseType> getPlans(@Header("Authorization") String authHeader);

    // Other endpoints as needed
    @GET("api/data")
    Call<List<YourDataModel>> getData();
    @POST("api/data")
    Call<YourDataModel> addData(@Body RequestType request);


}
