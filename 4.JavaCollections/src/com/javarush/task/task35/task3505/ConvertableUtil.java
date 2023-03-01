package com.javarush.task.task35.task3505;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConvertableUtil {

    public static <K, V extends Convertable<?>> Map<K, V> convert(List<? extends Convertable<?>> list) {
        Map<K, V> result = new HashMap<>();

        for (Convertable<?> v : list) {
            result.put((K) v.getKey(), (V) v);
        }

        return result;
    }
}
