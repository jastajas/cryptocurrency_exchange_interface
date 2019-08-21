package com.example.cryptocurrency_exchange_interface;

/**
 * Enum represents available application option.*/
public enum UserOption {

    PRINT_SYMBOL_DETAILS("Show all symbols details"),
    RUN_TRADES_MONITORING("Run BTCUSD pair trades monitoring"),
    FINISH_TRADES_MONITORING("Finish BTCUSD pair trades monitoring"),
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
