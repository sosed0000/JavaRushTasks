package com.javarush.task.task38.task3802;

/* 
Проверяемые исключения (checked exception)
*/

import java.io.File;
import java.nio.file.Files;

public class VeryComplexClass {
    public void veryComplexMethod() throws Exception {
        //напишите тут ваш код
        Files.delete((new File("r:/d.exe").toPath()));
    }

    public static void main(String[] args) throws Exception {

    }
}
