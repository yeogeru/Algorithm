import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author : Yeogeru
 * @description : Dynamic Programming
 * @since : 2025-06-13
 */
public class BOJ14722_G4 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st;
        int[][] graph = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 1; j <= n; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[][][] dp = new int[n + 1][n + 1][3];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                for (int k = 0; k < 3; k++) {
                    int prev = (k + 2) % 3;
                    boolean drink = true;
                    if (graph[i][j] > 0) drink = dp[i][j][prev] > 0;
                    if (graph[i][j] == k && drink) {
                        dp[i][j][k] = Math.max(dp[i - 1][j][prev] + 1, dp[i][j - 1][prev] + 1);
                        continue;
                    }
                    dp[i][j][k] = Math.max(dp[i - 1][j][k], dp[i][j - 1][k]);
                }
            }
        }

        int max = 0;
        for (int i = 0; i < 3; i++) {
            max = Math.max(max, dp[n][n][i]);
        }

        System.out.println(max);

    }
}
