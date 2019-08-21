package com.example.cryptocurrency_exchange_interface;


import com.example.cryptocurrency_exchange_interface.service.InformationPrinter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;
/**
 * Application main controller. Controller shows available option, takes user input and run selected option.
 * */
@Component
public class ApllicationController {

    private Scanner sc;
    private BitfinexRequestsManager bitfinexRequestsManager;

    @Autowired
    public ApllicationController(BitfinexRequestsManager bitfinexRequestsManager) {
        sc = new Scanner(System.in);
        this.bitfinexRequestsManager = bitfinexRequestsManager;
    }

    /**
     * Method run selected function in BitfinexRequestsManager.
     * @param option number of option representing particular function
     * @throws URISyntaxException
     * @throws IOException
     */
    private void runOption(int option) throws URISyntaxException, IOException {
        switch (option) {
            case 0:
                bitfinexRequestsManager.showAllSymbolsDetails();
                break;
            case 1:
                bitfinexRequestsManager.subscribeAndShowTradesBtcusd();
                break;
            case 2:
                bitfinexRequestsManager.unsubscribeTradesBtcusd();
            default:
                break;
        }
    }

    /**
     * Method run main application loop: print user option, get user input and run function.
     */
    public void runApp() {
        System.out.println("APP START");
        int mainController = 0;

        do {
            InformationPrinter.printUserOptions();
            try {
                mainController = sc.nextInt();

                if (mainController < 0 || mainController > 3) {
                    throw new InputMismatchException();
                }

                runOption(mainController);
            } catch (InputMismatchException a) {
                sc.nextLine();
                mainController = 0;
                System.out.println("Incorrect option type. Please input number value from option list.");
            } catch (URISyntaxException e) {
                System.out.println("Incorrect URL value. Please contact with admin.");
            } catch (IOException e) {
                e.printStackTrace();
            }

        } while (mainController != 3);
        System.out.println("Finish app");
    }

}
