package com.javarush.task.task33.task3310.tests;

import com.javarush.task.task33.task3310.Shortener;
import com.javarush.task.task33.task3310.strategy.*;
import org.junit.Assert;
import org.junit.Test;


import java.util.HashSet;
import java.util.Set;

public class FunctionalTest {
    public void testStorage(Shortener shortener) {
        String s0 = "First testing string";
        String s1 = "Second testing string";
        String s2 = s0;

        long[] indexes = new long[3];
        indexes[0] = shortener.getId(s0);
        indexes[1] = shortener.getId(s1);
        indexes[2] = shortener.getId(s2);
        Assert.assertNotEquals(indexes[1], indexes[0]);
        Assert.assertNotEquals(indexes[1], indexes[2]);
        Assert.assertEquals(indexes[0], indexes[2]);

        String[] strings = new String[3];
        strings[0] = shortener.getString(indexes[0]);
        strings[1] = shortener.getString(indexes[1]);
        strings[2] = shortener.getString(indexes[2]);
        Assert.assertEquals(strings[0], s0);
        Assert.assertEquals(strings[1], s1);
        Assert.assertEquals(strings[2], s2);
    }
    @Test
    public void testHashMapStorageStrategy() {
        Shortener shortener = new Shortener(new HashMapStorageStrategy());
        testStorage(shortener);
    }

    @Test
    public void testOurHashMapStorageStrategy() {
        Shortener shortener = new Shortener(new OurHashMapStorageStrategy());
        testStorage(shortener);
    }

    @Test
    public void testFileStorageStrategy() {
        Shortener shortener = new Shortener(new FileStorageStrategy());
        testStorage(shortener);
    }

    @Test
    public void testHashBiMapStorageStrategy() {
        Shortener shortener = new Shortener(new HashBiMapStorageStrategy());
        testStorage(shortener);
    }

    @Test
    public void testDualHashBidiMapStorageStrategy() {
        Shortener shortener = new Shortener(new DualHashBidiMapStorageStrategy());
        testStorage(shortener);
    }

    @Test
    public void testOurHashBiMapStorageStrategy() {
        Shortener shortener = new Shortener(new OurHashBiMapStorageStrategy());
        testStorage(shortener);
    }

    }


