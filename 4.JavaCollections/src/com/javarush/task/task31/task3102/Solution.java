package com.javarush.task.task31.task3102;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

/* 
Находим все файлы
*/

public class Solution {
    static List<String> files = new ArrayList<>();


    public static void main(String[] args) throws IOException {
        List<String> result = getFileTree("d:\\Test\\");
        result.forEach(System.out::println);
    }

    static class MyFileVisitor extends SimpleFileVisitor<Path> {

        public FileVisitResult visitFile(Path path, BasicFileAttributes fileAttributes) {
            files.add(path.toString());
            return FileVisitResult.CONTINUE;
        }

    }

    public static List<String> getFileTree(String root) throws IOException {
        Files.walkFileTree(Paths.get(root), new MyFileVisitor());
        return files;
    }


}
