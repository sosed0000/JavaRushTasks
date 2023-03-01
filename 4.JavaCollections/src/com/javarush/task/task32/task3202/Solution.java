package com.javarush.task.task32.task3202;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Base64;

/* 
Читаем из потока
*/

public class Solution {
    public static void main(String[] args) throws IOException {
  //      StringWriter writer = getAllDataFromInputStream(new FileInputStream("testFile.log"));
        StringWriter writer = null;
        System.out.println(writer.toString());
    }

    public static StringWriter getAllDataFromInputStream(InputStream is) throws IOException {
        byte[] buffer = new byte[1024];
        String s;
        StringWriter stringWriter = new StringWriter();
        int readBytes;
        if (is != null) {
            while ((readBytes = is.read(buffer)) > 0) {
                s = new String(buffer, StandardCharsets.UTF_8);
                stringWriter.write(s, 0, readBytes);
            }
        }
        return stringWriter;
    }
}
