package com.example.cryptocurrency_exchange_interface;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

@SpringBootApplication
public class CryptocurrencyExchangeInterfaceApplication {

    @Autowired
    private ApllicationController apllicationController;
    @Autowired
    private ConfigurableApplicationContext applicationContext;

    @PostConstruct
    public void runApplication() {
        apllicationController.runApp(applicationContext);
    }

    public static void main(String[] args) throws URISyntaxException, IOException, InterruptedException {
        ConfigurableApplicationContext ctx = SpringApplication.run(CryptocurrencyExchangeInterfaceApplication.class, args);

    }

}
