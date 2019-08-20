package com.example.cryptocurrency_exchange_interface;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import java.util.InputMismatchException;
import java.util.Scanner;

@Component
public class ApllicationController {

    private Scanner sc;
    private BitfinexRequestsManager bitfinexRequestsManager;

    @Autowired
    public ApllicationController(BitfinexRequestsManager bitfinexRequestsManager) {
        sc = new Scanner(System.in);
        this.bitfinexRequestsManager = bitfinexRequestsManager;
    }

    private void showOption() {
        UserOption[] userOptions = UserOption.values();
        for (int i = 0; i < userOptions.length; i++) {
            System.out.println(i + " - " + userOptions[i]);
        }
    }

    private void runOption(int option) {
        switch (option) {
            case 0:
                bitfinexRequestsManager.showAllSymbolsDetails();
                break;
            case 1:
                bitfinexRequestsManager.subscribeAndShowTradesBtcusd();
                break;
            default:
                break;
        }
    }


    public void runApp(ConfigurableApplicationContext context) {
        System.out.println("WITAJ !");
        int mainController = 0;

        do {
            showOption();
            System.out.println("Wybierz nr opcji: ");
            try {
                mainController = sc.nextInt();

                if (mainController < 0 || mainController > 2) {
                    throw new InputMismatchException();
                }

                runOption(mainController);
            } catch (InputMismatchException a) {
                sc.nextLine();
                mainController = 0;
                System.out.println("Wprowadzono niewłaściwy typ opcji.");
            }

        } while (mainController != 2);
        context.close();
        System.out.println("Dziękujemy za skorzystanie z aplikacji!");
        System.out.println("Do zobaczenia!");
    }
}
