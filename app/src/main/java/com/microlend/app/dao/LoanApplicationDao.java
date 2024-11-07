package com.microlend.app.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.microlend.app.loan.LoanApplication;
import com.microlend.app.model.Budget;
import com.microlend.app.model.LoanApplication2; // Entity

import java.util.List;

@Dao
public interface LoanApplicationDao {
    @Insert
    void insert(LoanApplication2 loanApplication); // Corrected to use LoanApplication2

    @Query("SELECT * FROM loan_table") // Match the table name with LoanApplication2
    LiveData<List<LoanApplication2>> getAllLoanApplications();
    @Delete
    void delete(LoanApplication2 loanApplication2);
}
