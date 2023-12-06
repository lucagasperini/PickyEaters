package com.pickyeaters.app.view.cli;

import com.pickyeaters.app.controller.SessionController;
import com.pickyeaters.app.controller.SystemController;
import com.pickyeaters.app.model.Session;

import java.util.Scanner;

public class MainCLI {
    public static void start() {
        load();
        welcome();
        login();
        requestLoop();
    }

    public static void quit() {
        System.out.println("Goodbye!");
        isRunning = false;
    }

    private static boolean isRunning = false;
    private static Session session = new Session();
    private static void load() {
        isRunning = true;
        SystemController.load();
    }
    private static void login() {
        Scanner userInput = new Scanner(System.in);
        do {
            System.out.print("Username: ");
            String username = userInput.nextLine();
            System.out.print("Password: ");
            String password = userInput.nextLine();

            session = SessionController.login(username, password);
        } while(!session.isValid());
    }
    private static void requestLoop() {
        Scanner userInput = new Scanner(System.in);
        while(isRunning) {
            System.out.print("> ");
            try {
                requestParser(userInput.nextLine());
            } catch (UnsupportedOperationException ex) {
                System.out.println("ERROR: " + ex.getMessage());
            }
        }
    }

    private static void requestParser(String request) {
        String[] tmp = request.split(" ");
        switch (tmp[0].toLowerCase()) {
            case "help":
            case "h":
                HelpView.show(tmp);
                break;
            case "quit":
            case "q":
                quit();
                break;
            default:
                throw new UnsupportedOperationException("Cannot execute " + tmp[0].toLowerCase());
        }
    }

    private static void welcome() {
        System.out.println("Welcome to PickyEaters");
    }
}
