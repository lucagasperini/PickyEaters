package com.pickyeaters.logic.view.cli;

import com.pickyeaters.logic.controller.application.InitController;
import com.pickyeaters.logic.controller.exception.DatabaseControllerException;
import com.pickyeaters.logic.controller.exception.SettingsControllerException;
import com.pickyeaters.logic.view.bean.SettingsBean;

import java.util.Map;
import java.util.Scanner;

public class InitView extends VirtualViewCLI {
    private final InitController controller = new InitController();

    protected InitView() {
        super("");
    }

    private void askConfig() {
        Scanner userInput = new Scanner(System.in);
        SettingsBean settings = new SettingsBean();

        System.out.print("Database Host: ");
        settings.setDatabaseHost(userInput.nextLine());
        System.out.print("Database Port: ");
        settings.setDatabasePort(userInput.nextLine());
        System.out.print("Database Name: ");
        settings.setDatabaseName(userInput.nextLine());
        System.out.print("Database User: ");
        settings.setDatabaseUser(userInput.nextLine());
        System.out.print("Database Password: ");
        settings.setDatabasePassword(userInput.nextLine());

        try {
            controller.loadFromInput(settings);
        } catch (SettingsControllerException | DatabaseControllerException ex) {
            System.out.println("ERROR: " + ex.getMessage());
            askConfig();
        }
    }


    @Override
    public void show(Map<String, String> arg) {
        try {
            controller.loadFromFile();
        } catch (SettingsControllerException | DatabaseControllerException ex) {
            // If you cannot load settings/database, ask user input
            askConfig();
        }
    }

}
