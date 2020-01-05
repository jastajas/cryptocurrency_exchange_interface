package com.example.cryptocurrency_exchange_interface;

import com.example.cryptocurrency_exchange_interface.exceptions.IncorrectActivity;
import com.example.cryptocurrency_exchange_interface.exceptions.IncorrectActivityCode;
import com.example.cryptocurrency_exchange_interface.model.Subscription;
import com.example.cryptocurrency_exchange_interface.repository.TradeRepository;
import com.example.cryptocurrency_exchange_interface.service.BitfinexObjectsConverter;
import com.example.cryptocurrency_exchange_interface.model.SubscriptionResponse;
import com.example.cryptocurrency_exchange_interface.model.SymbolsDetails;
import com.example.cryptocurrency_exchange_interface.model.Trade;
import com.example.cryptocurrency_exchange_interface.repository.SubscriptionsRepository;
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
import java.util.Optional;

/**
 * Object is main service for Bitfinex API reqeusts.
 */

@Component
public class BitfinexRequestsManager {

    private RestRequests restRequests;
    private BitfinexObjectsConverter objectsConverter;
    private SubscriptionsRepository subscriptionsRepository;
    private TradeRepository tradeRepository;
    private WebSocketClientEndpoint clientEndPoint;

    /**
     * @param restRequests is object which provide resources necessary for REST requests.
     * @param objectsConverter is converter object. It provides tool for bitfinex object conversion to Java Beans.
     * @param tradeRepository object provides local database for received trade logs from bitfinex server.
     * @param subscriptionsRepository object provides local database for subsriptions.
     */
    @Autowired
    public BitfinexRequestsManager(RestRequests restRequests, BitfinexObjectsConverter objectsConverter, TradeRepository tradeRepository, SubscriptionsRepository subscriptionsRepository) {
        this.restRequests = restRequests;
        this.objectsConverter = objectsConverter;
        this.tradeRepository = tradeRepository;
        this.subscriptionsRepository = subscriptionsRepository;
    }

    /**
     * Method initiates WebSocket connection and it set message handler to catch subscription response and notifications about trades.
     *
     * @throws URISyntaxException
     */
    private void webSocketInit() throws URISyntaxException {
        if (null == clientEndPoint)
            clientEndPoint = new WebSocketClientEndpoint(new URI("wss://api.bitfinex.com/ws"));

        clientEndPoint.addMessageHandler(message -> {
            SubscriptionResponse subscriptionResponse = objectsConverter.convertObjectToSubscriptionResponse(message);
            if (subscriptionResponse != null) subscriptionsRepository.addSubscription(new Subscription(subscriptionResponse,true));

            Trade trade = objectsConverter.convertJsonToTradeObject(message);
            if (trade != null) {
                tradeRepository.addTrade(trade);
            }
        });
    }

    /**
     * Method sends request for symbols details information, converts received data and print results in console
     */
    public void showAllSymbolsDetails() {
        List<Object> rowDataSymbolsDetails = restRequests.getObjectsList("https://api.bitfinex.com/v1/symbols_details", HttpMethod.GET);
        List<SymbolsDetails> symbolsDetails = objectsConverter.convertObjectsToSymbolDetailsList(rowDataSymbolsDetails);
        InformationPrinter.printSymbolsDetails(symbolsDetails);
    }


    /**
     * Method sends WebSocket subscription request for recent trade information of particular currencies pair, handle received messages,
     * converts received data and print results in console
     * @param pairSymbol String representing pair of currencies symbols
     * @throws URISyntaxException
     * @throws IncorrectActivity
     */
    public void subscribeTrades(String pairSymbol) throws URISyntaxException, IncorrectActivity {

        webSocketInit();

        if(subscriptionsRepository.containActiveSubscriptionForPair(pairSymbol)) {
            throw new IncorrectActivity(IncorrectActivityCode.SUBSCRIPTION_DUPLICATE);
        } else {
            clientEndPoint.sendMessage("{\n" +
                    "  \"event\": \"subscribe\",\n" +
                    "  \"channel\": \"trades\",\n" +
                    "  \"pair\": \"" + pairSymbol + "\"\n" +
                    "}");
        }
    }

    /**
     * Method sends WebSocket request to resign subscription of trade for particular currencies pair.
     * @param pairSymbol String representing pair of currencies symbols
     * @throws URISyntaxException
     * @throws IncorrectActivity
     */
    public void unsubscribeTrades(String pairSymbol) throws URISyntaxException, IncorrectActivity {
        if (null == clientEndPoint)
            clientEndPoint = new WebSocketClientEndpoint(new URI("wss://api.bitfinex.com/ws"));


        subscriptionsRepository.getSubscriptionByChannel("trades")
                .orElseThrow(()-> new IncorrectActivity(IncorrectActivityCode.LACK_OF_ACTIVE_SUBSCRIPTIONS))
                .optionalBoxing()
                .ifPresent(subscription -> {
                    subscription.setActive(false);
                    clientEndPoint.sendMessage("{\n" +
                            "  \"event\": \"unsubscribe\",\n" +
                            "  \"chanId\": " + subscription.getSubscriptionResponse().getChanId() + "\n" +
                            "}");
                }
        );

    }

}
