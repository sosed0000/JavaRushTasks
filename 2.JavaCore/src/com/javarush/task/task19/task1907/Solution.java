package com.javarush.task.task19.task1907;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* 
Считаем слово
*/

public class Solution {
    public static void main(String[] args) throws IOException {
      String file = "";
        List<Integer> chars = new ArrayList<>();
        List<String> worlds = new ArrayList<>();
        StringBuilder fullFile = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            file = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (FileReader reader = new FileReader(file)) {
            while (reader.ready()) {
                chars.add(reader.read());

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        //if (!((char) (int) chars.get(i) + "").equals("[.,;:!?-()\"]")) {

        chars.forEach(ch -> fullFile.append(((char) (int) ch)));
        int count = 0;
 //       Pattern p = Pattern.compile(//"(^|[.,;:!\\?\\-()\"\n\r\f^]|\\W)world($|[.,;:!\\?\\-()\"\n\r\f])");
//        Pattern p = Pattern.compile("(^|!)world[.,;:!\\?\\-()\"\n\r\f$]");
 //       Matcher m = p.matcher(fullFile);
//        while (m.find()) {
 //           count++;
 //       }
   //     System.out.println(count);
        String[] strings = fullFile.toString().split("(^|[.,;:!\\?\\-()\"\n\r\f^]|\\W|$)");

        for (String s:
             strings) {
            if (s.equals("world")) {
                count++;
            }
        }
        System.out.println(count);




    }
}
