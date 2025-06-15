import java.io.*;
import java.util.*;

/**
 * @author : Yeogeru
 * @description : Dynamic Programming
 * @since : 2025-06-15
 */
public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[][] dp = new int[10001][4];
        dp[1][1] = 1;
        dp[2][1] = 1;
        dp[2][2] = 1;
        dp[3][1] = 1;
        dp[3][2] = 1;
        dp[3][3] = 1;
        for (int i = 4; i <= 10000; i++) {
            dp[i][1] += dp[i - 1][1];
            dp[i][2] += dp[i - 2][1] + dp[i - 2][2];
            dp[i][3] += dp[i - 3][1] + dp[i - 3][2] + dp[i - 3][3];
        }

        int test_case = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int t = 1; t <= test_case; t++) {
            int n = Integer.parseInt(br.readLine());
            int sum = dp[n][1] + dp[n][2] + dp[n][3];
            sb.append(sum);
            if (t < test_case) sb.append("\n");
        }
        System.out.println(sb);
    }

}
