package com.javarush.task.task40.task4006;

import java.io.*;
import java.net.Socket;
import java.net.URL;

/* 
Отправка GET-запроса через сокет
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        getSite(new URL("http://javarush.ru/social.html"));
   //     getSite(new URL("https://eoxjqwnxui41u4h.m.pipedream.net"));
    }

    public static void getSite(URL url) {

        try {
            Socket socket = new Socket(url.getHost(), 80);

            PrintStream out = new PrintStream( socket.getOutputStream() );

            out.println("GET " + url.getPath());
            out.println();


            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String responseLine;

            while ((responseLine = bufferedReader.readLine()) != null) {
                System.out.println(responseLine);
            }
            bufferedReader.close();
            socket.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}