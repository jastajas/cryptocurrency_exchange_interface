package com.example.cryptocurrency_exchange_interface;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;


import javax.annotation.PostConstruct;

/**
 * <h1> Bitfinex Cryptocurrency Exchange Interface </h1>
 * <p>Simple interface that download data from bitfinex server via its API:
 * get symbols details and check and update recent trade event for btcusd pair.</p>
 *
 * @autor Jacek Stańczak
 * @version 1.0.0
 * @since 2019.08.21
 */
@SpringBootApplication
public class CryptocurrencyExchangeInterfaceApplication {

    @Autowired
    private ApllicationController apllicationController;

    @PostConstruct
    public void runApplication() {
         apllicationController.runApp();
    }

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(CryptocurrencyExchangeInterfaceApplication.class, args);


    }

}
