package com.microlend.app.chatbot;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ChatbotService {
    @POST("/api/chatbot")
    Call<ChatbotResponse> sendMessage(@Body ChatbotRequest request);
}
