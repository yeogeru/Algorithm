import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * @author : Yeogeru
 * @description : Dynamic Programming
 * @since : 2025-06-09
 */
public class BOJ01660_G5 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[121];
        arr[0] = 1;
        int sum = 1;
        for (int i = 1, j = 2; arr[i - 1] <= 300000; i++, j++) {
            sum += j;
            arr[i] = arr[i - 1] + sum;
        }

        int[] dp = new int[n + 1]; // index = 대포수, value = 전부 사용해 만들수 있는 최소 개수
        Arrays.fill(dp, 1_000_000);
        dp[0] = 0;

        for (int i = 1; i <= n; i++) {
            for (int j = 0; arr[j] <= i; j++) {
                dp[i] = Math.min(dp[i], dp[i - arr[j]] + 1);
            }
        }

        System.out.println(dp[n]);

    }
}
