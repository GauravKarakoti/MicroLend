package com.microlend.app.loan;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.microlend.app.MainActivity;
import com.microlend.app.R;
import com.microlend.app.model.LoanApplication2;
import com.microlend.app.viewmodel.LoanApplicationViewModel;

import java.util.List;

public class ActivityLoanApplication extends AppCompatActivity {
    private LoanApplicationViewModel loanApplicationViewModel;
    private EditText editTextAmount;
    private ListView listViewLoanApplications;
    private Button buttonAdd, buttonGoBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loan_application);

        editTextAmount = findViewById(R.id.editTextLoanAmount);
        listViewLoanApplications = findViewById(R.id.listViewLoanApplications);
        buttonAdd = findViewById(R.id.buttonAddLoanApplication);
        buttonGoBack = findViewById(R.id.buttonGoBack);
        buttonGoBack.setOnClickListener(v -> goBackToDashboard());

        loanApplicationViewModel = new ViewModelProvider(this).get(LoanApplicationViewModel.class);

        loanApplicationViewModel.getAllLoanApplications().observe(this, new Observer<List<LoanApplication2>>() {
            @Override
            public void onChanged(List<LoanApplication2> loanApplications) {
                LoanApplicationAdapter adapter = new LoanApplicationAdapter(ActivityLoanApplication.this, loanApplications);
                listViewLoanApplications.setAdapter(adapter);
            }
        });

        buttonAdd.setOnClickListener(v -> {
            String amountStr = editTextAmount.getText().toString();
            if (!amountStr.isEmpty()) {
                double amount = Double.parseDouble(amountStr); // Parse amount as double
                LoanApplication2 loanApplication = new LoanApplication2(amount); // Use double in constructor
                loanApplicationViewModel.insert(loanApplication);
                editTextAmount.setText("");
                Toast.makeText(ActivityLoanApplication.this, "Loan application added", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(ActivityLoanApplication.this, "Please enter an amount", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void goBackToDashboard() {
        Intent intent = new Intent(ActivityLoanApplication.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
