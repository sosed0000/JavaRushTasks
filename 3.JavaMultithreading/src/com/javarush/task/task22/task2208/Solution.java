package com.javarush.task.task22.task2208;

import java.util.LinkedHashMap;
import java.util.Map;


/* 
Формируем WHERE
*/

public class Solution {
    public static void main(String[] args) {
        Map<String, String> test = new LinkedHashMap<>();
        test.put("name", null);
        test.put("country", null);
        test.put("city", null);
        test.put("age", null);
        System.out.println(getQuery(test));

    }

    public static String getQuery(Map<String, String> params) {
        StringBuilder result = new StringBuilder();
        for (String key : params.keySet()) {
            if (params.get(key) != null) {
                result.append(String.format("%s = '%s' and ", key, params.get(key)));
            }
        }
        if (result.length() > 0) {
            result.replace(result.lastIndexOf(" and "), result.length(), "");
        }
        return result.toString();
    }
}
