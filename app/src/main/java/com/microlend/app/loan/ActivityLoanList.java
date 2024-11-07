package com.microlend.app.loan;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.microlend.app.MainActivity;
import com.microlend.app.R;

import java.util.ArrayList;

public class ActivityLoanList extends AppCompatActivity {
    private RecyclerView recyclerView;
    private LoanAdapter loanAdapter;
    private Button buttonGoBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loan_list);

        recyclerView = findViewById(R.id.recyclerViewLoans);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        buttonGoBack = findViewById(R.id.buttonGoBack);

        ArrayList<LoanApplication> loanApplications = (ArrayList<LoanApplication>) getIntent().getSerializableExtra("loanApplications");
        loanAdapter = new LoanAdapter(loanApplications);
        recyclerView.setAdapter(loanAdapter);
        buttonGoBack.setOnClickListener(v -> goBackToDashboard());
    }
    private void goBackToDashboard() {
        // This method will finish the current activity and return to the MainActivity (Dashboard)
        Intent intent = new Intent(ActivityLoanList.this, ActivityLoanApplication.class);
        startActivity(intent);
        finish(); // Close this activity
    }
}
