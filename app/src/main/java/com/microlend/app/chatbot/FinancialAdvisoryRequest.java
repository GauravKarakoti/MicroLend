package com.microlend.app.chatbot;

public class FinancialAdvisoryRequest {
    private String query;

    public FinancialAdvisoryRequest(String query) {
        this.query = query;
    }

    public String getQuery() {
        return query;
    }
}