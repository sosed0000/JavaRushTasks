package com.javarush.task.task31.task3113;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/* 
Что внутри папки?
*/

public class Solution {

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        Path dir = Paths.get(scanner.nextLine());
        scanner.close();
        if (!dir.toFile().isDirectory()) {
            System.out.println(dir + " - не папка");
            return;
        }

        Files.walkFileTree(dir, new DirInfo());
        System.out.printf("Всего папок - %d\n", dirsCount);
        System.out.printf("Всего файлов - %d\n", filesCount);
        System.out.printf("Общий размер - %d\n", summaryFilesSize);

    }

    static int dirsCount = -1;
    static int filesCount;
    static long summaryFilesSize;



    static class DirInfo extends SimpleFileVisitor<Path> {


        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
            filesCount++;
            summaryFilesSize += Files.size(file);
            return FileVisitResult.CONTINUE;
        }

        @Override
        public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
            dirsCount++;
            return FileVisitResult.CONTINUE;
        }
    }
}
