package com.javarush.task.task39.task3908;

/* 
Возможен ли палиндром?
*/

public class Solution {
    public static void main(String[] args) {
        System.out.println(isPalindromePermutation("qweqwerrr"));

    }

    public static boolean isPalindromePermutation(String s) {
        boolean foundOdd = false;
        int[] tableCount = new int[256];

        for (char c : s.toLowerCase().toCharArray()) {
            tableCount[c] += 1;
        }

        for (int count : tableCount) {
            if (count % 2 != 0) {
                if (foundOdd) {
                    return false;
                }
                foundOdd = true;
            }
        }

        return true;
//        if (s == null) {
//            return false;
//        }
//        Map<Character, Integer> count = new HashMap<>();
//        for (char ch : s.toLowerCase().toCharArray()) {
//            count.computeIfPresent(ch, (character, integer) -> integer+=1);
//            count.putIfAbsent(ch, 1);
//        }
//        int odds = 0;
//        for (int i :
//                count.values()) {
//            if (i % 2 != 0) {
//                odds++;
//                if (odds > 1){
//                    return false;
//                }
//            }
//        }
//        return true;
    }
}
