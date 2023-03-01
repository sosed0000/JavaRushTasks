package com.javarush.task.task31.task3112;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.*;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

/* 
Загрузчик файлов
*/

public class Solution {

    public static void main(String[] args) throws IOException {
        Path passwords = downloadFile("https://javarush.ru/testdata/secretPasswords.txt", Paths.get("D:/MyDownloads"));

        for (String line : Files.readAllLines(passwords)) {
            System.out.println(line);
        }
    }

    public static Path downloadFile(String urlString, Path downloadDirectory) throws IOException {
        // implement this method
        URL url = new URL(urlString);

        Path downloadedFile = Paths.get(downloadDirectory.toString() + "/" + urlString.substring(urlString.lastIndexOf('/') + 1));

        Path tempFile = Files.createTempFile(Paths.get("D:/Test"),"Temp - ", ".tmp");
        Files.copy(url.openStream(), tempFile, StandardCopyOption.REPLACE_EXISTING );
        Files.move(tempFile, downloadedFile);
        return downloadedFile;
    }
}
