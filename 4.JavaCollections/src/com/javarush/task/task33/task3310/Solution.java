package com.javarush.task.task33.task3310;

import com.javarush.task.task33.task3310.strategy.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Solution {
    public static void main(String[] args) {
     //   testStrategy(new HashMapStorageStrategy(), 20000);
    //    testStrategy(new OurHashMapStorageStrategy(), 5000);
   //     testStrategy(new FileStorageStrategy(), 50);
        testStrategy(new OurHashBiMapStorageStrategy(), 20000);
        testStrategy(new HashBiMapStorageStrategy(), 20000);
        testStrategy(new DualHashBidiMapStorageStrategy(), 20000);
        testStrategy(new OurHashBiMapStorageStrategy(), 30000);
        testStrategy(new HashBiMapStorageStrategy(), 30000);
        testStrategy(new DualHashBidiMapStorageStrategy(), 20000);



    }

    public static Set<Long> getIds(Shortener shortener, Set<String> strings) {
        Set<Long> ids = new HashSet<>();
        strings.forEach(s -> ids.add(shortener.getId(s)));
        return ids;
    }

    public static Set<String> getStrings(Shortener shortener, Set<Long> keys) {
        Set<String> strings = new HashSet<>();
        keys.forEach(l -> strings.add(shortener.getString(l)));
        return strings;
    }

    public static void testStrategy(StorageStrategy strategy, long elementsNumber) {
        Helper.printMessage(strategy.getClass().getSimpleName() + ":");
        Set<String> inputStrings = new HashSet<>();
        for (int i = 0; i < elementsNumber; i++) {
            inputStrings.add(Helper.generateRandomString());
        }
        Shortener shortener = new Shortener(strategy);

        Date startTime = new Date();
        Set<Long> ids = getIds(shortener, inputStrings);
        Date endTime = new Date();
        Helper.printMessage("Время получения идентификаторов для " + elementsNumber + " строк: " + String.valueOf(endTime.getTime() - startTime.getTime()));

        startTime = new Date();
        Set<String> outputStrings = getStrings(shortener, ids);
        endTime = new Date();
        Helper.printMessage("Время получения строк для " + elementsNumber + " идентификаторов: " + String.valueOf(endTime.getTime() - startTime.getTime()));

        if (inputStrings.equals(outputStrings)) {
            Helper.printMessage("Тест пройден.");
        } else {
            Helper.printMessage("Тест не пройден.");
        }
    }
}
