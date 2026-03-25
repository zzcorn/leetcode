package S1849;

public class Solution2 {

    public static void main(String[] args) {
        System.out.println(new Solution().splitString("050043"));
    }

    public boolean splitString(String s) {
        return dfs(s, 0, -1, 0);
    }

    /**
     * @param s 原字符串
     * @param index 当前处理到的位置
     * @param prev 上一个数字（-1表示还没选）
     * @param count 已经分了几段
     */
    private boolean dfs(String s, int index, long prev, int count) {
        // ✅ 结束条件
        if (index == s.length()) {
            return count >= 2;
        }

        long cur = 0;

        // 枚举下一段的长度
        for (int i = index; i < s.length(); i++) {
            cur = cur * 10 + (s.charAt(i) - '0');

            // 🔥 剪枝1：防止数过大（可选）
            if (cur > 1e10) break;

            // 第一段可以随便选
            if (prev == -1) {
                if (dfs(s, i + 1, cur, count + 1)) {
                    return true;
                }
            } else {
                // 必须满足递减1
                if (cur == prev - 1) {
                    if (dfs(s, i + 1, cur, count + 1)) {
                        return true;
                    }
                }

                // 🔥 剪枝2：已经超过了，没必要继续
                if (cur >= prev) break;
            }
        }

        return false;
    }
}
