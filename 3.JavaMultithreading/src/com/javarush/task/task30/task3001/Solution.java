package com.javarush.task.task30.task3001;

/*
Конвертер систем счислений
*/

import java.math.BigInteger;

public class Solution {
    public static void main(String[] args) {
        Number number = new Number(NumberSystemType._10, "6");
        Number result = convertNumberToOtherNumberSystem(number, NumberSystemType._2);
        System.out.println(result);    //expected 110

        number = new Number(NumberSystemType._16, "6df");
        result = convertNumberToOtherNumberSystem(number, NumberSystemType._8);
        System.out.println(result);    //expected 3337

        number = new Number(NumberSystemType._16, "abcdefabcdef");
        result = convertNumberToOtherNumberSystem(number, NumberSystemType._16);
        System.out.println(result);    //expected abcdefabcdef
    }

    public static Number convertNumberToOtherNumberSystem(Number number, NumberSystem expectedNumberSystem) {
        //напишите тут ваш код
//        if (!isValid(number, expectedNumberSystem)) {
        checkDataIsValid(number);
        int sourceType = number.getNumberSystem().getNumberSystemIntValue();
        String sourceNumber = number.getDigit().toLowerCase();
        boolean isZero = false;
        try {
            isZero = Integer.parseInt(sourceNumber) == 0;
        } catch (NumberFormatException ignored) {
        }
        if (isZero)
            return new Number(expectedNumberSystem, "0");
        boolean isNegative = sourceNumber.charAt(0) == '-';
        if (isNegative)
            sourceNumber = sourceNumber.replaceFirst("-", "");
        BigInteger decimalNumber = convertToDecimal(sourceNumber, sourceType);
        String expectedNumber;
        if (expectedNumberSystem.getNumberSystemIntValue() == 10)
            expectedNumber = String.valueOf(decimalNumber);
        else
            expectedNumber = convertDecimalToAny(decimalNumber, expectedNumberSystem.getNumberSystemIntValue());

        return new Number(expectedNumberSystem, isNegative ? "-" + expectedNumber : expectedNumber);
    }

    private static boolean isValid(Number number, NumberSystem expectedNumberSystem) {

        if (expectedNumberSystem == null)
            return false;

        int sourceType = number.getNumberSystem().getNumberSystemIntValue();
        String sourceNumber = number.getDigit();
        if (sourceNumber == null || sourceNumber.isEmpty())
            return false;

        sourceNumber = sourceNumber.toLowerCase();
        String regex;
        if (sourceType <= 10)
            regex = "-?[0-" + (sourceType - 1) + "]+";
        else
            regex = "-?[0-9a-" + (char) (sourceType + 87) + "]+";
        return sourceNumber.matches(regex);
    }

    private static BigInteger convertToDecimal(String number, int delimiter) {
        if (delimiter == 10)
            return new BigInteger(number);
        char[] chars = number.toCharArray();
        int[] digits = new int[chars.length];
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] > 57)
                digits[i] = chars[i] - 87;
            else
                digits[i] = chars[i] - 48;
        }
        int power = 0;
        BigInteger decimalNumber = BigInteger.ZERO;
        BigInteger bigIntDelimiter = BigInteger.valueOf(delimiter);
        for (int i = digits.length - 1; i >= 0; i--)
            decimalNumber = decimalNumber.add(bigIntDelimiter.pow(power++).multiply(BigInteger.valueOf(digits[i])));
        return decimalNumber;
    }

    private static String convertDecimalToAny(BigInteger decimalNumber, int delimiter) {
        StringBuilder expectedNumber = new StringBuilder();
        BigInteger bigIntDelimiter = BigInteger.valueOf(delimiter);
        while (decimalNumber.compareTo(BigInteger.ZERO) > 0) {
            int digit = (decimalNumber.mod(bigIntDelimiter)).intValue();
            if (digit > 9)
                expectedNumber.append((char) (digit + 87));
            else expectedNumber.append(digit);
            decimalNumber = decimalNumber.divide(bigIntDelimiter);
        }
        return expectedNumber.reverse().toString();
    }

    private static void checkDataIsValid(Number number) {
        if (number.getNumberSystem().getNumberSystemIntValue() <= 10) {
            checkDigitsLimited(number.getDigit(), number.getNumberSystem().getNumberSystemIntValue() + 48);
        } else {
            checkCharsLimited(number.getDigit(), number.getNumberSystem().getNumberSystemIntValue() + 87);
        }
    }

    private static void checkCharsLimited(String digit, int value) {
        for (char c : digit.toCharArray()) {
            if (!(c >= 48 && c <= 57) && !(c >= 97 && c < value)) {
                throw new NumberFormatException();
            }
        }

    }

    private static void checkDigitsLimited(String digit, int value) {
        for (char c : digit.toCharArray()) {
            if (!(c >= 48 && c < value)) {
                throw new NumberFormatException();
            }
        }
    }
}
