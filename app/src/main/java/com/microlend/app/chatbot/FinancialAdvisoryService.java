package com.microlend.app.chatbot;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

// Define the API endpoint for financial advice
public interface FinancialAdvisoryService {

    @POST("financial-advice")
    Call<FinancialAdvisoryResponse> getAdvice(@Body FinancialAdvisoryRequest request);
}
