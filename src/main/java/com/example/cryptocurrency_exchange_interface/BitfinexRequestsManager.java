package com.example.cryptocurrency_exchange_interface;

import com.example.cryptocurrency_exchange_interface.service.BitfinexObjectsConverter;
import com.example.cryptocurrency_exchange_interface.model.SubscriptionResponse;
import com.example.cryptocurrency_exchange_interface.model.SymbolsDetails;
import com.example.cryptocurrency_exchange_interface.model.Trade;
import com.example.cryptocurrency_exchange_interface.repository.WebSocketResponseRepository;
import com.example.cryptocurrency_exchange_interface.request_service.RestRequests;
import com.example.cryptocurrency_exchange_interface.request_service.WebSocketClientEndpoint;
import com.example.cryptocurrency_exchange_interface.service.InformationPrinter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

/**
 * Object is main service for Bitfinex API reqeusts.
 */
@Component
public class BitfinexRequestsManager {

    private RestRequests restRequests;
    private BitfinexObjectsConverter objectsConverter;
    private WebSocketResponseRepository webSocketResponseRepository;
    private WebSocketClientEndpoint clientEndPoint;

    /**
     * @param restRequests is object which provide resources necessary for REST requests.
     * @param objectsConverter is converter object. It provides conversions from JSON to Java Beans.
     * @param webSocketResponseRepository object provides local database for received server subsription responses
     */
    @Autowired
    public BitfinexRequestsManager(RestRequests restRequests, BitfinexObjectsConverter objectsConverter, WebSocketResponseRepository webSocketResponseRepository) {
        this.restRequests = restRequests;
        this.objectsConverter = objectsConverter;
        this.webSocketResponseRepository = webSocketResponseRepository;
    }

    /**
     * Method send request for symbols details information, converts received data and print results in console
     */
    public void showAllSymbolsDetails() {
        List<Object> rowDataSymbolsDetails = restRequests.getObjectsList("https://api.bitfinex.com/v1/symbols_details", HttpMethod.GET);
        List<SymbolsDetails> symbolsDetails = objectsConverter.convertObjectsToSymbolDetailsList(rowDataSymbolsDetails);
        InformationPrinter.prinSymbolsDetails(symbolsDetails);
    }

    /**
     * Method send WebSocket subscription request for recent trade information, handle received messages, converts received data and print results in console
     * @throws URISyntaxException
     */
    public void subscribeAndShowTradesBtcusd() throws URISyntaxException {


        if (null == clientEndPoint)
            clientEndPoint = new WebSocketClientEndpoint(new URI("wss://api.bitfinex.com/ws"));

        clientEndPoint.addMessageHandler(message -> {
            SubscriptionResponse subscriptionResponse = objectsConverter.convertObjectToSubscriptionResponse(message);
            if (subscriptionResponse != null) webSocketResponseRepository.addSubscription(subscriptionResponse);

            Trade trade = objectsConverter.convertJsonToTradeObject(message);
            if (trade != null) {
                InformationPrinter.printNewTrade(trade);
                InformationPrinter.printUserOptions();
            }
        });

        clientEndPoint.sendMessage("{\n" +
                "  \"event\": \"subscribe\",\n" +
                "  \"channel\": \"trades\",\n" +
                "  \"pair\": \"BTCUSD\"\n" +
                "}");

    }

    /**
     * Method send WebSocket request to unscribe trade information.
     * @throws URISyntaxException
     * @throws IOException
     */
    public void unsubscribeTradesBtcusd() throws URISyntaxException, IOException {

        if (null == clientEndPoint)
            clientEndPoint = new WebSocketClientEndpoint(new URI("wss://api.bitfinex.com/ws"));

        webSocketResponseRepository.getRecentSubscription("trades").ifPresentOrElse(
                subscriptionResponse -> {
                    clientEndPoint.sendMessage("{\n" +
                            "  \"event\": \"unsubscribe\",\n" +
                            "  \"chanId\": " + subscriptionResponse.getChanId() + "\n" +
                            "}");
                },
                () -> System.out.println("No trades subscriptions available")
        );

    }

}
