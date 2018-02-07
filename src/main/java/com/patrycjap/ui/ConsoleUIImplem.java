package com.patrycjap.ui;

import com.patrycjap.api.ConsoleUI;

import java.util.Scanner;


public class ConsoleUIImplem implements ConsoleUI {
    @Override
    public void printMessage(String message) {
        System.out.println(message);
    }

    @Override
    public String getInput() {
        Scanner scanner = new Scanner(System.in);
        scanner.useDelimiter("[;\\r\\n]+");
        String text = scanner.nextLine();
        return text;
    }
}
