package com.microlend.app.advisor;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.microlend.app.MainActivity;
import com.microlend.app.R;
import com.microlend.app.expense.ActivityBudgetTracker;

public class ActivityFinancialAdvisory extends AppCompatActivity {
    private EditText editTextIncome, editTextExpenses, editTextSavings;
    private Button buttonGetAdvice, buttonGoBack;
    private TextView textViewAdvice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_financial_advisory);

        editTextIncome = findViewById(R.id.editTextIncome);
        editTextExpenses = findViewById(R.id.editTextExpenses);
        editTextSavings = findViewById(R.id.editTextSavings);
        buttonGetAdvice = findViewById(R.id.buttonGetAdvice);
        buttonGoBack = findViewById(R.id.buttonGoBack);
        textViewAdvice = findViewById(R.id.textViewAdvice);

        buttonGetAdvice.setOnClickListener(v -> generateAdvice());
        buttonGoBack.setOnClickListener(v -> goBackToDashboard());
    }

    private void generateAdvice() {
        String income = editTextIncome.getText().toString();
        String expenses = editTextExpenses.getText().toString();
        String savings = editTextSavings.getText().toString();

        if (!income.isEmpty() && !expenses.isEmpty() && !savings.isEmpty()) {
            // Simple advice logic based on input (this can be expanded)
            double incomeValue = Double.parseDouble(income);
            double expensesValue = Double.parseDouble(expenses);
            double savingsValue = Double.parseDouble(savings);
            double netSavings = incomeValue - expensesValue;

            String advice;
            if (netSavings >= savingsValue) {
                advice = "You're on track! Keep saving.";
            } else {
                advice = "Consider reducing your expenses to meet your savings goal.";
            }

            textViewAdvice.setText(advice);
        } else {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
        }
    }
    private void goBackToDashboard() {
        // This method will finish the current activity and return to the MainActivity (Dashboard)
        Intent intent = new Intent(ActivityFinancialAdvisory.this, MainActivity.class);
        startActivity(intent);
        finish(); // Close this activity
    }
}
