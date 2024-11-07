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
import com.microlend.app.model.Budget;
import com.microlend.app.viewmodel.BudgetViewModel;

import java.util.List;

public class BudgetActivity extends AppCompatActivity {

    private BudgetViewModel budgetViewModel;
    private EditText editTextBudgetAmount;
    private ListView listViewBudgets;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("BudgetActivity", "onCreate called");
        setContentView(R.layout.activity_budget_tracker);

        budgetViewModel = new BudgetViewModel(getApplication());
        editTextBudgetAmount = findViewById(R.id.editTextBudgetAmount);
        listViewBudgets = findViewById(R.id.listViewBudgets);
        Button buttonAddBudget = findViewById(R.id.buttonAddBudget);
        Button buttonGoBackBudget = findViewById(R.id.buttonGoBack);

        budgetViewModel.getAllBudgets().observe(this, new Observer<List<Budget>>() {
            @Override
            public void onChanged(List<Budget> budgets) {
                // Update ListView with budgets
                // You may want to use an ArrayAdapter here to display the budget list
            }
        });

        buttonAddBudget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String budgetAmount = editTextBudgetAmount.getText().toString().trim();
                if (!budgetAmount.isEmpty()) {
                    try {
                        Budget budget = new Budget(Double.parseDouble(budgetAmount)); // Ensure amount is a double
                        try {
                            budgetViewModel.insert(budget); 
                        } catch (Exception e) {
                            Log.e("BudgetActivity", "Error inserting budget", e);
                            Toast.makeText(BudgetActivity.this, "Error occurred. Please try again.", Toast.LENGTH_SHORT).show();
                        }
                        editTextBudgetAmount.setText("");
                    } catch (NumberFormatException e) {
                        Toast.makeText(BudgetActivity.this, "Invalid budget amount", Toast.LENGTH_SHORT).show();
                        Log.e("BudgetActivity", "Invalid budget amount entered", e);
                    } catch (Exception e) {
                        Toast.makeText(BudgetActivity.this, "Error occurred. Please try again.", Toast.LENGTH_SHORT).show();
                        Log.e("BudgetActivity", "Error inserting budget", e);
                    }
                } else {
                    Toast.makeText(BudgetActivity.this, "Please enter a budget amount", Toast.LENGTH_SHORT).show();
                }
            }
        });

        buttonGoBackBudget.setOnClickListener(v -> finish());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("BudgetActivity", "onDestroy called");
    }
}
