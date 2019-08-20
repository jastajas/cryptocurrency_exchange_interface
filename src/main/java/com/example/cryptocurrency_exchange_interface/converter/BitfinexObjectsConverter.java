package com.example.cryptocurrency_exchange_interface.converter;

import com.example.cryptocurrency_exchange_interface.model.SymbolsDetails;
import com.example.cryptocurrency_exchange_interface.model.Trade;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class BitfinexObjectsConverter {

    private ObjectMapper mapper;

    @Autowired
    public BitfinexObjectsConverter(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    public Trade convertJsonToTradeObject(String json) throws IOException {

        Object[] objects = mapper.readValue(json, Object[].class);

        if (objects.length != 6) return null;

        return Trade.builder()
                .channel_id((Integer) objects[0])
                .messageType((String) objects[1])
                .seq((String) objects[2])
                .timestamp((Integer) objects[3])
                .price((Float) objects[4])
                .amount((Float) objects[6])
                .build();
    }

    public List<SymbolsDetails> convertObjectsToSymbolDetailsList(List<Object> objects){

        if(null != objects && objects.size() > 0){
            return mapper.convertValue(objects, new TypeReference<List<SymbolsDetails>>() { });
        }

        return null;
    }


}
