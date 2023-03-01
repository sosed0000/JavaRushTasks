package com.javarush.task.task36.task3605;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/* 
Использование TreeSet
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        args = new String[] {"d:\\1122.txt"};
        Set<Character> chars = new TreeSet<>();
        try {
            List<String> lines = Files.readAllLines(new File(args[0]).toPath());
            for (String line : lines){
                for (char ch : line.toLowerCase().toCharArray()){
                    if (Character.isAlphabetic(ch)){
                        chars.add(ch);
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if (chars.size() < 5) {
            chars.forEach(System.out::print);
        } else {
            Iterator<Character> iterator = chars.iterator();
            for (int i = 0; i < 5; i++) {
                System.out.print(iterator.next());
            }
        }

    }
}
