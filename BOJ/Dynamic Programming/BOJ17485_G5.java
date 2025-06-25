import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * @author : Yeogeru
 * @description : Dynamic Programming
 * @since : 2025-06-25
 */
public class BOJ17485_G5 {
    static int[] dt = {-1, 0, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[][] map = new int[n + 1][m];
        int[][][] dp = new int[3][n + 1][m];
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            Arrays.fill(dp[0][i], 1_000_000_000);
            Arrays.fill(dp[1][i], 1_000_000_000);
            Arrays.fill(dp[2][i], 1_000_000_000);
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }


        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < m; j++) {
                for (int d = 0; d < 3; d++) {
                    int pd = (d + 1) % 3;
                    int prev = dt[pd] + j;
                    if (prev >= 0 && prev < m) {
                        dp[d][i][j] = Math.min(dp[d][i][j], dp[pd][i - 1][prev] + map[i][j]);
                    }
                    pd = (d + 2) % 3;
                    prev = dt[pd] + j;
                    if (prev >= 0 && prev < m) {
                        dp[d][i][j] = Math.min(dp[d][i][j], dp[pd][i - 1][prev] + map[i][j]);
                    }
                }
            }
        }

        int result = Integer.MAX_VALUE;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < 3; j++) {
                result = Math.min(result, dp[j][n][i]);
            }
        }

        System.out.println(result);

    }

}
