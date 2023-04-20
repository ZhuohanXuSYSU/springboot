package array;

public class SplitArrayPlus {

    public static void main(String[] args) {

        int K = 90000;

        long pay1 = 0;
        long pay2 = 0;


        for (int k = 0; k < K; k ++) {

            int N = (int) (Math.random() * 6000) + 5000;
            int[] arr = new int[N];
            for (int i = 0; i < N; i ++) {
                arr[i] = (int) (Math.random() * 100);
            }

            long s = System.currentTimeMillis();

            int[] ans1 = splitArraySimp(arr);

            pay1 = System.currentTimeMillis() - s;

            s = System.currentTimeMillis();

            int[] ans2 = splitArrayPlus(arr);

            pay2 = System.currentTimeMillis() - s;

            for (int i = 0; i < arr.length; i ++) {
                if (ans1[i] != ans2[i]) {
                    System.out.println("ans1 = " + ans1[i] + " but ans2 = " + ans2[i]);
                    return;
                }
            }
        }
        System.out.println("++++++ Yes ++++++");
        System.out.println("pay1 = " + pay1 + "    pay2 = " + pay2);
    }

    public static int[] splitArrayPlus(int[] arr) {
        if (arr == null || arr.length < 1) {
            throw new RuntimeException("数组为空!");
        }

        int[] sum = new int[arr.length + 1];
        for (int i = 0; i < arr.length; i ++) {
            sum[i + 1] = sum[i] + arr[i];
        }

        int[] dp = new int[arr.length];
        int[] best = new int[arr.length];

        for (int i = 1; i < dp.length; i ++) {
            int max = Integer.MIN_VALUE;
            int split;
            for (split = best[i-1]; split < i; split ++) {
                int cur = Math.min(sum[split + 1], sum[i + 1] - sum[split + 1]);
                if (cur >= max) {
                    max = cur;
                } else {
                    break;
                }
            }
            best[i] = split - 1;
            dp[i] = max;
        }

        return dp;
    }

    public static int[] splitArraySimp(int[] arr) {
        if (arr == null || arr.length < 1) {
            throw new RuntimeException("arr is null || arr.length < 1");
        }

        int[] sum = new int[arr.length + 1];
        for (int i = 0; i < arr.length; i ++) {
            sum[i + 1] = sum[i] + arr[i];
        }

        int[] ans = new int[arr.length];
        for (int i = 1; i < arr.length; i ++) {
            int max = Integer.MIN_VALUE;
            for (int split = 0; split < i; split ++) {
                int cur = Math.min(sum[split + 1], sum[i + 1] - sum[split + 1]);
                max = Math.max(max, cur);
            }
            ans[i] = max;
        }
        return ans;
    }

}
