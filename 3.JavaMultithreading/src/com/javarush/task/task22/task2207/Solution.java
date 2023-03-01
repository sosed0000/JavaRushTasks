package com.javarush.task.task22.task2207;

import java.io.*;
import java.util.*;

/* 
Обращенные слова
*/

public class Solution {
    public static List<Pair> result = new LinkedList<>();

    public static void main(String[] args) {
        StringBuilder input = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in)); BufferedReader buff = new BufferedReader(new FileReader(reader.readLine()))) {
            while (buff.ready()) {
                input.append(buff.readLine()).append(" ");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        String[] words = input.toString().split(" ");

        for (int i = 0; i < words.length; i++) {
            if (words[i] == null) continue;
            StringBuilder revWord = new StringBuilder(words[i]).reverse();
            for (int j = i + 1; j < words.length; j++) {
                if (words[j] == null) continue;
                if (revWord.toString().equals(words[j])) {
                    result.add(new Pair(words[i], words[j]));
                   words[i] = words[j] = null;
                   break;
                }
            }
        }
        result.forEach(System.out::println);

    }

    public static class Pair {
        String first;
        String second;

        public Pair() {
        }

        public Pair(String first, String second) {
            this.first = first;
            this.second = second;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Pair pair = (Pair) o;

            if (first != null ? !first.equals(pair.first) : pair.first != null) return false;
            return second != null ? second.equals(pair.second) : pair.second == null;

        }

        @Override
        public int hashCode() {
            int result = first != null ? first.hashCode() : 0;
            result = 31 * result + (second != null ? second.hashCode() : 0);
            return result;
        }

        @Override
        public String toString() {
            return first == null && second == null ? "" : first == null ? second : second == null ? first : first.compareTo(second) < 0 ? first + " " + second : second + " " + first;

        }
    }

}
