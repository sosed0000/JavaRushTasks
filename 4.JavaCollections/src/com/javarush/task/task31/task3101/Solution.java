package com.javarush.task.task31.task3101;

import java.io.*;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

/* 
Проход по дереву файлов
*/

public class Solution {
    public static void main(String[] args) {


          if (args.length == 0) {
            return;
        }


 //       args = new String[]{"d:/Test", "d:/1.txt"};

        // Map<Path, File> files = new TreeMap<>();


        Path path = Paths.get(args[0]);
        File resultFileAbsolutePath = new File(args[1]);
        File newNameFile = new File(resultFileAbsolutePath.getParent() + "/allFilesContent.txt");
 /*
        if (!FileUtils.isExist(resultFileAbsolutePath)) {
            System.out.println("Файл не найден!");
            return;
        }

  */

        if (FileUtils.isExist(newNameFile)) {
            FileUtils.deleteFile(newNameFile);
        }
        FileUtils.renameFile(resultFileAbsolutePath, newNameFile);
        resultFileAbsolutePath = newNameFile;


        MyFileVisitor myFileVisitor = new MyFileVisitor();
        try {
            Files.walkFileTree(path, myFileVisitor);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //    myFileVisitor.getFiles().forEach(System.out::println);
        StringBuilder allFilesContent = new StringBuilder();

        for (File file :
                myFileVisitor.getFiles()) {
            if (file.isFile()) {
                try {
                    BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(file.getAbsolutePath()));
                    if (inputStream.available() <= 50) {
                        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                        while (reader.ready()) {
                            allFilesContent.append(reader.readLine()).append("\n");
                        }
                        reader.close();
                    }
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
     //   System.out.println(allFilesContent);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(resultFileAbsolutePath, true))) {
            writer.write(allFilesContent.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}

