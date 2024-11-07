package com.microlend.app.loan;

import java.io.Serializable;

public class LoanApplication implements Serializable {
    private String id; // Unique identifier
    private String amount;

    public LoanApplication(double amount) {
        this.amount = String.valueOf(amount);
    }

    public String getAmount() {
        return amount;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
