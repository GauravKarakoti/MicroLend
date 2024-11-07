package com.microlend.app.ui;

import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import com.microlend.app.model.YourDataModel;
import com.microlend.app.ApiService;
import com.microlend.app.R;
import com.microlend.app.network.RetrofitClient;


public class YourActivity extends AppCompatActivity {

    private ApiService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize the API service
        apiService = RetrofitClient.getClient("https://your-base-url.com/").create(ApiService.class);

        // Make a network call
        fetchData();
    }

    private void fetchData() {
        Call<List<YourDataModel>> call = apiService.getData();
        call.enqueue(new Callback<List<YourDataModel>>() {
            @Override
            public void onResponse(Call<List<YourDataModel>> call, Response<List<YourDataModel>> response) {
                if (response.isSuccessful()) {
                    List<YourDataModel> data = response.body();
                    // Handle the response
                } else {
                    Toast.makeText(YourActivity.this, "Error: " + response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<YourDataModel>> call, Throwable t) {
                Toast.makeText(YourActivity.this, "Failure: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
