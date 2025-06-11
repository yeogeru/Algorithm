import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * @author : Yeogeru
 * @description : Dynamic Programming
 * @since : 2025-06-11
 */
public class BOJ14852_G4 {
    static final int MOD = 1_000_000_007;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        long[][] dp = new long[n + 2][2];
        dp[1][0] = 2;
        dp[2][0] = 7;
        dp[2][1] = 1;
        for (int i = 3; i <= n; i++) {
            dp[i][1] = (dp[i - 1][1] + dp[i - 3][0]) % MOD;
            dp[i][0] = ((2 * dp[i][1]) + (2 * dp[i - 1][0]) + (3 * dp[i - 2][0])) % MOD;
        }
        System.out.println(dp[n][0]);
    }
}
