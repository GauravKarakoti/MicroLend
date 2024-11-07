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
import com.microlend.app.model.Expense;
import com.microlend.app.viewmodel.ExpenseViewModel;

import java.util.List;

public class ActivityExpenseTracker extends AppCompatActivity {
    private ExpenseViewModel expenseViewModel;
    private EditText editTextAmount;
    private ListView listViewExpenses;
    private Button buttonAdd , buttonGoBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense_tracker);

        editTextAmount = findViewById(R.id.editTextExpenseAmount);
        listViewExpenses = findViewById(R.id.listViewExpenses);
        buttonAdd = findViewById(R.id.buttonAddExpense);
        buttonGoBack = findViewById(R.id.buttonGoBack); // Ensure this is initialized

        expenseViewModel = new ViewModelProvider(this).get(ExpenseViewModel.class);
        buttonGoBack.setOnClickListener(v -> goBackToDashboard());


        expenseViewModel.getAllExpenses().observe(this, new Observer<List<Expense>>() {
            @Override
            public void onChanged(List<Expense> expenses) {
                // Update the ListView with the new expense list
                ExpenseAdapter adapter = new ExpenseAdapter(ActivityExpenseTracker.this, expenses);
                listViewExpenses.setAdapter(adapter);
            }
        });

        buttonAdd.setOnClickListener(v -> {
            String amountStr = editTextAmount.getText().toString();
            if (!amountStr.isEmpty()) {
                double amount = Double.parseDouble(amountStr);
                Expense expense = new Expense(amount);
                expenseViewModel.insert(expense);
                editTextAmount.setText("");
                Toast.makeText(ActivityExpenseTracker.this, "Expense added", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(ActivityExpenseTracker.this, "Please enter an amount", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void goBackToDashboard() {
        Intent intent = new Intent(ActivityExpenseTracker.this, MainActivity.class);
        startActivity(intent);
        finish(); // Close this activity
    }
}
