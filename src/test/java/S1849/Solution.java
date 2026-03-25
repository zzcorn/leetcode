package S1849;

class Solution {
    static final double INF = 1E10;

    public static void main(String[] args) {
        System.out.println(new Solution().splitString("543"));
    }

    public boolean splitString(String s) {
        int n = s.length();
        long start = 0;

        char[] charArray = s.toCharArray();
        for(int i = 0; i < n - 1 && start < INF; i++) {
            start = start * 10 + (charArray[i] - '0');
            long pval = start;
            long cval = 0;
            int cidx = i+1;
            for(int j = i + 1; j < n && cval < INF; j++) {
                if(pval == 1) {
                    boolean allZero = true;
                    for(int k = cidx; k < n; k++) {
                        if(charArray[k] != '0') {
                            allZero = false;
                        }
                    }
                    if(allZero) {
                        return true;
                    } else {
                        break;
                    }
                }
                cval = cval * 10 + (charArray[j] - '0');
                if(cval > pval - 1) {
                    break;
                } else if (cval == pval - 1) {
                    if(j + 1 == n) {
                        return true;
                    }
                    pval = cval;
                    cval = 0;
                    cidx = j + 1;
                }
            }
        }
        return false;
    }
}
