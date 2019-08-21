package com.example.cryptocurrency_exchange_interface.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * Representation of symbol_details JSON object get from server BITFINEX as a response for symbol details information request.
 * */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class SymbolsDetails {

    private String pair;
    private int price_precision;
    private double initial_margin;
    private double minimum_margin;
    private double maximum_order_size;
    private double minimum_order_size;
    private String expiration;
    private boolean margin;

    public String printBasicData(){
        return
                pair + ",     " + price_precision +",    " + maximum_order_size + ",    " + minimum_order_size;
    }

}
