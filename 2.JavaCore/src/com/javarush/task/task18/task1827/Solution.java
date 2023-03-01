package com.javarush.task.task18.task1827;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/* 
Прайсы
*/

public class Solution {
    public static void main(String[] args) {


        if (args.length == 0) {
            return;
        }

        String flag = args[0];
        String productName = args[1];
        String price = args[2];
        String quantity = args[3];

        String fileName = null;

        try (BufferedReader fileNameReader = new BufferedReader(new InputStreamReader(System.in))) {
            fileName = fileNameReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (args[0].equals("-c")) {
            try (BufferedReader reader = new BufferedReader(new FileReader(fileName));
                 BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {

                int id = 0;
                String line;
                int lineId;
                while ((line = reader.readLine()) != null) {
                    lineId = Integer.parseInt(line.substring(0, 8).trim());
                    id = (id > lineId) ? id : ++lineId;
                }


                String idToFile = id + "        ".substring(0, 8 - String.valueOf(id).length());
                String productNameToFile = productName + "                              ".substring(0, 30 - productName.length());
                String priceToFile = price + "        ".substring(0, 8 - price.length());
                String quantityToFile = quantity + "    ".substring(0, 4 - quantity.length());
                String toFile = "\n" + idToFile + productNameToFile + priceToFile + quantityToFile;

                writer.write(toFile);


            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

}