import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * @author : Yeogeru
 * @description : Dynamic Programming
 * @since : 2025-07-06
 */
public class BOJ01535_S2 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] cost = new int[n];
        int[] prise = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < n; i++) {
            cost[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < n; i++) {
            prise[i] = Integer.parseInt(st.nextToken());
        }

        int[] dp = new int[101];
        for(int i = 0 ; i < n ; i++) {
            for(int j = 100 ; j >= cost[i] ; j--) {
                dp[j] = Math.max(dp[j], dp[j - cost[i]] + prise[i]);
            }
        }

        System.out.println(dp[99]);


    }
}
