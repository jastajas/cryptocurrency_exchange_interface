package com.example.cryptocurrency_exchange_interface.service;

import com.example.cryptocurrency_exchange_interface.model.SubscriptionResponse;
import com.example.cryptocurrency_exchange_interface.model.SymbolsDetails;
import com.example.cryptocurrency_exchange_interface.model.Trade;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class BitfinexObjectsConverter {

    private ObjectMapper mapper;

    /**
     * @param mapper ObjectMapper class for mapping JSON object to Java Beans.
     */
    @Autowired
    public BitfinexObjectsConverter(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    /**
     * Method convert JSON (string) to Trade object.
     * @param json string representing JSON object
     * @return Trade object if json represent trade object otherwise return null
     * @throws IOException
     */
    public Trade convertJsonToTradeObject(String json) throws IOException {

        try {
            Object[] objects = mapper.readValue(json, Object[].class);

            if (objects.length != 6) return null;

            return Trade.builder()
                    .channel_id((Integer) objects[0])
                    .messageType((String) objects[1])
                    .seq((String) objects[2])
                    .timestamp((Integer) objects[3])
                    .price(objects[4] instanceof Integer ? Double.valueOf((Integer) objects[4]) : (Double) objects[4])
                    .amount(objects[5] instanceof Integer ? Double.valueOf((Integer) objects[5]) : (Double) objects[5])
                    .build();
        } catch (MismatchedInputException e) {
        }
        return null;
    }

    /**
     * Method convert List of Objects to List of SymbolsDetails.
     * @param objects represent List of general objects
     * @return List of SymbolsDetails Objects
     */

    public List<SymbolsDetails> convertObjectsToSymbolDetailsList(List<Object> objects) {

        if (null != objects && objects.size() > 0) {
            return mapper.convertValue(objects, new TypeReference<List<SymbolsDetails>>() {
            });
        }

        return null;
    }

    /**
     * Method convert Object to SymbolDetails.
     * @param object objects represent general objects
     * @return SymbolsDetails object
     */
    public SymbolsDetails convertObjectToSymbolDetails(Object object) {

        if (null != object) {
            return mapper.convertValue(object, new TypeReference<SymbolsDetails>() {
            });
        }

        return null;
    }

    /**
     * Method convert JSON (string) to  object SubscriptionResponse.
     * @param json Method convert JSON (string) to Trade object.
     * @return SubscriptionResponse object.
     */

    public SubscriptionResponse convertObjectToSubscriptionResponse(String json) {

        try {
            return  mapper.readValue(json, SubscriptionResponse.class);
        } catch (IOException e) {
        }

        return null;
    }

}
