package com.javarush.task.task19.task1916;

import javax.annotation.processing.FilerException;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/* 
Отслеживаем изменения
*/

public class Solution {

    public static enum Type {
        ADDED,        //добавлена новая строка
        REMOVED,      //удалена строка
        SAME          //без изменений
    }

    public static class LineItem {
        public Type type;
        public String line;

        public LineItem(Type type, String line) {
            this.type = type;
            this.line = line;
        }
    }

    public static List<LineItem> lines = new ArrayList<LineItem>();

    public static void main(String[] args) {
        String file1 = ""; //предыдущая версия файла
        String file2 = ""; //текущая версия файла

        List<String> lines1 = new ArrayList<>(); //список для считываня предыдущей версии файла
        List<String> lines2 = new ArrayList<>(); //список для считываня текущей версии файла

        //"4. В методе main(String[] args) программа должна считывать пути к файлам с консоли (используй BufferedReader)."
        //"5. В методе main(String[] args) BufferedReader для считывания данных с консоли должен быть закрыт."
        //считываем
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            file1 = reader.readLine();
            file2 = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //"6. Программа должна считывать содержимое первого и второго файла (используй FileReader)."
        //"7. Потоки чтения из файлов (FileReader) должны быть закрыты."
        //считываем
        try (BufferedReader reader1 = new BufferedReader(new FileReader(file1));
             BufferedReader reader2 = new BufferedReader(new FileReader(file2))
        ) {
            while (reader1.ready()) {
                lines1.add(reader1.readLine());
            }
            while (reader2.ready()) {
                lines2.add(reader2.readLine());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }


        //"8. Список lines должен содержать объединенную версию строк из файлов, где для каждой строки указана одна из операций ADDED, REMOVED, SAME."


        //Алгоритм, который не принимает валидатор (результат идентичен примеру)
        int i = 0, j = 0;
        while (i < lines1.size() || j < lines2.size()) {
            if (j >= lines2.size()) {
                lines.add(new LineItem(Type.REMOVED, lines1.get(i)));
                i++;
            } else if (i >= lines1.size()) {
                lines.add(new LineItem(Type.ADDED, lines2.get(j)));
                j++;
            } else if (lines1.get(i).equals(lines2.get(j))) {
                lines.add(new LineItem(Type.SAME, lines1.get(i)));
                i++;
                j++;
            } else if (!lines2.get(j + 1 ).equals(lines1.get(i))) {
                lines.add(new LineItem(Type.REMOVED, lines1.get(i)));
                i++;
            } else {
                lines.add(new LineItem(Type.ADDED, lines2.get(j)));
                j++;
            }
        }


/*
        while (lines1.size() > 0) {
            if (lines1.get(0).equals(lines2.get(0))) {
                lines.add(new LineItem(Type.SAME, lines1.get(0)));
                lines1.remove(0);
                lines2.remove(0);
            } else if (!lines1.get(0).equals(lines2.get(1))) {
                lines.add(new LineItem(Type.REMOVED, lines1.get(0)));
                lines1.remove(0);
            } else {
                lines.add(new LineItem(Type.ADDED, lines2.get(0)));
                lines2.remove(0);
            }
            if (lines2.isEmpty()) {
                lines.add(new LineItem(Type.REMOVED, lines1.get(0)));
                break;
            }
        }
        if (!lines2.isEmpty()) {
            lines.add(new LineItem(Type.ADDED, lines2.remove(0)));
        }



 */

        lines.forEach(lineItem -> System.out.println(lineItem.type + " " + lineItem.line));

    }
}