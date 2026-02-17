import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author : Yeogeru
 * @description : Dynamic Programming
 * @since : 2026-02-18
 */
public class BOJ01932_S1 {
    static int n;
    static int[][] triangle, dp;

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());
        triangle = new int[n + 1][n];
        dp = new int[n + 1][n];
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < i; j++) {
                triangle[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int max = 0;
        dp[1][0] = triangle[1][0];
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < i; j++) {
                if (j < i - 1) dp[i][j] = Math.max(dp[i - 1][j] + triangle[i][j], dp[i][j]);
                if (j > 0) dp[i][j] = Math.max(dp[i - 1][j - 1] + triangle[i][j], dp[i][j]);
                if (i == n) max = Math.max(max, dp[i][j]);
            }
        }

        System.out.println(max);


    }
}