package com.javarush.task.task20.task2027;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* 
Кроссворд
*/

public class Solution {
    public static void main(String[] args) {
/*
        int[][] crossword = new int[][]{
                {'f', '6', 'p', '3', 'l', 't'},
                {'u', 'k', '5', '5', '3', '3'},
                {'f', 'f', 'f', '4', 'p', '3'},
                {'m', 'y', 'f', '1', 'q', 'p'},
                {'1', '3', '0', 'i', 'j', 'u'}
        };
        detectAllWords(crossword, "home", "same", "456").forEach(System.out::println);



 */



        int[][] crossword = new int[][]{
                {'o', 'd', 'e', 'r', 'l', 'k'},
                {'u', 'k', 'a', 'm', 'e', 'o'},
                {'l', 'n', 'o', 'r', 'o', 'v'},
                {'m', 'l', 'p', 'k', 'r', 'h'},
                {'p', 'o', 'e', 'e', 'j', 'j'}
        };
        detectAllWords(crossword, "ok", "same").forEach(System.out::println);


        /*
Ожидаемый результат
home - (5, 3) - (2, 0)
same - (1, 1) - (4, 1)
         */
    }

    public static List<Word> detectAllWords(int[][] crossword, String... words) {
        List<Word> detectedWords = new ArrayList<>();
        List<Pattern> patternsDirect = new ArrayList<>();
        List<Pattern> patternsReverse = new ArrayList<>();

        //заполнение списков паттернов
        for (String word :
                words) {
            patternsDirect.add(Pattern.compile(word));
            patternsReverse.add(Pattern.compile(new StringBuilder(word).reverse().toString()));
        }

        //поиск в строках

        //собираем строку для поиска
        for (int i = 0; i < crossword.length; i++) {
            StringBuilder string = new StringBuilder();
            for (int j = 0; j < crossword[0].length; j++) {
                string.append((char) crossword[i][j]);
            }
            //поиск в строке i по patternsDirect
            for (Pattern p :
                    patternsDirect) {
                Matcher matcher = p.matcher(string);
                while (matcher.find()) {
                    Word word = new Word(p.toString());
                    word.setStartPoint(matcher.start(), i);
                    word.setEndPoint(matcher.end() - 1, i);
                    detectedWords.add(word);
                }
            }
            //поиск в строке i по patternsReverse
            for (Pattern p :
                    patternsReverse) {
                Matcher matcher = p.matcher(string);
                while (matcher.find()) {
                    //так как паттерн перевёрнут, разворачиваем его обратно перед передачей в Word
                    Word word = new Word(new StringBuilder(p.toString()).reverse().toString());
                    word.setStartPoint(matcher.end() - 1, i);
                    word.setEndPoint(matcher.start(), i);
                    detectedWords.add(word);
                }
            }
        }

        //поиск в столбцах

        //собираем строку для поиска
        for (int j = 0; j < crossword[0].length; j++) {
            StringBuilder string = new StringBuilder();
            for (int i = 0; i < crossword.length; i++) {
                string.append((char) crossword[i][j]);
            }
            //поиск в столбце j по patternsDirect
            for (Pattern p :
                    patternsDirect) {
                Matcher matcher = p.matcher(string);
                while (matcher.find()) {
                    Word word = new Word(p.toString());
                    word.setStartPoint(j, matcher.start());
                    word.setEndPoint(j, matcher.end() - 1);
                    detectedWords.add(word);
                }
            }
            //поиск в строке i по patternsReverse
            for (Pattern p :
                    patternsReverse) {
                Matcher matcher = p.matcher(string);
                while (matcher.find()) {
                    //так как паттерн перевёрнут, разворачиваем его обратно перед передачей в Word
                    Word word = new Word(new StringBuilder(p.toString()).reverse().toString());
                    word.setStartPoint(j, matcher.end() - 1);
                    word.setEndPoint(j, matcher.start());
                    detectedWords.add(word);
                }
            }
        }

        //поиск в диагоналях 🡾🢄 по верхней части, включая середину

        for (int x = 0; x < crossword[0].length; x++) {
            StringBuilder string = new StringBuilder();
            for (int i = 0, j = x; (i < crossword.length) && (j < crossword[0].length); i++, j++) {
                string.append((char) crossword[i][j]);
            }
            //поиск в строке по patternsDirect
            for (Pattern p :
                    patternsDirect) {
                Matcher matcher = p.matcher(string);
                while (matcher.find()) {
                    Word word = new Word(p.toString());
                    word.setStartPoint(x + matcher.start(), matcher.start());
                    word.setEndPoint(x + matcher.end() - 1, matcher.end() - 1);
                    detectedWords.add(word);
                }
            }
            //поиск в строке по patternsReverse
            for (Pattern p :
                    patternsReverse) {
                Matcher matcher = p.matcher(string);
                while (matcher.find()) {
                    //так как паттерн перевёрнут, разворачиваем его обратно перед передачей в Word
                    Word word = new Word(new StringBuilder(p.toString()).reverse().toString());
                    word.setStartPoint(x + matcher.end() - 1, matcher.end() - 1);
                    word.setEndPoint(x + matcher.start(), matcher.start());
                    detectedWords.add(word);
                }
            }
            //поиск в диагоналях 🡾🢄 по нижней части, не включая середину

            string = new StringBuilder();
            for (int i = x + 1, j = 0; (i < crossword.length) && (j < crossword[0].length); i++, j++) {
                string.append((char) crossword[i][j]);
            }
            //поиск в строке по patternsDirect
            for (Pattern p :
                    patternsDirect) {
                Matcher matcher = p.matcher(string);
                while (matcher.find()) {
                    Word word = new Word(p.toString());
                    word.setStartPoint(matcher.start(), x + matcher.start() + 1);
                    word.setEndPoint(matcher.end() - 1, x + matcher.end());
                    detectedWords.add(word);
                }
            }
            //поиск в строке по patternsReverse
            for (Pattern p :
                    patternsReverse) {
                Matcher matcher = p.matcher(string);
                while (matcher.find()) {
                    //так как паттерн перевёрнут, разворачиваем его обратно перед передачей в Word
                    Word word = new Word(new StringBuilder(p.toString()).reverse().toString());
                    word.setStartPoint(matcher.end() - 1, x + matcher.end());
                    word.setEndPoint(matcher.start(), x + matcher.start() + 1);
                    detectedWords.add(word);
                }
            }

        }


        //поиск в диагоналях 🡽🡿  по верхней части, включая середину

        for (int x = crossword[0].length - 1; x >= 0; x--) {
            StringBuilder string = new StringBuilder();
            for (int i = 0, j = x; i < crossword.length && j >= 0; i++, j--) {
                string.append((char) crossword[i][j]);
            }
            //поиск в строке по patternsDirect
            for (Pattern p :
                    patternsDirect) {
                Matcher matcher = p.matcher(string);
                while (matcher.find()) {
                    Word word = new Word(p.toString());
                    word.setStartPoint(x - matcher.start(), matcher.start());
                    word.setEndPoint(x - matcher.end() + 1, matcher.end() - 1);
                    detectedWords.add(word);
                }
            }
            //поиск в строке по patternsReverse
            for (Pattern p :
                    patternsReverse) {
                Matcher matcher = p.matcher(string);
                while (matcher.find()) {
                    //так как паттерн перевёрнут, разворачиваем его обратно перед передачей в Word
                    Word word = new Word(new StringBuilder(p.toString()).reverse().toString());
                    word.setStartPoint(x - matcher.end() + 1, matcher.end() - 1);
                    word.setEndPoint(x - matcher.start(), matcher.start());
                    detectedWords.add(word);
                }
            }
            //поиск в диагоналях 🡽🡿  по нижней части, не включая середину

            string = new StringBuilder();
            for (int i = x + 1, j = crossword[0].length - 1; (i < crossword.length) && (j >= 0); i++, j--) {
                string.append((char) crossword[i][j]);
            }
            //поиск в строке по patternsDirect
            for (Pattern p :
                    patternsDirect) {
                Matcher matcher = p.matcher(string);
                while (matcher.find()) {
                    Word word = new Word(p.toString());
                    word.setStartPoint(crossword[0].length - 1 - matcher.start(), x + matcher.start() + 1);
                    word.setEndPoint(crossword[0].length - matcher.end(), x + matcher.end());
                    detectedWords.add(word);
                }
            }
            //поиск в строке по patternsReverse
            for (Pattern p :
                    patternsReverse) {
                Matcher matcher = p.matcher(string);
                while (matcher.find()) {
                    //так как паттерн перевёрнут, разворачиваем его обратно перед передачей в Word
                    Word word = new Word(new StringBuilder(p.toString()).reverse().toString());
                    word.setStartPoint(crossword[0].length - matcher.end(), x + matcher.end());
                    word.setEndPoint(crossword[0].length - matcher.start() - 1, x + matcher.start() + 1);
                    detectedWords.add(word);
                }
            }

        }

        return detectedWords;
    }

    public static class Word {
        private String text;
        private int startX;
        private int startY;
        private int endX;
        private int endY;

        public Word(String text) {
            this.text = text;
        }

        public void setStartPoint(int i, int j) {
            startX = i;
            startY = j;
        }

        public void setEndPoint(int i, int j) {
            endX = i;
            endY = j;
        }

        @Override
        public String toString() {
            return String.format("%s - (%d, %d) - (%d, %d)", text, startX, startY, endX, endY);
        }
    }
}
