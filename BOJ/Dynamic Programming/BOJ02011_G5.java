import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * @author : Yeogeru
 * @description : Dynamic Programming
 * @since : 2025-06-08
 */
public class BOJ02011_G5 {
    static final int MOD = 1_000_000;
    static int[] dp;
    static char[] input;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        input = br.readLine().toCharArray();
        dp = new int[input.length + 1];
        dp[0] = 1;
        for (int i = 1; i < dp.length; i++) {
            int cur = input[i - 1] - '0';
            if (cur != 0) {
                dp[i] += dp[i - 1];
                dp[i] %= MOD;
            }
            if (i == 1) continue;

            int last = input[i - 2] - '0';
            int t = last * 10 + cur;
            if(last == 0) continue;
            if (t >= 1 && t <= 26) {
                dp[i] += dp[i - 2];
                dp[i] %= MOD;
            }
        }

        System.out.println(dp[input.length]);

    }

}
