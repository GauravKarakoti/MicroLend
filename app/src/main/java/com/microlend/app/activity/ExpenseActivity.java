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
import com.microlend.app.model.Expense; // Make sure you have an Expense model
import com.microlend.app.viewmodel.ExpenseViewModel;

import java.util.List;

public class ExpenseActivity extends AppCompatActivity {

    private ExpenseViewModel expenseViewModel;
    private EditText editTextExpenseAmount;
    private ListView listViewExpenses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("ExpenseActivity", "onCreate called");
        setContentView(R.layout.activity_expense_tracker); // Adjust layout name as needed

        expenseViewModel = new ExpenseViewModel(getApplication());
        editTextExpenseAmount = findViewById(R.id.editTextExpenseAmount);
        listViewExpenses = findViewById(R.id.listViewExpenses);
        Button buttonAddExpense = findViewById(R.id.buttonAddExpense);
        Button buttonGoBackExpense = findViewById(R.id.buttonGoBack);

        expenseViewModel.getAllExpenses().observe(this, new Observer<List<Expense>>() {
            @Override
            public void onChanged(List<Expense> expenses) {
                // Update ListView with expenses
                // You may want to use an ArrayAdapter here to display the expense list
            }
        });

        buttonAddExpense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String expenseAmount = editTextExpenseAmount.getText().toString().trim();
                if (!expenseAmount.isEmpty()) {
                    try {
                        Expense expense = new Expense(Double.parseDouble(expenseAmount)); // Ensure amount is a double
                        expenseViewModel.insert(expense);
                        editTextExpenseAmount.setText("");
                    } catch (NumberFormatException e) {
                        Toast.makeText(ExpenseActivity.this, "Invalid expense amount", Toast.LENGTH_SHORT).show();
                        Log.e("ExpenseActivity", "Invalid expense amount entered", e);
                    } catch (Exception e) {
                        Toast.makeText(ExpenseActivity.this, "Error occurred. Please try again.", Toast.LENGTH_SHORT).show();
                        Log.e("ExpenseActivity", "Error inserting expense", e);
                    }
                } else {
                    Toast.makeText(ExpenseActivity.this, "Please enter an expense amount", Toast.LENGTH_SHORT).show();
                }
            }
        });

        buttonGoBackExpense.setOnClickListener(v -> finish());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("ExpenseActivity", "onDestroy called");
    }
}
