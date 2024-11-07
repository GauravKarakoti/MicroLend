package com.microlend.app.chatbot;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.microlend.app.network.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ChatbotViewModel extends ViewModel {

    private MutableLiveData<String> chatbotResponse = new MutableLiveData<>();

    public LiveData<String> getChatbotResponse() {
        return chatbotResponse;
    }

    public void sendFinancialQuery(String query) {
        Retrofit retrofit = RetrofitClient.getClient("http://your-laravel-server.com");
        FinancialAdvisoryService advisoryService = retrofit.create(FinancialAdvisoryService.class);

        FinancialAdvisoryRequest request = new FinancialAdvisoryRequest(query);

        Call<FinancialAdvisoryResponse> call = advisoryService.getAdvice(request);
        call.enqueue(new Callback<FinancialAdvisoryResponse>() {
            @Override
            public void onResponse(Call<FinancialAdvisoryResponse> call, Response<FinancialAdvisoryResponse> response) {
                if (response.isSuccessful()) {
                    chatbotResponse.setValue(response.body().getResponse());
                } else {
                    chatbotResponse.setValue("Error: Could not retrieve advice.");
                }
            }

            @Override
            public void onFailure(Call<FinancialAdvisoryResponse> call, Throwable t) {
                chatbotResponse.setValue("Error: " + t.getMessage());
            }
        });
    }

    public void sendMessage(String message) {
        Retrofit retrofit = RetrofitClient.getClient("http://your-laravel-server.com");
        ChatbotService chatbotService = retrofit.create(ChatbotService.class);
        ChatbotRequest chatbotRequest = new ChatbotRequest(message);

        Call<ChatbotResponse> call = chatbotService.sendMessage(chatbotRequest);
        call.enqueue(new Callback<ChatbotResponse>() {
            @Override
            public void onResponse(Call<ChatbotResponse> call, Response<ChatbotResponse> response) {
                if (response.isSuccessful()) {
                    chatbotResponse.setValue(response.body().getResponse());
                }
            }

            @Override
            public void onFailure(Call<ChatbotResponse> call, Throwable t) {
                chatbotResponse.setValue("Error: " + t.getMessage());
            }
        });
    }

}
