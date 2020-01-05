package com.example.cryptocurrency_exchange_interface.exceptions;

public enum IncorrectActivityCode {

    SUBSCRIPTION_DUPLICATE("Subscription is running. New subscription is not added."),
    LACK_OF_ACTIVE_SUBSCRIPTIONS("No trades subscriptions available.");

    private String message;

    IncorrectActivityCode(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

}
