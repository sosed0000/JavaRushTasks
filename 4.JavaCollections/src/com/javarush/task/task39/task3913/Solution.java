package com.javarush.task.task39.task3913;

import org.junit.Assert;

import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Solution {
    public static void main(String[] args) throws ParseException {
        LogParser logParser = new LogParser(Paths.get("d:/logs/"));
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy H:mm:ss");

        System.out.println(logParser.execute("get status for user = \"Eduard Petrovich Morozko\" and date between \"11.12.2013 0:00:00\" and \"03.01.2014 23:59:59\""));
    }

}