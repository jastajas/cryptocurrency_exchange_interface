package com.example.cryptocurrency_exchange_interface.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Builder(toBuilder = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Trade {
    private int channel_id;
    private String messageType;
    private String seq;
    private int timestamp;
    private float price;
    private float amount;


    public String printBasicData() {
        return Instant.ofEpochSecond(timestamp).toString() + ", " + price + ", " + amount;
    }

}
