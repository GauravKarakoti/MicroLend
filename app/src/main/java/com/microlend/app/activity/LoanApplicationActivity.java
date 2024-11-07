package com.microlend.app.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import com.microlend.app.R;
import com.microlend.app.model.LoanApplication2; // Make sure you have LoanApplication2 model
import com.microlend.app.viewmodel.LoanApplicationViewModel;

import java.util.List;

public class LoanApplicationActivity extends AppCompatActivity {

    private LoanApplicationViewModel loanApplicationViewModel;
    private EditText editTextLoanAmount;
    private ListView listViewLoanApplications;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("LoanApplicationActivity", "onCreate called");
        setContentView(R.layout.activity_loan_application); // Adjust layout name as needed

        loanApplicationViewModel = new LoanApplicationViewModel(getApplication());
        editTextLoanAmount = findViewById(R.id.editTextLoanAmount);
        listViewLoanApplications = findViewById(R.id.listViewLoanApplications);
        Button buttonAddLoan = findViewById(R.id.buttonAddLoanApplication);
        Button buttonGoBackLoan = findViewById(R.id.buttonGoBack);

        loanApplicationViewModel.getAllLoanApplications().observe(this, new Observer<List<LoanApplication2>>() {
            @Override
            public void onChanged(List<LoanApplication2> loanApplications) {
                // Update ListView with loan applications
                // You may want to use an ArrayAdapter here to display the loan application list
            }
        });

        buttonAddLoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String loanAmount = editTextLoanAmount.getText().toString().trim();
                if (!loanAmount.isEmpty()) {
                    try {
                        LoanApplication2 loanApplication = new LoanApplication2(Double.parseDouble(loanAmount)); // Ensure amount is a double
                        loanApplicationViewModel.insert(loanApplication);
                        editTextLoanAmount.setText("");
                    } catch (NumberFormatException e) {
                        Toast.makeText(LoanApplicationActivity.this, "Invalid loan amount", Toast.LENGTH_SHORT).show();
                        Log.e("LoanApplicationActivity", "Invalid loan amount entered", e);
                    } catch (Exception e) {
                        Toast.makeText(LoanApplicationActivity.this, "Error occurred. Please try again.", Toast.LENGTH_SHORT).show();
                        Log.e("LoanApplicationActivity", "Error inserting loan application", e);
                    }
                } else {
                    Toast.makeText(LoanApplicationActivity.this, "Please enter a loan amount", Toast.LENGTH_SHORT).show();
                }
            }
        });

        buttonGoBackLoan.setOnClickListener(v -> finish());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("LoanApplicationActivity", "onDestroy called");
    }
}
