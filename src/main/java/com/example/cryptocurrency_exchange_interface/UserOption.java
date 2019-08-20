package com.example.cryptocurrency_exchange_interface;

public enum UserOption {

    PRINT_SYMBOL_DETAILS("Show all symbols details"),
    RUN_TRADES_MONITORING("Run BTCUSD pair trades monitoring"),
    FINISH("Finish programm");

    private String optionDescription;

    UserOption(String optionDescription) {
        this.optionDescription = optionDescription;
    }

    @Override
    public String toString() {
        return optionDescription;
    }

}
