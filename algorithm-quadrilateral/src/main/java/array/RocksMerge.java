package array;

public class RocksMerge {
    public static void main(String[] args) {


        long payment1 = 0;
        long payment2 = 0;

        for (int k = 0; k < 100; k ++) {

            int N = (int) (Math.random() * 2000) + 1000;

            int[] arr = new int[N];

            for (int i = 0; i < N; i++) {
                arr[i] = (int) (Math.random() * 100);
            }

            long s1 = System.currentTimeMillis();

            int ans1 = mergeRocks(arr);

            payment1 += System.currentTimeMillis() - s1;

            s1 = System.currentTimeMillis();

            int ans2 = mergeRocksPlus(arr);

            payment2 += System.currentTimeMillis() - s1;

            if (ans1 != ans2) {
                System.out.println("ans1 = " + ans1 + " but ans2 = " + ans2);
                return;
            }
        }

        System.out.println("++++++++ YES +++++++++");
        System.out.println("payment1 = " + payment1);
        System.out.println("payment2 = " + payment2);
    }

    public static int mergeRocks(int[] rocks) {
        if (rocks == null || rocks.length < 2) {
            throw new RuntimeException("rocks == null || rocks.length < 2");
        }
        int N = rocks.length;
        int[] sum = sumArray(rocks);

        int[][] dp = new int[N][N];

        for (int col = 1; col < N; col ++) {
            for (int row = col - 1; row >= 0; row --) {
                int cost = Integer.MAX_VALUE;
                for (int split = row; split < col; split ++) {
                    int cur = dp[row][split] + dp[split + 1][col] + range(row, col, sum);
                    cost = Math.min(cost, cur);
                }
                dp[row][col] = cost;
            }
        }
        return dp[0][N-1];
    }

    public static int mergeRocksPlus(int[] rocks) {
        if (rocks == null || rocks.length < 1) {
            throw new RuntimeException("rocks == null || rocks.length < 1");
        }
        int[] sum = sumArray(rocks);
        int N = rocks.length;
        int[][] dp = new int[N][N];
        int[][] best = new int[N][N];

        for (int col = 1; col < N; col ++) {
            for (int row = col - 1; row >= 0; row --) {
                int cost = Integer.MAX_VALUE;
                int bestSplit = -1;
                for (int split = best[row][col - 1]; split <= ((row + 1 < col) ? best[row+1][col] : col - 1); split ++) {
                    int cur = dp[row][split] + dp[split + 1][col] + range(row, col, sum);
                    if (cur <= cost) {
                        cost = cur;
                        bestSplit = split;
                    }
                }
                dp[row][col] = cost;
                best[row][col] = bestSplit;
            }
        }
        return dp[0][N-1];
    }

    public static int range(int start, int end, int[] sum) {
        if (start > end || start < 0 || end >= sum.length - 1) {
            throw new RuntimeException("输入非法 : 索引越界 或 startIndex > endIndex .");
        }
        return sum[end + 1] - sum[start];
    }

    public static int[] sumArray(int[] arr) {
        int[] sum = new int[arr.length + 1];
        for (int i = 0; i < arr.length; i ++) {
            sum[i + 1] = sum[i] + arr[i];
        }
        return sum;
    }
}
