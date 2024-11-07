package com.microlend.app.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.microlend.app.model.Budget;
import com.microlend.app.model.Expense;

import java.util.List;

@Dao
public interface ExpenseDao {
    @Insert
    void insert(Expense expense);

    @Query("SELECT * FROM expense_table")
    LiveData<List<Expense>> getAllExpenses();
    @Delete
    void delete(Expense expense);
}
