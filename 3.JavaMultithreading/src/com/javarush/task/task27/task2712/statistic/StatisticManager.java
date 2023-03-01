package com.javarush.task.task27.task2712.statistic;

import com.javarush.task.task27.task2712.statistic.event.CookedOrderEventDataRow;
import com.javarush.task.task27.task2712.statistic.event.EventDataRow;
import com.javarush.task.task27.task2712.statistic.event.EventType;
import com.javarush.task.task27.task2712.statistic.event.VideoSelectedEventDataRow;

import java.text.SimpleDateFormat;
import java.util.*;

public class StatisticManager {
    private static StatisticManager ourInstance = new StatisticManager();

    public static StatisticManager getInstance() {
        return ourInstance;
    }

    private StatisticStorage statisticStorage = new StatisticStorage();

    private StatisticManager() {
    }

    private class StatisticStorage {
        private Map<EventType, List<EventDataRow>> storage = new HashMap<>();

        private StatisticStorage() {
            for (EventType type : EventType.values()) {
                this.storage.put(type, new ArrayList<EventDataRow>());
            }
        }

        private void put(EventDataRow data) {
            EventType type = data.getType();
            if (!this.storage.containsKey(type))
                throw new UnsupportedOperationException();

            this.storage.get(type).add(data);
        }


        private List<EventDataRow> get(EventType e) {
            return storage.get(e);
        }

    }

    public void register(EventDataRow data) {
        this.statisticStorage.put(data);
    }


    public Map<String, Long> getProfitMap() {
        Map<String, Long> dayAmounts = new HashMap<>();
        List<EventDataRow> shownAdvertisement = statisticStorage.get(EventType.SELECTED_VIDEOS);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);
        for (EventDataRow row : shownAdvertisement) {
            String key = simpleDateFormat.format(row.getDate());
            long amount = ((VideoSelectedEventDataRow) row).getAmount();
            if (dayAmounts.containsKey(key))
                dayAmounts.compute(key, (a, b) -> b + amount);
            else
                dayAmounts.put(key, amount);
        }
        return dayAmounts;
    }

    public Map<String, Map<String, Integer>> getCookWorkloadMap() {
        Map<String, Map<String, Integer>> cookWorkload = new HashMap<>();
        List<EventDataRow> cookedOrders = statisticStorage.get(EventType.COOKED_ORDER);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);
        for (EventDataRow row : cookedOrders) {
            String key = simpleDateFormat.format(row.getDate());
            int workingMinutes = (row.getTime() / 60) + (row.getTime() % 60 > 0 ? 1 : 0);
            String cookName = ((CookedOrderEventDataRow) row).getCookName();
            if (cookWorkload.containsKey(key)) {
                Map<String, Integer> cookMap = cookWorkload.get(key);
                if (cookMap.containsKey(cookName))
                    cookMap.compute(cookName, (a, b) -> b + workingMinutes);
                else
                    cookMap.put(cookName, workingMinutes);
                cookWorkload.put(key, cookMap);
            }else {
                Map<String, Integer> cookMap = new HashMap<>();
                cookMap.put(cookName, workingMinutes);
                cookWorkload.put(key, cookMap);
            }
        }
        return cookWorkload;
    }
}

