package com.javarush.task.task31.task3101;

import java.io.File;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

public class MyFileVisitor extends SimpleFileVisitor<Path> {
    private List<File> files = new ArrayList<>();

    public FileVisitResult visitFile(Path path,
                                     BasicFileAttributes fileAttributes) {
 //       System.out.println("file name:" + path);
        files.add(path.toFile());
        return FileVisitResult.CONTINUE;
    }

    public FileVisitResult preVisitDirectory(Path path,
                                             BasicFileAttributes fileAttributes) {
 //       System.out.println("Directory name:" + path);
        return FileVisitResult.CONTINUE;
    }


    public List<File> getFiles() {
        return files;
    }
}