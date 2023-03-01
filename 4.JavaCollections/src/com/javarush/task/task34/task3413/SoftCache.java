package com.javarush.task.task34.task3413;

import java.lang.ref.SoftReference;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SoftCache {
    private Map<Long, SoftReference<AnyObject>> cacheMap = new ConcurrentHashMap<>();

    public AnyObject get(Long key) {
        SoftReference<AnyObject> softReference = cacheMap.get(key);

        //напишите тут ваш код
        if (cacheMap.get(key) == null) {return null;}
        return softReference.get();

    }

    public AnyObject put(Long key, AnyObject value) {

        if (cacheMap.get(key) == null) {
            return null;
        } else {
            AnyObject object = get(key);
        SoftReference<AnyObject> softReference = cacheMap.put(key, new SoftReference<>(value));
            softReference.clear();

        return object;}

        //напишите тут ваш код

    }

    public AnyObject remove(Long key) {
            AnyObject object = get(key);
            if (object == null) {return null;}
            SoftReference<AnyObject> softReference = cacheMap.remove(key);
            softReference.clear();
            return object;

        //напишите тут ваш код
    }
}