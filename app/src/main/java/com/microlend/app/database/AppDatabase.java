package com.microlend.app.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.microlend.app.dao.BudgetDao;
import com.microlend.app.dao.ExpenseDao;
import com.microlend.app.dao.LoanApplicationDao;
import com.microlend.app.model.Budget;
import com.microlend.app.model.Expense;
import com.microlend.app.model.LoanApplication2;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Budget.class, Expense.class, LoanApplication2.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    public abstract BudgetDao budgetDao();
    public abstract ExpenseDao expenseDao();
    public abstract LoanApplicationDao loanApplicationDao();

    private static volatile AppDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    // Correctly implemented getInstance method
    public static AppDatabase getInstance(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    AppDatabase.class, "microlend_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
