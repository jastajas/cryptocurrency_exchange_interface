package com.example.cryptocurrency_exchange_interface.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Optional;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Subscription {

    private SubscriptionResponse subscriptionResponse;
    private boolean isActive;

    public Optional<Subscription> optionalBoxing(){
        return Optional.of(this);
    }

}
