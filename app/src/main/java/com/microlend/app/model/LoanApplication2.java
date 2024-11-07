package com.microlend.app.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "loan_table")
public class LoanApplication2 {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private double amount; // Change to double

    public LoanApplication2(double amount) { // Change constructor to accept double
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getAmount() {
        return amount; // Change return type to double
    }

    public void setAmount(double amount) { // Change parameter type to double
        this.amount = amount;
    }
}
