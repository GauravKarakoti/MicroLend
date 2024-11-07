package com.microlend.app.expense;

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
import com.microlend.app.model.Budget;
import com.microlend.app.viewmodel.BudgetViewModel;

import java.util.List;

public class ActivityBudgetTracker extends AppCompatActivity {
    private BudgetViewModel budgetViewModel;
    private EditText editTextAmount;
    private ListView listViewBudgets;
    private Button buttonAdd , buttonGoBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_budget_tracker);

        editTextAmount = findViewById(R.id.editTextBudgetAmount);
        listViewBudgets = findViewById(R.id.listViewBudgets);
        buttonAdd = findViewById(R.id.buttonAddBudget);
        buttonGoBack = findViewById(R.id.buttonGoBack); // Ensure this is initialized
        buttonGoBack.setOnClickListener(v -> goBackToDashboard());

        budgetViewModel = new ViewModelProvider(this).get(BudgetViewModel.class);

        budgetViewModel.getAllBudgets().observe(this, new Observer<List<Budget>>() {
            @Override
            public void onChanged(List<Budget> budgets) {
                // Update the ListView with the new budget list
                BudgetAdapter adapter = new BudgetAdapter(ActivityBudgetTracker.this, budgets);
                listViewBudgets.setAdapter(adapter);
            }
        });

        buttonAdd.setOnClickListener(v -> {
            String amountStr = editTextAmount.getText().toString();
            if (!amountStr.isEmpty()) {
                double amount = Double.parseDouble(amountStr);
                Budget budget = new Budget(amount);
                budgetViewModel.insert(budget);
                editTextAmount.setText("");
                Toast.makeText(ActivityBudgetTracker.this, "Budget added", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(ActivityBudgetTracker.this, "Please enter an amount", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void goBackToDashboard() {
        Intent intent = new Intent(ActivityBudgetTracker.this, MainActivity.class);
        startActivity(intent);
        finish(); // Close this activity
    }
}
