package S386;

import java.util.ArrayList;
import java.util.List;

class Solution {
    public static void main(String[] args) {
        // 13 [1,10,11,12,13,2,3,4,5,6,7,8,9]
        System.out.println(new Solution().lexicalOrder(102));
    }

    public List<Integer> lexicalOrder(int n) {
        List<Integer> res = new ArrayList<>();
        int num = 1;

        for (int i = 0; i < n; i++) {
            res.add(num);

            if (num * 10 <= n) {
                num *= 10;
            } else {
                while (num % 10 == 9 || num + 1 > n) {
                    num /= 10;
                }
                num++;
            }
        }
        return res;
    }
}