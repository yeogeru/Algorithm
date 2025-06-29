import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * @author : Yeogeru
 * @description : Dynamic Programming
 * @since : 2025-06-29
 */
public class BOJ02758_G4 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int test_case = Integer.parseInt(br.readLine());

        long[][] dp = new long[11][2001];
        Arrays.fill(dp[1], 1);
        for (int i = 2; i <= 10; i++) {
            for (int j = 1; j <= 2000; j++) {
                for (int k = j * 2; k <= 2000; k++) {
                    dp[i][k] += dp[i - 1][j];
                }
            }
        }

        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        for (int t = 1; t <= test_case; t++) {
            st = new StringTokenizer(br.readLine(), " ");
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            long result = 0;
            for (int i = 1; i <= m; i++) {
                result += dp[n][i];
            }
            sb.append(result);
            if (t < test_case) sb.append("\n");
        }

        System.out.println(sb.toString());
    }
}
