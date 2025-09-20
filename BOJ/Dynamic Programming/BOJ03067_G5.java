import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author : Yeogeru
 * @description : Dynamic Programming
 * @since : 2025-09-20
 */
public class BOJ03067_G5 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int test_case = Integer.parseInt(br.readLine());

        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        for (int t = 0; t < test_case; t++) {
            int n = Integer.parseInt(br.readLine());
            int[] coin = new int[n];
            int[] dp = new int[10001];
            st = new StringTokenizer(br.readLine(), " ");
            for (int i = 0; i < n; i++) {
                coin[i] = Integer.parseInt(st.nextToken());
            }
            dp[0] = 1;
            int goal = Integer.parseInt(br.readLine());
            for (int i = 0; i < n; i++) {
                for (int j = coin[i]; j <= goal; j++) {
                    dp[j] += dp[j - coin[i]];
                }

            }
            sb.append(dp[goal]).append("\n");
        }

        System.out.println(sb.toString());

    }


}