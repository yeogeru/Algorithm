import java.io.*;
import java.util.*;

/**
 * @author : Yeogeru
 * @description : DP
 * @since : 2025-04-07
 */

public class BOJ02294_G5 {
    static int[] dp, coin;
    static int n, k;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        coin = new int[n];
        dp = new int[k + 1];
        for (int i = 0; i < n; i++) {
            coin[i] = Integer.parseInt(br.readLine());
        }
        Arrays.fill(dp, Integer.MAX_VALUE - 1);
        dp[0] = 0;

        for (int i = 0; i < n; i++) {
            for (int j = coin[i] ; j <= k ; j++) {
                dp[j] = Math.min(dp[j], dp[j - coin[i]] + 1);
            }
        }
        System.out.println(dp[k] != Integer.MAX_VALUE - 1 ? dp[k] : -1);
    }

}
