import java.io.*;
import java.util.*;

/**
 * @author : Yeogeru
 * @description : Dynamic Programming
 * @since : 2025-05-30
 */
public class BOJ05557_G5 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int[] arr = new int[n - 1];
        long[][] dp = new long[n - 1][21];
        for (int i = 0; i < n - 1; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        int dest = Integer.parseInt(st.nextToken());
        dp[0][arr[0]] = 1;
        for (int i = 1; i < n - 1; i++) {
            for (int j = 0; j <= 20; j++) {
                if (dp[i - 1][j] == 0) continue;
                int hap = j + arr[i];
                int minus = j - arr[i];
                if (hap >= 0 && hap <= 20) {
                    dp[i][hap] += dp[i - 1][j];
                }
                if (minus >= 0 && minus <= 20) {
                    dp[i][minus] += dp[i - 1][j];
                }
            }
        }
        System.out.println(dp[n - 2][dest]);
    }
}
