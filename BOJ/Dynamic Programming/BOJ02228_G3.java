import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * @author : Yeogeru
 * @description : Dynamic Programming
 * @since : 2025-09-17
 */
public class BOJ02228_G3 {
    static int n, m;
    static int[] arr;
    static int[][] dp;
    static final int K = -123456789;
    static boolean[][] visit;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        arr = new int[n];
        dp = new int[n][m];
        visit = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i], K);
            arr[i] = Integer.parseInt(br.readLine());
        }

        System.out.println(top_down(0, 0));

    }

    static int top_down(int index, int cur) {
        if (cur == m) return 0;
        if (index >= n) return K;
        if (visit[index][cur]) return dp[index][cur];
        visit[index][cur] = true;

        dp[index][cur] = top_down(index + 1, cur);
        int temp = 0;
        for (int i = index; i < n; i++) {
            temp += arr[i];
            dp[index][cur] = Math.max(dp[index][cur], top_down(i + 2, cur + 1) + temp);
        }

        return dp[index][cur];

    }


}