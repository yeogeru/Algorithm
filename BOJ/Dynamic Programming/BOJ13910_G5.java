import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * @author : Yeogeru
 * @description : Dynamic Programming
 * @since : 2025-06-17
 */
public class BOJ13910_G5 {
    static final int K = 100_000_000;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[] dp = new int[n + 1];
        Arrays.fill(dp, K);
        dp[0] = 0;

        int[] arr = new int[m];
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < m; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            dp[arr[i]] = 1;
        }

        for (int i = 0; i < m; i++) {
            for (int j = i + 1; j < m; j++) {
                if (arr[i] + arr[j] > n) continue;
                dp[arr[i] + arr[j]] = 1;
            }
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j < i; j++) {
                dp[i] = Math.min(dp[i], dp[i - j] + dp[j]);
            }
        }

        if (dp[n] == K) dp[n] = -1;

        System.out.println(dp[n]);

    }

}
