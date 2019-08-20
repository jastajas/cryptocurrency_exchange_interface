package com.example.cryptocurrency_exchange_interface.converter;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class TimeConverter {

    public static String convertTimestampToDateTime(long timestamp){
        Timestamp ts = new Timestamp(timestamp);
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        return formatter.format(ts);
    }
}
