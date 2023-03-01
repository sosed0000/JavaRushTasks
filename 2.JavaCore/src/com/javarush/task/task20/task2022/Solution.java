package com.javarush.task.task20.task2022;

import java.io.*;

/* 
Переопределение сериализации в потоке
*/

public class Solution implements Serializable, AutoCloseable {
    private transient FileOutputStream stream;
    private String fileName;

    public Solution(String fileName) throws FileNotFoundException {
        this.fileName = fileName;
        this.stream = new FileOutputStream(fileName);
    }

    public void writeObject(String string) throws IOException {
        stream.write(string.getBytes());
        stream.write("\n".getBytes());
        stream.close();

    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();

        //   out.close();
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        this.stream = new FileOutputStream(fileName ,true);

     //   in.close();
    }

    @Override
    public void close() throws Exception {
        System.out.println("Closing everything!");
        stream.close();
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        Solution solution = new Solution("d:/1.txt");
        solution.writeObject("Котик, котик, котик");

        System.out.println(solution.stream.toString());

        FileOutputStream fileOutputStream = new FileOutputStream("d:/2.txt");
        ObjectOutputStream out = new ObjectOutputStream(fileOutputStream);

        out.writeObject(solution);

        FileInputStream fileInputStream = new FileInputStream("d:/2.txt");
        ObjectInputStream in = new ObjectInputStream(fileInputStream);


        Solution loadedObject = (Solution) in.readObject();
        System.out.println(loadedObject.stream.toString());


    }
}
