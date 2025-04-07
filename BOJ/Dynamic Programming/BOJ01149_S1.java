import java.io.*;
import java.util.*;

/**
 * @author : Yeogeru
 * @description : DP
 * @since : 2025-04-07
 */
public class BOJ01149_S1 {
    static int n;
    static int[][] dp;
    static int[][] cost;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        StringTokenizer st;
        cost = new int[n][3];
        dp = new int[n][3];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            cost[i][0] = Integer.parseInt(st.nextToken());
            cost[i][1] = Integer.parseInt(st.nextToken());
            cost[i][2] = Integer.parseInt(st.nextToken());
        }

        System.out.println(calc(0, -1));
    }

    public static int calc(int cur, int prev_color) {
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < 3; i++) {
            if (prev_color == i) continue;
            if (cur + 1 == n) {
                min = Math.min(min, cost[cur][i]);
                continue;
            }
            if (dp[cur][i] == 0) dp[cur][i] = cost[cur][i] + calc(cur + 1, i);
            min = Math.min(min, dp[cur][i]);
        }
        return min;
    }

}
