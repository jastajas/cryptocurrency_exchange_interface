package com.example.cryptocurrency_exchange_interface.exceptions;

public class IncorrectActivity extends Exception{

    public IncorrectActivity(IncorrectActivityCode exceptionCode) {
        super(exceptionCode.getMessage());
    }
}
