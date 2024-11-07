package com.microlend.app.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.microlend.app.model.Budget;

import java.util.List;

@Dao
public interface BudgetDao {
    @Insert
    void insert(Budget budget);

    @Query("SELECT * FROM budget_table") // Make sure to replace 'budget_table' with your actual table name
    LiveData<List<Budget>> getAllBudgets(); // Ensure this returns LiveData

    @Delete
    void delete(Budget budget);
}

