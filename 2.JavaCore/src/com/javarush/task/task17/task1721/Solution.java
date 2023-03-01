package com.javarush.task.task17.task1721;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* 
Транзакционность
*/

public class Solution {
    public static List<String> allLines = new ArrayList<String>();
    public static List<String> forRemoveLines = new ArrayList<String>();

    public static void main(String[] args) {
        String filePass1;
        String filePass2;

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            filePass1 = reader.readLine();
            filePass2 = reader.readLine();

            reader = new BufferedReader(new FileReader(filePass1));
            String line;
            while ((line = reader.readLine()) != null) {
                allLines.add(line);
            }

            reader = new BufferedReader(new FileReader(filePass2));
            while ((line = reader.readLine()) != null) {
                forRemoveLines.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        Solution solution = new Solution();
        try {
            solution.joinData();
        } catch (CorruptedDataException cde) {
            System.out.println("Corrupted data");
        }

        System.out.println(Arrays.toString(allLines.toArray()));
        System.out.println(Arrays.toString(forRemoveLines.toArray()));

    }


    public void joinData() throws CorruptedDataException {


        for (String l :
                forRemoveLines) {
            if (!allLines.contains(l)) {
                allLines.clear();
                throw new CorruptedDataException();
            }
        }
        allLines.removeAll(forRemoveLines);
    }
}
