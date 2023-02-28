package _tests;


import java.text.ParseException;
import java.util.*;

public class Main {
    int s;

    public static void main(String[] args) throws ParseException {
        String currencyCode = "-5";

        System.out.println(currencyCode.matches("\\d"));


    }


    //        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy H:mm:ss");
//        Date date = dateFormat.parse("13.09.2013 05:04:50");
//        date = null;
//        System.out.println((new Date()).before(date));


//        int qty = 10;
//        p1(availableBanknotes, 0, 0, new ArrayList<>());

//
//        System.out.println(findCombinations(new ArrayList<>(), availableBanknotes, 700, new HashSet<>()));

//
//    public static  Set<List<Integer>> findCombinations(List<Integer> result, List<Integer> banknotes, int expectedAmount, Set<List<Integer>> combinations) {
//        for (Integer ch : banknotes) {
//            result.add(ch);
//            int sum = result.stream().mapToInt(Integer::intValue).sum();
//            if (sum == expectedAmount) {
//                result.sort(Comparator.reverseOrder());
//                if (result.size() <= min) {
//                    combinations.add(new ArrayList<>(result));
//                    min = result.size();
//                                  break;
//                }
//            }
//              //     if (sum > expectedAmount) {break;}
//                   if (result.size() > min) {break;}
//            findCombinations(result, banknotes.subList(1, banknotes.size()), expectedAmount, combinations);
//            result.remove(ch);
//
//
//        }
//        return combinations;
//    }
//
//    static int min = Integer.MAX_VALUE;

//
//    static void p1(List<Integer> availableBanknotes, int startPosition1, int startPosition2, List<Integer> result) {
//        int sum = result.stream().mapToInt(Integer::intValue).sum();
//        for (Integer banknote : availableBanknotes.subList(startPosition1, availableBanknotes.size())) {
//
//            while (sum < expectedAmount) {
//                if (availableBanknotes.size() <= startPosition1) {
//                    break;
//                }
//                result.add(availableBanknotes.get(startPosition2));
//                p1(availableBanknotes, startPosition1, startPosition2++, result);
//            }
//            if (sum == expectedAmount) {
//                System.out.println(result + " " + sum);
//            }
//        }
//
//
//    }

    /*
    static void p1(int[] availableBanknotes, int len, int startPosition, int[]result, int ll) {
        int mas[]=new int[ll];
        int mnozh=1;
        int summa=0;
        if (len == 0) {
            for (int mn=0;mn<ll;mn++){
                mnozh=mnozh*result[mn];
            }
            System.out.println(Arrays.toString(result)+ " " + mnozh);
        } else {
            for(int i = startPosition; i <= availableBanknotes.length-len; i++) {
                result[result.length - len] = availableBanknotes[i];
                p1(availableBanknotes, len-1, i+1, result,ll);

            }
        }

    }
     */

    public static int combination(int[] array, int length, int startPosition, int[] result, int lengthResult, int sum) {
        int performing = 1;

        if (length == 0) {
            for (int i = 0; i < lengthResult; i++) {
                performing *= result[i];
            }
            System.out.println(Arrays.toString(result) + " " + performing);
            sum += performing;
        } else {
            for (int i = startPosition; i <= array.length - length; i++) {
                result[result.length - length] = array[i];
                sum = combination(array, length - 1, i + 1, result, lengthResult, sum);
            }
        }
        return sum;
    }


}
