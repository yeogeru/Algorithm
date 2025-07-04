import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * @author : Yeogeru
 * @description : Dynamic Programming
 * @since : 2025-07-03
 */
public class BOJ02073_G4 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int D = Integer.parseInt(st.nextToken());
        int P = Integer.parseInt(st.nextToken());
        int[] leng = new int[P + 1];
        int[] cap = new int[P + 1];
        for (int i = 1; i <= P; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            leng[i] = Integer.parseInt(st.nextToken());
            cap[i] = Integer.parseInt(st.nextToken());
        }
        int[][] dp = new int[D + 1][P + 1];
        Arrays.fill(dp[0], Integer.MAX_VALUE);

        for (int i = 1; i <= D; i++) {
            for (int j = 1; j <= P; j++) {
                if (i < leng[j]) {
                    dp[i][j] = Math.max(dp[i][j], dp[i][j - 1]);
                    continue;
                }
                dp[i][j] = Math.max(dp[i][j - 1], Math.min(dp[i - leng[j]][j - 1], cap[j]));
            }
        }

        System.out.println(dp[D][P]);

    }
}
