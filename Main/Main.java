import java.io.*;
import java.util.*;

/**
 * @author : Yeogeru
 * @description : Dynamic Programming
 * @since : 2025-06-05
 */
public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int c = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());
        int[] cost = new int[n];
        int[] gain = new int[n];
        int[] dp = new int[2001];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            cost[i] = Integer.parseInt(st.nextToken());
            gain[i] = Integer.parseInt(st.nextToken());
            dp[gain[i]] = cost[i];
        }
        Arrays.fill(dp, 100_000_000);
        dp[0] = 0;
        for (int i = 0; i < n; i++) {
            for (int j = gain[i]; j <= 2000; j++) {
                dp[j] = Math.min(dp[j], cost[i] + dp[j - gain[i]]);
            }
        }

        int result = Integer.MAX_VALUE;
        for (int i = c; i <= 2000; i++) {
            result = Math.min(result, dp[i]);
        }

        System.out.println(result);

    }
}
