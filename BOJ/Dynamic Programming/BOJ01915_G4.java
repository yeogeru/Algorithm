import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author : Yeogeru
 * @description : Dynamic Programming
 * @since : 2025-07-15
 */
public class BOJ01915_G4 {
    static int n, m, max = 0;
    static boolean[][] graph;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        graph = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            char[] input = br.readLine().toCharArray();
            for (int j = 0; j < m; j++) {
                if (input[j] == '1') {
                    graph[i][j] = true;
                    max = 1;
                }
            }
        }
        
        int[][] dp = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (!graph[i][j]) continue;
                dp[i][j] = 1;
                if (i - 1 < 0 || j - 1 < 0) continue;
                int point = dp[i - 1][j - 1];
                int top = 0;
                int left = 0;
                while (top++ < point && left++ < point) {
                    if (i - top < 0 || j - left < 0) break;
                    if (!graph[i - top][j] || !graph[i][j - left]) break;
                    ++dp[i][j];
                }

                max = Math.max(max, dp[i][j]);
            }
        }

        System.out.println(max * max);

    }

}
