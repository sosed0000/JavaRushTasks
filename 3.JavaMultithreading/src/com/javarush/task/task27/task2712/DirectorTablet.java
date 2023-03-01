package com.javarush.task.task27.task2712;

import com.javarush.task.task27.task2712.ad.Advertisement;
import com.javarush.task.task27.task2712.ad.StatisticAdvertisementManager;
import com.javarush.task.task27.task2712.statistic.StatisticManager;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.*;

public class DirectorTablet {
    public void printAdvertisementProfit(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);
        StatisticManager statisticManager = StatisticManager.getInstance();
        Map<String, Long> map = statisticManager.getProfitMap();
        TreeMap<Date, Long> profitMap = new TreeMap<>();
        for (Map.Entry<String, Long> entry : map.entrySet()) {
            Date date = simpleDateFormat.parse(entry.getKey(), new ParsePosition(0));
            profitMap.put(date, entry.getValue());
        }
        double total = 0D;
        while (!profitMap.isEmpty()) {
            Map.Entry<Date, Long> entry = profitMap.pollLastEntry();
            total += entry.getValue();
            System.out.printf("%s - %.2f\n", simpleDateFormat.format(entry.getKey()), (double) entry.getValue() / 100);
        }
        System.out.printf("Total - %.2f\n", total / 100);
    }
    public void printCookWorkloading(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);
        StatisticManager statisticManager = StatisticManager.getInstance();
        Map<String, Map<String, Integer>> map = statisticManager.getCookWorkloadMap();
        TreeMap<Date, Map<String, Integer>> cookMap = new TreeMap<>();
        for (Map.Entry<String, Map<String, Integer>> entry : map.entrySet()){
            Date date = simpleDateFormat.parse(entry.getKey(), new ParsePosition(0));
            cookMap.put(date, entry.getValue());
        }
        while (!cookMap.isEmpty()){
            Map.Entry<Date, Map<String, Integer>> entry = cookMap.pollLastEntry();
            System.out.println(simpleDateFormat.format(entry.getKey()));
            for (Map.Entry<String, Integer> entry1 : entry.getValue().entrySet()){
                if (entry1.getValue() > 0)
                    System.out.printf("%s - %d min\n", entry1.getKey(), entry1.getValue());
            }
            System.out.println();
        }


    }
    public void printActiveVideoSet(){
        List<Advertisement> activeVideo = StatisticAdvertisementManager.getInstance().getAdvertisements(true);
        activeVideo.sort((o1, o2) -> {
            return o1.getName().compareToIgnoreCase(o2.getName());
        });
        activeVideo.forEach(a -> System.out.printf("%s - %d\n",a.getName(), a.getHits()));


    }
    public void printArchivedVideoSet(){
        List<Advertisement> activeVideo = StatisticAdvertisementManager.getInstance().getAdvertisements(false);
        activeVideo.sort((o1, o2) -> {
            return o1.getName().compareToIgnoreCase(o2.getName());
        });
        activeVideo.forEach(a -> System.out.println(a.getName()));
    }
}
