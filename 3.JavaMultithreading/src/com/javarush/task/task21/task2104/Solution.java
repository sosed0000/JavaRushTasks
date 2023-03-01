package com.javarush.task.task21.task2104;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/* 
Equals and HashCode
*/

public class Solution {
    private final String first, last;

    public Solution(String first, String last) {
        this.first = first;
        this.last = last;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Solution)) return false;

        Solution solution = (Solution) o;

        if (first != null ? !first.equals(solution.first) : solution.first != null) return false;
        return last != null ? last.equals(solution.last) : solution.last == null;
    }

    @Override
    public int hashCode() {
        int result = first != null ? first.hashCode() : 0;
        result = 31 * result + (last != null ? last.hashCode() : 0);
        return result;
    }
    //    @Override
//    public boolean equals(Object o) {
//        if (o == null) return false;
//        if (o.getClass() != Solution.class) {
//            return false;
//        }
//        return equals((Solution) o);
//    }
//
//    public boolean equals(Solution n) {
//
//        return n.first == first && n.last == last;
//    }
//
//    @Override
//    public int hashCode() {
//        int hashCode = 0;
//        for (char ch : first.toCharArray()) {
//            hashCode +=ch;
//        }
//        for (char ch : last.toCharArray()) {
//            hashCode +=ch;
//        }
//        return hashCode;
//    }

    public static void main(String[] args) {

        Set<Solution> s = new HashSet<>();
        s.add(new Solution("Donald", "Duck"));
        System.out.println(s.contains(new Solution("Donald", "Duck")));
    }
}
