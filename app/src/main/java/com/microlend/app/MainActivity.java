package com.microlend.app;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import com.microlend.app.chatbot.ChatbotViewModel;
import com.microlend.app.model.YourDataModel;
import com.microlend.app.ApiService;
import com.microlend.app.R;
import com.microlend.app.network.RetrofitClient;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.microlend.app.expense.ActivityBudgetTracker;
import com.microlend.app.expense.ActivityExpenseTracker; // Create this activity for expense tracking
import com.microlend.app.loan.ActivityLoanApplication; // Create this activity for loan application
import com.microlend.app.advisor.ActivityFinancialAdvisory; // Create this activity for financial advice
import com.microlend.app.R;

public class MainActivity extends AppCompatActivity {
    private Button buttonBudgetTracker, buttonExpenseTracker, buttonLoanApplication, buttonFinancialAdvice;
    private ApiService apiService;
    private ChatbotViewModel chatbotViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("MainActivity", "MainActivity started");

        buttonBudgetTracker = findViewById(R.id.buttonBudgetTracker);
        buttonExpenseTracker = findViewById(R.id.buttonExpenseTracker);
        buttonLoanApplication = findViewById(R.id.buttonLoanApplication);
        buttonFinancialAdvice = findViewById(R.id.buttonFinancialAdvice);

        buttonBudgetTracker.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, ActivityBudgetTracker.class)));
        buttonExpenseTracker.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, ActivityExpenseTracker.class)));
        buttonLoanApplication.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, ActivityLoanApplication.class)));
        buttonFinancialAdvice.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, ActivityFinancialAdvisory.class)));
        apiService = RetrofitClient.getClient("https://api.vultr.com/v2/").create(ApiService.class);

        // Make an API call
        fetchData();

        chatbotViewModel = new ViewModelProvider(this).get(ChatbotViewModel.class);

        chatbotViewModel.getChatbotResponse().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String response) {
                // Display the financial advice in your UI
                Toast.makeText(MainActivity.this, response, Toast.LENGTH_LONG).show();
            }
        });

        // Sending a financial query
        chatbotViewModel.sendFinancialQuery("What should I invest in?");
    }


    private void fetchData() {
        Call<List<YourDataModel>> call = apiService.getData();
        call.enqueue(new Callback<List<YourDataModel>>() {
            @Override
            public void onResponse(Call<List<YourDataModel>> call, Response<List<YourDataModel>> response) {
                if (response.isSuccessful()) {
                    List<YourDataModel> data = response.body();
                    // Handle the response data (e.g., update UI)
                } else {
                    Toast.makeText(MainActivity.this, "Error: " + response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<YourDataModel>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Failure: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
