package com.javarush.task.task18.task1828;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/* 
Прайсы 2
*/

public class Solution {
    public static class Product {
        int id;
        String name;
        String price;
        String quantity;

        public Product(int id, String name, String price, String quantity) {
            this.id = id;
            this.name = name;
            this.price = price;
            this.quantity = quantity;
        }

        @Override
        public String toString() {
            return String.format("\n%-8d%-30s%-8s%-4s", id, name, price, quantity);
        }
    }

    public static void main(String[] args) throws Exception {

     //   args = "-d 19847986".split(" ");


        if (args.length == 0) {
            return;
        }

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName = reader.readLine();

        List<Product> products = new ArrayList<>();

        try (BufferedReader fileReader = new BufferedReader(new FileReader(fileName))) {
            while (fileReader.ready()) {
                Product product = getProduct(fileReader.readLine());
                products.add(product);
            }
        }

        switch (args[0]) {
            case "-c":
                int id = 0;
                for (Product product : products) {
                    if (product.id > id) id = product.id;
                }
                String name = "";
                for (int i = 1; i < args.length - 2; i++) {
                    name += args[i] + " ";
                }
                if (name.length() > 30) {
                    name = name.substring(0, 30);
                }
                String price = args[args.length - 2];
                if (price.length() > 8) {
                    price = price.substring(0, 8);
                }
                String quantity = args[args.length - 1];
                if (quantity.length() > 4) {
                    quantity = quantity.substring(0, 4);
                }
                Product newProduct = new Product(++id, name.trim(), price, quantity);
                try (FileWriter fileWriter = new FileWriter(fileName, true)) {
                    fileWriter.write(newProduct.toString());
                }
                break;

            case "-u":
                Product updateProduct = getProduct(args);
                for (Product p:
                     products) {
                    if (p.id == updateProduct.id) {
                        p.name = updateProduct.name;
                        p.price = updateProduct.price;
                        p.quantity = updateProduct.quantity;
                    }
                }
                writeAllProducts(products, fileName);
                break;

            case "-d":
                for (int i = 0; i < products.size(); i++) {
                    if (products.get(i).id == Integer.parseInt(args[1])) {
                        products.remove(i);
                        break;
                    }
                }
                writeAllProducts(products, fileName);
                break;
        }
    }

    public static Product getProduct(String string) {
        String id = string.substring(0, 8).trim();
        String name = string.substring(8, 38).trim();
        String price = string.substring(38, 46).trim();
        String quantity = string.substring(46, 50).trim();
        return new Product(Integer.parseInt(id), name, price, quantity);
    }

    public static Product getProduct(String[] args) {
        String id = args[1].trim();
        String name = args[2].trim();
        String price = args[3].trim();
        String quantity = args[4].trim();
        return new Product(Integer.parseInt(id), name, price, quantity);
    }

    public static void writeAllProducts(List<Product> products, String fileName) {
        try (FileWriter fileWriter = new FileWriter(fileName)) {
            if (products.isEmpty()) {
                return;
            }
            fileWriter.write(products.get(0).toString().substring(1));
            for (int i = 1; i < products.size(); i++) {
                fileWriter.write(products.get(i).toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
