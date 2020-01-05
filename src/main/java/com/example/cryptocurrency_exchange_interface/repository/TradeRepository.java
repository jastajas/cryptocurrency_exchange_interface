package com.example.cryptocurrency_exchange_interface.repository;

import com.example.cryptocurrency_exchange_interface.model.Trade;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class TradeRepository {

    private List<Trade> subscribedTrades;

    public TradeRepository() {
        this.subscribedTrades = new ArrayList<>();
    }

    public List<Trade> getSubscribedTrades() {
        return subscribedTrades;
    }

    public void setSubscribedTrades(List<Trade> subscribedTrades) {
        this.subscribedTrades = subscribedTrades;
    }

    public void addTrade(Trade receivedTrade){
        Optional
                .ofNullable(receivedTrade)
                .ifPresentOrElse(trade -> this.subscribedTrades.add(trade),
                        ()-> System.out.println("No trade found."));
    }
}
