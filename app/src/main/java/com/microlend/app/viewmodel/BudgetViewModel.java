package com.microlend.app.viewmodel;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import com.microlend.app.dao.BudgetDao;
import com.microlend.app.database.AppDatabase;
import com.microlend.app.model.Budget;
import java.util.List;

public class BudgetViewModel extends AndroidViewModel {
    private final BudgetDao budgetDao;
    private final LiveData<List<Budget>> allBudgets;

    public BudgetViewModel(Application application) {
        super(application);
        AppDatabase db = AppDatabase.getInstance(application);
        budgetDao = db.budgetDao();
        allBudgets = budgetDao.getAllBudgets(); // Directly use without casting
    }

    public LiveData<List<Budget>> getAllBudgets() {
        return allBudgets;
    }

    public void insert(Budget budget) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            try {
                budgetDao.insert(budget);
            } catch (Exception e) {
                e.printStackTrace(); // Log the exception
            }
        });
    }

    public void delete(Budget budget) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            try {
                budgetDao.delete(budget);
            } catch (Exception e) {
                e.printStackTrace(); // Log the exception
            }
        });
    }
}
