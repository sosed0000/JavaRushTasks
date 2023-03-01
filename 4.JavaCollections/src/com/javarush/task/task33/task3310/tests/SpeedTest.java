package com.javarush.task.task33.task3310.tests;

import com.javarush.task.task33.task3310.Helper;
import com.javarush.task.task33.task3310.Shortener;
import com.javarush.task.task33.task3310.strategy.HashBiMapStorageStrategy;
import com.javarush.task.task33.task3310.strategy.HashMapStorageStrategy;
import org.junit.Assert;
import org.junit.Test;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class SpeedTest {
    public long getTimeToGetIds(Shortener shortener, Set<String> strings, Set<Long> ids){
        Date startTime = new Date();
        strings.forEach(s -> ids.add(shortener.getId(s)));
        Date endTime = new Date();
        return endTime.getTime() - startTime.getTime();
    }
    public long getTimeToGetStrings(Shortener shortener, Set<Long> ids, Set<String> strings){
        Date startTime = new Date();
        ids.forEach(id -> strings.add(shortener.getString(id)));
        Date endTime = new Date();
        return endTime.getTime() - startTime.getTime();
    }
    @Test
    public void testHashMapStorage(){
        Shortener shortener1 = new Shortener(new HashMapStorageStrategy());
        Shortener shortener2 = new Shortener(new HashBiMapStorageStrategy());
        Set<String> origStrings = new HashSet<>();
        for (int i = 0; i < 1000; i++) {
            origStrings.add(Helper.generateRandomString());
        }
        Set<Long> ids = new HashSet<>();
        long shortener1Time = getTimeToGetIds(shortener1, origStrings, ids);
        long shortener2Time = getTimeToGetIds(shortener2, origStrings, ids);
        Assert.assertTrue(shortener1Time > shortener2Time);

        shortener1Time = getTimeToGetStrings(shortener1, ids, new HashSet<>());
        shortener2Time = getTimeToGetStrings(shortener2, ids, new HashSet<>());
        Assert.assertEquals(shortener1Time, shortener2Time, 30);
    }
}
