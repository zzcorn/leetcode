package S726;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

class Solution {

    public static void main(String[] args) {
        String formula = "K4(ON(SO3)2)2";
        System.out.println(new Solution().countOfAtoms(formula));
    }

    public String countOfAtoms(String formula) {
        Result res = parse(formula, 0);

        StringBuilder ans = new StringBuilder();
        TreeMap<String, Long> sorted = new TreeMap<>(res.map);

        for (Map.Entry<String, Long> entry : sorted.entrySet()) {
            ans.append(entry.getKey());
            if (entry.getValue() > 1) {
                ans.append(entry.getValue());
            }
        }

        return ans.toString();
    }

    private Result parse(String formula, int index) {
        Map<String, Long> count = new HashMap<>();
        int n = formula.length();

        while (index < n && formula.charAt(index) != ')') {

            char c = formula.charAt(index);

            if (c == '(') {
                Result sub = parse(formula, index + 1);
                index = sub.index;

                int start = index;
                while (index < n && Character.isDigit(formula.charAt(index))) {
                    index++;
                }
                int multiplier = start < index ? Integer.parseInt(formula.substring(start, index)) : 1;

                for (Map.Entry<String, Long> e : sub.map.entrySet()) {
                    count.put(e.getKey(), count.getOrDefault(e.getKey(), 0L) + e.getValue() * multiplier);
                }
            } else {
                int start = index++;
                while (index < n && Character.isLowerCase(formula.charAt(index))) {
                    index++;
                }
                String name = formula.substring(start, index);

                start = index;
                while (index < n && Character.isDigit(formula.charAt(index))) {
                    index++;
                }
                int num = start < index ? Integer.parseInt(formula.substring(start, index)) : 1;

                count.put(name, count.getOrDefault(name, 0L) + num);
            }
        }

        // 跳过 ')'
        index++;

        return new Result(count, index);
    }

    private static class Result {
        Map<String, Long> map;
        int index;

        Result(Map<String, Long> map, int index) {
            this.map = map;
            this.index = index;
        }
    }
}