import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author : Yeogeru
 * @description : Dynamic Programming
 * @since : 2025-06-23
 */
public class BOJ02225_G5 {
    static final int MOD = 1_000_000_000;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[][] dp = new int[n + 1][k + 1];
        for (int i = 1; i <= k; i++) {
            dp[1][i] = i;
        }

        for (int i = 2; i <= n; i++) {
            dp[i][1] = 1;
            for (int j = 2; j <= k; j++) {
                dp[i][j] = (dp[i][j] + dp[i - 1][j]) % MOD;
                dp[i][j] = (dp[i][j] + dp[i][j - 1]) % MOD;
            }
        }

        System.out.println(dp[n][k]);

    }

}
