import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * @author : Yeogeru
 * @description : Dynamic Programming
 * @since : 2025-07-12
 */
public class BOJ11049_G3 {
    static int n;
    static int[][] arr, dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());
        arr = new int[n + 1][2];
        dp = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }

        System.out.println(calc(1, n));
    }

    static int calc(int start, int end) {
        if (start == end) return 0;
        if (dp[start][end] != Integer.MAX_VALUE) return dp[start][end];
        for (int i = start; i < end; i++) {
            int t = calc(start, i) + calc(i + 1, end) + (arr[start][0] * arr[i][1] * arr[end][1]);
            dp[start][end] = Math.min(dp[start][end], t);
        }
        return dp[start][end];
    }

}
