package bankomat;

import java.util.HashMap;
import java.util.Map;

public class ATM {
    public static void main(String[] args) {
        Map<Integer, Integer> result = withdraw(1100, new int[] {500, 200});

        if (result != null) {
            for (Map.Entry<Integer, Integer> entry : result.entrySet()) {
                System.out.println("Купюр номиналом " + entry.getKey() + ": " + entry.getValue());
            }
        } else {
            System.out.println("Невозможно выдать запрошенную сумму.");
        }
    }

    public static Map<Integer, Integer> withdraw(int amount, int[] denominations) {
        Map<Integer, Integer> result = new HashMap<Integer, Integer>();

        for (int d : denominations) {
            if (amount >= d) {
                int count = amount / d;
                result.put(d, count);
                amount -= d * count;
            }
        }

        if (amount == 0) {
            return result;
        }

        result.put(500, 1);
        amount -= 500;
        Map<Integer, Integer> remainingResult = withdrawRecursive(amount, denominations, result);

        if (remainingResult != null) {
            for (Map.Entry<Integer, Integer> entry : remainingResult.entrySet()) {
                result.put(entry.getKey(), result.getOrDefault(entry.getKey(), 0) + entry.getValue());
            }

            return result;
        }

        return null;
    }

    private static Map<Integer, Integer> withdrawRecursive(int amount, int[] denominations, Map<Integer, Integer> result) {
        if (amount == 0) {
            return new HashMap<Integer, Integer>();
        }

        for (int d : denominations) {
            if (d < 500) {
                continue; // skip denominations less than 500
            }
            if (d <= amount && result.getOrDefault(d, 0) > 0) {
                result.put(d, result.get(d) - 1);
                Map<Integer, Integer> subResult = withdrawRecursive(amount - d, denominations, result);
                result.put(d, result.get(d) + 1);

                if (subResult != null) {
                    subResult.put(d, subResult.getOrDefault(d, 0) + 1);
                    return subResult;
                }
            }
        }

        return null;
    }
}