package com.example.cryptocurrency_exchange_interface.service;

import com.example.cryptocurrency_exchange_interface.UserOption;
import com.example.cryptocurrency_exchange_interface.model.SymbolsDetails;
import com.example.cryptocurrency_exchange_interface.model.Trade;

import java.util.Arrays;
import java.util.List;

public class InformationPrinter {

   public static void printNewTrade(Trade trade){
       System.out.println("...................................................................");
       System.out.println("DateTime, Last price, Last amount");
       System.out.println(trade.printBasicData());
       System.out.println("...................................................................");
   }

   public static void printUserOptions(){
       Arrays
               .stream(UserOption.values())
               .forEach(userOption -> System.out.println(userOption.ordinal() + " - " + userOption));
       System.out.println("Select option no:");
   }

   public static void prinSymbolsDetails(List<SymbolsDetails> symbolsDetails){
       System.out.println("List of symbol details:\n pair, price_precision, maximum_order_size, minimum_order_size");
       symbolsDetails.forEach(symbolDetails -> System.out.println(symbolDetails.printBasicData()));
   }



}
