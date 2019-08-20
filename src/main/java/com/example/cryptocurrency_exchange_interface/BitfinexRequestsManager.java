package com.example.cryptocurrency_exchange_interface;

import com.example.cryptocurrency_exchange_interface.converter.BitfinexObjectsConverter;
import com.example.cryptocurrency_exchange_interface.model.SymbolsDetails;
import com.example.cryptocurrency_exchange_interface.model.Trade;
import com.example.cryptocurrency_exchange_interface.request_service.RestRequests;
import com.example.cryptocurrency_exchange_interface.request_service.WebSocketClientEndpoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@Component
public class BitfinexRequestsManager {

    private RestRequests restRequests;
    private BitfinexObjectsConverter objectsConverter;

    @Autowired
    public BitfinexRequestsManager(RestRequests restRequests, BitfinexObjectsConverter objectsConverter) {
        this.restRequests = restRequests;
        this.objectsConverter = objectsConverter;
    }

    public void showAllSymbolsDetails() {
        List<Object> rowDataSymbolsDetails = restRequests.getObjectsList("https://api.bitfinex.com/v1/symbols_details", HttpMethod.GET);
        List<SymbolsDetails> symbolsDetails = objectsConverter.convertObjectsToSymbolDetailsList(rowDataSymbolsDetails);
        System.out.println("List of symbol details:\n pair, price_precision, maximum_order_size, minimum_order_size");
        symbolsDetails.forEach(symbolDetails -> System.out.println(symbolDetails.printBasicData()));
    }

    public void subscribeAndShowTradesBtcusd(){

        WebSocketClientEndpoint clientEndPoint = null;
        try {
            clientEndPoint = new WebSocketClientEndpoint(new URI("wss://api.bitfinex.com/ws"));
            clientEndPoint.addMessageHandler(message -> {
                Trade trade = objectsConverter.convertJsonToTradeObject(message);
                if(trade != null) {
                    System.out.println("DateTime, Last price, Last amount");
                    System.out.println(trade.printBasicData());
                }
            });

            clientEndPoint.sendMessage("{\n" +
                    "  \"event\": \"subscribe\",\n" +
                    "  \"channel\": \"trades\",\n" +
                    "  \"pair\": \"BTCUSD\"\n" +
                    "}");

        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

    }



}
