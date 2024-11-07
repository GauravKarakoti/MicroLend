package com.microlend.app.chatbot;

public class ChatbotRequest {
    private String message;

    public ChatbotRequest(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}