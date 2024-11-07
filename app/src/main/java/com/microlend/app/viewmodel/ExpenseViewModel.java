package com.microlend.app.viewmodel;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import com.microlend.app.dao.ExpenseDao;
import com.microlend.app.database.AppDatabase;
import com.microlend.app.model.Expense;
import java.util.List;

public class ExpenseViewModel extends AndroidViewModel {
    private final ExpenseDao expenseDao;
    private final LiveData<List<Expense>> allExpenses;

    public ExpenseViewModel(Application application) {
        super(application);
        AppDatabase db = AppDatabase.getInstance(application);
        expenseDao = db.expenseDao();
        allExpenses = expenseDao.getAllExpenses(); // Directly use without casting
    }

    public LiveData<List<Expense>> getAllExpenses() {
        return allExpenses;
    }

    public void insert(Expense expense) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            try {
                expenseDao.insert(expense);
            } catch (Exception e) {
                e.printStackTrace(); // Log the exception
            }
        });
    }

    public void delete(Expense expense) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            try {
                expenseDao.delete(expense);
            } catch (Exception e) {
                e.printStackTrace(); // Log the exception
            }
        });
    }
}
