package S679;

import java.util.ArrayList;
import java.util.List;

public class Solution {

    private static final double EPSILON = 1e-6;

    public static void main(String[] args) {
        // true
        System.out.println(new Solution().judgePoint24(new int[]{4, 1, 8, 7}));
        // false
        System.out.println(new Solution().judgePoint24(new int[]{1, 2, 1, 2}));
    }

    public boolean judgePoint24(int[] cards) {
        List<Double> list = new ArrayList<>();
        for (int card : cards) {
            list.add((double) card);
        }
        return backtrack(list);
    }

    public boolean backtrack(List<Double> list) {
        if (list.size() == 1) {
            return Math.abs(list.get(0) - 24) < EPSILON;
        }
        for (int i = 0; i < list.size(); i++) {
            for (int j = i + 1; j < list.size(); j++) {
                double a = list.get(i);
                double b = list.get(j);
                List<Double> nextList = new ArrayList<>();
                for (int k = 0; k < list.size(); k++) {
                    if (k != i && k != j) {
                        nextList.add(list.get(k));
                    }
                }

                double[] result = new double[]{a + b, a - b, b - a, a * b, a / b, b / a};
                for (int k = 0; k < result.length; k++) {
                    if (k == 4 && b == 0) {
                        continue;
                    }
                    if (k == 5 && a == 0) {
                        continue;
                    }
                    nextList.add(result[k]);
                    if (backtrack(nextList)) {
                        return true;
                    }
                    nextList.remove(nextList.size() - 1);
                }
            }
        }
        return false;
    }
}
