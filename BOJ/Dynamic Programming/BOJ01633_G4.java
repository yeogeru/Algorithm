import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * @author : Yeogeru
 * @description : Dynamic Programming
 * @since : 2025-06-14
 */
public class BOJ01633_G4 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        String input = br.readLine();
        List<int[]> list = new ArrayList<>();
        while (!input.isEmpty()) {
            st = new StringTokenizer(input, " ");
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            list.add(new int[]{a, b});
            input = br.readLine();
            if (input == null) break;
        }
        
        int n = list.size();

        int[][][] dp = new int[n + 1][16][16];
        dp[0][1][0] = list.get(0)[0];
        dp[0][0][1] = list.get(0)[1];

        int max = 0;
        for (int i = 1; i < n; i++) {
            for (int j = 0; j <= 15; j++) {
                for (int k = 0; k <= 15; k++) {
                    int white = 0;
                    int black = 0;
                    if (j > 0) {
                        white = dp[i - 1][j - 1][k] + list.get(i)[0];
                    }
                    if (k > 0) {
                        black = dp[i - 1][j][k - 1] + list.get(i)[1];
                    }
                    dp[i][j][k] = Math.max(dp[i - 1][j][k], Math.max(white, black));

                    if (j == 15 && k == 15) {
                        max = Math.max(max, dp[i][j][k]);
                    }
                }
            }
        }

        System.out.println(max);
    }

}
