package array;

public class SplitArray {

    public static void main(String[] args) {

        long payment1 = 0;
        long payment2 = 0;

        for (int k = 0; k < 90000; k ++) {

            int N = (int) (Math.random() * 10000) + 1000;

            int[] arr = new int[N];

            for (int i = 0; i < N; i++) {
                arr[i] = (int) (Math.random() * 100);
            }

            long s1 = System.currentTimeMillis();

            int ans1 = splitArraySimp(arr);

            payment1 += System.currentTimeMillis() - s1;

            s1 = System.currentTimeMillis();

            int ans2 = splitArray(arr);

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

    public static int splitArray(int[] arr) {
        if (arr == null) {
            throw new RuntimeException("数组为空");
        }
        int[] sum = new int[arr.length + 1];
        for (int i = 0; i < arr.length; i ++) {
            sum[i + 1] = sum[i] + arr[i];
        }

        int max = Integer.MIN_VALUE;
        int split = 0; // [0 ... split] 为左边部分，[split + 1, arr.length - 1] 为右边部分
        while (split < arr.length - 1) {

            int left = sum[split + 1];
            int right = sum[arr.length] - left;
            int cur = Math.min(left, right);
            if (cur < max) {
                break;
            }
            max = cur;
            split ++;
        }
        return max;
    }

    public static int splitArraySimp(int[] arr) {
        if (arr == null) {
            throw new RuntimeException("数组为空");
        }
        int[] sum = new int[arr.length + 1];
        for (int i = 0; i < arr.length; i ++) {
            sum[i + 1] = sum[i] + arr[i];
        }

        int max = Integer.MIN_VALUE;
        int split = 0; // [0 ... split] 为左边部分，[split + 1, arr.length - 1] 为右边部分
        while (split < arr.length - 1) {

            int left = sum[split + 1];
            int right = sum[arr.length] - left;
            max = Math.max(Math.min(left, right), max);
            split ++;
        }
        return max;
    }

}
