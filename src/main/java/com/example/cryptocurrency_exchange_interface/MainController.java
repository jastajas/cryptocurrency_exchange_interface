package com.example.cryptocurrency_exchange_interface;


import com.example.cryptocurrency_exchange_interface.exceptions.IncorrectActivity;
import com.example.cryptocurrency_exchange_interface.repository.TradeRepository;
import com.example.cryptocurrency_exchange_interface.service.ExceptionLogger;
import com.example.cryptocurrency_exchange_interface.service.InformationPrinter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Application main controller. Controller shows available options, takes user input, run selected option and handle thrown exceptions.
 */
@Component
public class MainController {

    private Scanner sc;
    private BitfinexRequestsManager bitfinexRequestsManager;
    private TradeRepository tradeRepository;
    private ExceptionLogger exceptionLogger;

    @Autowired
    public MainController(BitfinexRequestsManager bitfinexRequestsManager, TradeRepository tradeRepository, ExceptionLogger exceptionLogger) {
        sc = new Scanner(System.in);
        this.bitfinexRequestsManager = bitfinexRequestsManager;
        this.tradeRepository = tradeRepository;
        this.exceptionLogger = exceptionLogger;
    }

    /**
     * Method run selected function in BitfinexRequestsManager.
     * @param option enum value of selected option by user, representing particular function
     * @throws URISyntaxException
     * @throws IncorrectActivity
     */
    private void runOption(UserOption option) throws URISyntaxException, IncorrectActivity {
        InformationPrinter.printConfirmationOptionSelection(option);
        switch (option) {
            case PRINT_SYMBOL_DETAILS:
                bitfinexRequestsManager.showAllSymbolsDetails();
                break;
            case RUN_TRADES_MONITORING:
                bitfinexRequestsManager.subscribeTrades("BTCUSD");
                break;
            case PRINT_TRADES:
                InformationPrinter.printAllTrades(tradeRepository.getSubscribedTrades());
                break;
            case FINISH_TRADES_MONITORING:
                bitfinexRequestsManager.unsubscribeTrades("BTCUSD");
            default:
                break;
        }
    }

    /**
     * Method run main application loop: print user option, get user input and run function.
     */
    public void runApp() {

        InformationPrinter.printStartMessage();

        int userSelection = 0;

        do {
            InformationPrinter.printUserOptions();
            try {
                userSelection = sc.nextInt();

                runOption(UserOption.values()[userSelection]);
            } catch (InputMismatchException e) {
                sc.nextLine();
                userSelection = 0;
                String message = "Incorrect option type. Please input number value from option list.";
                exceptionLogger.logException(e, message);
                InformationPrinter.printExceptionMessage(message);
            } catch (URISyntaxException e) {
                String message = "Incorrect URL value. Please contact with admin.";
                exceptionLogger.logException(e, message);
                InformationPrinter.printExceptionMessage(message);
            } catch (IndexOutOfBoundsException e) {
                String message = "Incorrect option number. Please select option number from option list";
                exceptionLogger.logException(e, message);
                InformationPrinter.printExceptionMessage(message);
            } catch (IncorrectActivity e){
                exceptionLogger.logException(e);
                InformationPrinter.printExceptionMessage(e);
            }

        } while (userSelection != 4);

    }

}
