package com.example.cryptocurrency_exchange_interface.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * Object of response from Bitfinex server. Four fields represent response for subsription trade request.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubscriptionResponse {

    private String event;
    private String channel;
    private int chanId;
    private String pair;

}
