import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * @author : Yeogeru
 * @description : Dynamic Programming
 * @since : 2025-12-24
 */
public class BOJ02133_G4 {
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 0;
        if (n >= 2) dp[2] = 3;
        for (int i = 4; i <= n; i += 2) {
            dp[i] = (3 * dp[i - 2]);
            for (int j = i - 4; j >= 0; j -= 2) {
                dp[i] += (2 * dp[j]);
            }
        }

        System.out.println(dp[n]);

    }
}