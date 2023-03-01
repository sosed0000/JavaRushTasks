package com.javarush.task.task18.task1810;

import java.io.*;

/* 
DownloadException
*/

public class Solution {
    public static void main(String[] args) throws DownloadException {
        try (BufferedReader reader  = new BufferedReader(new InputStreamReader(System.in))){
            FileInputStream in;
            do {
                 in = new FileInputStream(reader.readLine());
            } while (in.available() >= 1000);
            in.close();
            throw new DownloadException();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static class DownloadException extends Exception {

    }
}
