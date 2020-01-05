package com.example.cryptocurrency_exchange_interface.service;

import com.example.cryptocurrency_exchange_interface.UserOption;
import com.example.cryptocurrency_exchange_interface.model.SymbolsDetails;
import com.example.cryptocurrency_exchange_interface.model.Trade;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class InformationPrinter {

    /**
     *
     * @param trade
     */
    public static void printSingleTrade(Trade trade) {
        System.out.println("...................................................................");
        System.out.println("DateTime, Last price, Last amount");
        System.out.println(trade.printBasicData());
        System.out.println("...................................................................");
    }

    /**
     *
     */
    public static void printUserOptions() {
        System.out.println("---------------------------------------------------------------------");
        Arrays
                .stream(UserOption.values())
                .forEach(userOption -> System.out.println(userOption.ordinal() + " - " + userOption));
        System.out.println("---------------------------------------------------------------------");
        System.out.println("Select option no:");
    }

    /**
     *
     * @param symbolsDetails
     */
    public static void printSymbolsDetails(List<SymbolsDetails> symbolsDetails) {
        System.out.println("List of symbol details:\n pair, price_precision, maximum_order_size, minimum_order_size");
        symbolsDetails.forEach(symbolDetails -> System.out.println(symbolDetails.printBasicData()));
    }

    /**
     *
     * @param trades
     */
    public static void printAllTrades(List<Trade> trades) {

        if (trades.isEmpty()) {
            System.out.println("No trades are registered");
        }else {
            trades.forEach(trade -> InformationPrinter.printSingleTrade(trade));
        }
    }

    /**
     *
     * @param selectedOption
     */
    public static void printConfirmationOptionSelection(UserOption selectedOption){
        System.out.println("Option " + selectedOption.name() + " is run.");
    }

    /**
     *
     * @param additionalMessage
     */
    public static void printExceptionMessage(String additionalMessage){
        printExceptionInformation(additionalMessage);
    }

    /**
     *
     * @param exception
     */
    public static void printExceptionMessage(Exception exception){
        printExceptionInformation(exception.getMessage());
    }

    /**
     *
     * @param message
     */
    private static void printExceptionInformation(String message){
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        System.out.println(message);
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
    }

    /**
     *
     */
    public static void printStartMessage(){
        System.out.println("APP START");
    }

}
