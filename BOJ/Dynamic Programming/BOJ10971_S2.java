import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * @author : Yeogeru
 * @description : TSP
 * @since : 2026-04-05
 */
public class BOJ10971_S2 {
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int n = Integer.parseInt(br.readLine());
        int[][] dist = new int[n][n];
        int[][] dp = new int[1 << n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < n; j++) {
                dist[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int result = Integer.MAX_VALUE;

        Queue<int[]> que = new ArrayDeque<>();
        for (int i = 0; i < (1 << n); i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }

        que.add(new int[]{0, 1, 0});
        dp[1][0] = 0;
        while (!que.isEmpty()) {
            int[] temp = que.poll();
            int cur = temp[0];
            int visit = temp[1];
            int total = temp[2];
            if (visit == (1 << n) - 1 && dist[cur][0] != 0) {
                result = Math.min(result, total + dist[cur][0]);
                continue;
            }
            for (int next = 1; next < n; next++) {
                if (dist[cur][next] == 0) continue;
                if ((visit & (1 << next)) != 0) continue;

                int next_visit = (visit | (1 << next));
                if (dp[next_visit][next] <= total + dist[cur][next]) continue;
                dp[next_visit][next] = total + dist[cur][next];

                que.add(new int[]{next, next_visit, total + dist[cur][next]});
            }
        }

        System.out.println(result);


    }

}
