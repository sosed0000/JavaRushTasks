package com.javarush.task.task22.task2209;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/* 
Составить цепочку слов
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        //...
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader fileReader = new BufferedReader(new FileReader(reader.readLine()));
        reader.close();
        StringBuilder input = new StringBuilder();
        while (fileReader.ready()) {
            input.append(fileReader.readLine());
        }
        fileReader.close();
        StringBuilder result = getLine(input.toString().split(" "));
        System.out.println(result.toString());
    }

    public static StringBuilder getLine(String... words) {
        if (words.length == 0) return new StringBuilder();
        List<String> wordsList = new ArrayList<>(Arrays.asList(words));
        StringBuilder result = new StringBuilder(wordsList.remove(0));
        while (!wordsList.isEmpty()) {
            int freeWords = wordsList.size();
            String keyChar = result.charAt(result.length() - 1) + "";
            for (int i = 0; i < freeWords; i++) {
                if ((wordsList.get(i).charAt(0) + "").equalsIgnoreCase(keyChar)) {
                    result.append(" ").append(wordsList.remove(i));
                    break;
                }
            }
            if (freeWords == wordsList.size()) {

                while (!wordsList.isEmpty()) {
                    keyChar = result.charAt(0) + "";
                    for (int i = 0; i < freeWords; i++) {
                        if ((wordsList.get(i).charAt(wordsList.get(i).length() - 1) + "").equalsIgnoreCase(keyChar)) {
                            result.insert(0, wordsList.remove(i) + " ");
                            break;
                        }
                    }
                    if (freeWords == wordsList.size()) break;
                }

                result.append(" ").append(result.substring(0, result.indexOf(" "))).delete(0, result.indexOf(" ") + 1);


            }

        }
        return result;
    }
}
