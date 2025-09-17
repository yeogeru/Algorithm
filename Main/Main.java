import java.io.*;
import java.util.*;

/**
 * @author : Yeogeru
 * @description : Dynamic Programming
 * @since : 2025-09-17
 */
public class Main {
    static int n, m;
    static int[] arr;
    static int[][] dp;
    static final int K = -123456789;
    static boolean[][] visit;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        arr = new int[n + 1];
        dp = new int[n + 1][m + 1];
        visit = new boolean[n + 1][m + 1];

        for (int i = 1; i <= n; i++) {
            arr[i] = arr[i - 1] + Integer.parseInt(br.readLine());
        }

        for (int i = 0; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                dp[i][j] = K;
            }
        }

        System.out.println(bottom_up());

    }

    static int bottom_up() {
        dp[1][1] = arr[1];
        for (int i = 2; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                dp[i][j] = dp[i - 1][j];
                if (j == 1) dp[i][j] = Math.max(dp[i][j], arr[i]);
                for (int k = 0; k <= i - 2; k++) {
                    dp[i][j] = Math.max(dp[i][j], dp[k][j - 1] + arr[i] - arr[k + 1]);
                }
            }
        }

        return dp[n][m];

    }


    static int top_down(int index, int cur) {
        if (cur > m) return 0;
        if (index > n) return K;
        if (visit[index][cur]) return dp[index][cur];
        visit[index][cur] = true;

        dp[index][cur] = top_down(index + 1, cur);
        for (int i = index; i <= n; i++) {
            dp[index][cur] = Math.max(dp[index][cur], top_down(i + 2, cur + 1) + arr[i] - arr[index - 1]);
        }

        return dp[index][cur];

    }


}