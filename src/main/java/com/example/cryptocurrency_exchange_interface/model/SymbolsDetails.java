package com.example.cryptocurrency_exchange_interface.model;

import org.springframework.stereotype.Component;

/**
 * @description Representation of symbol_details JSON object get from server BITFINEX as a response for symbol details information request.
 *              Subject of this is
 * */
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

    public SymbolsDetails() {
    }

    public SymbolsDetails(String pair, int price_precision, double initial_margin, double minimum_margin, double maximum_order_size, double minimum_order_size, String expiration, boolean margin) {
        this.pair = pair;
        this.price_precision = price_precision;
        this.initial_margin = initial_margin;
        this.minimum_margin = minimum_margin;
        this.maximum_order_size = maximum_order_size;
        this.minimum_order_size = minimum_order_size;
        this.expiration = expiration;
        this.margin = margin;
    }

    public String getPair() {
        return pair;
    }

    public void setPair(String pair) {
        this.pair = pair;
    }

    public int getPrice_precision() {
        return price_precision;
    }

    public void setPrice_precision(int price_precision) {
        this.price_precision = price_precision;
    }

    public double getInitial_margin() {
        return initial_margin;
    }

    public void setInitial_margin(double initial_margin) {
        this.initial_margin = initial_margin;
    }

    public double getMinimum_margin() {
        return minimum_margin;
    }

    public void setMinimum_margin(double minimum_margin) {
        this.minimum_margin = minimum_margin;
    }

    public double getMaximum_order_size() {
        return maximum_order_size;
    }

    public void setMaximum_order_size(double maximum_order_size) {
        this.maximum_order_size = maximum_order_size;
    }

    public double getMinimum_order_size() {
        return minimum_order_size;
    }

    public void setMinimum_order_size(double minimum_order_size) {
        this.minimum_order_size = minimum_order_size;
    }

    public String getExpiration() {
        return expiration;
    }

    public void setExpiration(String expiration) {
        this.expiration = expiration;
    }

    public boolean isMargin() {
        return margin;
    }

    public void setMargin(boolean margin) {
        this.margin = margin;
    }

    public String printBasicData(){
        return
                pair + ", " + price_precision +", " + maximum_order_size + ", " + minimum_order_size;
    }

    @Override
    public String toString() {
        return "Nowa{" +
                "pair='" + pair + '\'' +
                ", price_precision=" + price_precision +
                ", initial_margin=" + initial_margin +
                ", minimum_margin=" + minimum_margin +
                ", maximum_order_size=" + maximum_order_size +
                ", minimum_order_size=" + minimum_order_size +
                ", expiration='" + expiration + '\'' +
                ", margin=" + margin +
                '}';
    }
}
