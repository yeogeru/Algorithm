import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author : Yeogeru
 * @description : Dynamic Programming
 * @since : 2025-06-26
 */
public class BOJ20181_G2 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int[] feed = new int[n + 1];
        long[] dp = new long[n + 1];
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 1; i <= n; i++) {
            feed[i] = Integer.parseInt(st.nextToken());
        }

        long sum = 0;
        int left = 0;
        int right = 0;
        while (++right <= n) {
            sum += feed[right];
            dp[right] = dp[right - 1];
            while (sum >= k) {
                dp[right] = Math.max(dp[right], dp[left] + sum - k);
                sum -= feed[++left];
            }
        }

        System.out.println(dp[n]);

    }
}
