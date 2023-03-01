package com.javarush.task.task19.task1918;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;
import org.jsoup.select.Elements;


import java.io.*;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* 
Знакомство с тегами
*/

public class Solution {
    public static void main(String[] args) throws IOException {

     //   args = "span".split(" ");

        String tag = args[0];
        String file = "";
        String content = "";



        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            file = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            while (reader.ready()) {
                content = content + reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        Document document = Jsoup.parse(content, "", Parser.xmlParser());
        Elements h1 = document.select(tag);
        System.out.println(h1);

/*

        Pattern tagRegex = Pattern.compile("<" + tag + "(.*)</" + tag + ">");
       // Pattern tagRegex = Pattern.compile("<span(.+?)</span>");

        //content.replaceAll("\n", "").replaceAll("\r", "");
        Matcher matcher = tagRegex.matcher(content);

        while (matcher.find()) {
            String s = "<" + tag + matcher.group(1) + "</" + tag + ">";
          //  System.out.println(s);
            find(tagRegex, tag, s);

        }




    }

    public static void find(Pattern tagRegex, String tag, String content) {


        Matcher m = tagRegex.matcher(content);
        if (m.find()) {
            String s = "<" + tag + m.group(1) + "</" + tag + ">";
            Pattern p1 = Pattern.compile("<" + tag + "(.+?)</" + tag + ">");
            Matcher p = p1.matcher(s);
            if (p.find()) {
                String s1 = "<" + tag + p.group(1) + "</" + tag + ">";
                System.out.println(s1);
            } else {
                System.out.println(s);
            }
            s = s.substring(tag.length() + 1);
            find(tagRegex, tag, s);
        }




 */

    }
}
