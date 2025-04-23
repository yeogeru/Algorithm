import java.io.*;
import java.util.*;

/**
 * @author : Yeogeru
 * @description : Dynamic Programming
 * @since : 2025-04-23
 */

public class BOJ1520_G3 {

    static int N, M;
    static int[][] graph, dp;
    static int[] dtr = {-1, 1, 0, 0};
    static int[] dtc = {0, 0, -1, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        graph = new int[N][M];
        dp = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < M; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
            Arrays.fill(dp[i], -1);
        }

        int result = search(0, 0);

        System.out.println(result);

    }

    public static int search(int r, int c) {
        if(dp[r][c] != -1) return dp[r][c];

        dp[r][c] = 0;
        for(int d = 0 ; d < 4 ; d++) {
            int nr = r + dtr[d];
            int nc = c + dtc[d];
            if(!isInside(nr, nc)) continue;
            if(graph[r][c] <= graph[nr][nc]) continue;
            if(nr == N - 1 && nc == M - 1) ++dp[r][c];
            else dp[r][c] += search(nr, nc);
        }

        return dp[r][c];

    }

    public static boolean isInside(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < M;
    }

}
