package com.javarush.task.task26.task2613;

import com.javarush.task.task26.task2613.exception.InterruptOperationException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ResourceBundle;

public class ConsoleHelper {
    private static BufferedReader bis = new BufferedReader(new InputStreamReader(System.in));
    private static ResourceBundle res
            = ResourceBundle.getBundle(CashMachine.class.getPackage().getName()
            + ".resources.common_en");

    public static void writeMessage(String message) {
        System.out.println(message);
    }

    public static String readString() throws InterruptOperationException {
        String input = null;
        try {
            input = bis.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (input.equalsIgnoreCase("EXIT")) {
            ConsoleHelper.writeMessage(res.getString("the.end"));
            throw new InterruptOperationException();
        }
        return input;
    }

    public static String askCurrencyCode() throws InterruptOperationException {
        String currencyCode = "";
        while (true) {
            ConsoleHelper.writeMessage(res.getString("choose.currency.code"));
            currencyCode = readString();
            if (currencyCode.length() == 3) {
                break;
            }
            ConsoleHelper.writeMessage(res.getString("choose.denomination.and.count.format"));
        }
        return currencyCode.toUpperCase();
    }

    public static String[] getValidTwoDigits(String currencyCode) throws InterruptOperationException {
        String input;
        while (true) {
            ConsoleHelper.writeMessage(String.format(res.getString("choose.denomination.and.count.format"), currencyCode));
            input = readString();
            if (isValidTwoDigitsString(input)) {
                break;
            }
            ConsoleHelper.writeMessage(res.getString("invalid.data"));
        }
        return input.split(" ");
    }

    private static boolean isValidTwoDigitsString(String input) {
        try {
            String[] inputNumbers = input.trim().split(" ");
            if (inputNumbers.length == 2) {
                int firstDigit = Integer.parseInt(inputNumbers[0]);
                int secondDigit = Integer.parseInt(inputNumbers[1]);
                if (firstDigit > 0 && secondDigit > 0) {
                    return true;
                }
            }
        } catch (Exception ignored) {
        }

        return false;
    }

    public static Operation askOperation() throws InterruptOperationException {
        Operation operation;
        while (true) {
            ConsoleHelper.writeMessage(res.getString("choose.operation"));
            ConsoleHelper.writeMessage(res.getString("operation.INFO"));
            ConsoleHelper.writeMessage(res.getString("operation.DEPOSIT"));
            ConsoleHelper.writeMessage(res.getString("operation.WITHDRAW"));
            ConsoleHelper.writeMessage(res.getString("operation.EXIT"));
            try {
                return Operation.getAllowableOperationByOrdinal(Integer.parseInt(ConsoleHelper.readString()));
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
                ConsoleHelper.writeMessage(res.getString("invalid.data"));
            }
        }
    }

    public static void printExitMessage() {
        ConsoleHelper.writeMessage(res.getString("the.end"));
    }
}
