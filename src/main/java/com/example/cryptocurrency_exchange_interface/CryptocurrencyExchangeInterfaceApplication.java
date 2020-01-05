package com.example.cryptocurrency_exchange_interface;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * <h1> Bitfinex Cryptocurrency Exchange Interface </h1>
 * <p>Simple interface that download data from bitfinex server via its API:
 * get symbols details and check and update recent trade event for BTCUSD pair.</p>
 *
 * @version 1.1.0
 * @autor Jacek Stańczak
 * @since 2019.08.21
 */

@SpringBootApplication
public class CryptocurrencyExchangeInterfaceApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(CryptocurrencyExchangeInterfaceApplication.class, args);

        MainController mainController = ctx.getBean(MainController.class);
        mainController.runApp();
        ctx.close();

    }

}
