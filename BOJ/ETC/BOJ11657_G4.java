import java.util.*;
import java.io.*;

/**
 * @author : Yeogeru
 * @description : Bellman-Ford
 * @since : 2025-12-31
 */
public class BOJ11657_G4 {
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[][] edges = new int[m][3];
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            edges[i][0] = Integer.parseInt(st.nextToken());
            edges[i][1] = Integer.parseInt(st.nextToken());
            edges[i][2] = Integer.parseInt(st.nextToken());
        }
        long[] result = new long[n + 1];
        Arrays.fill(result, Integer.MAX_VALUE);
        result[1] = 0;
        boolean loop = false;
        for(int i = 1 ; i <= n ; i++) {
            for(int j = 0 ; j < m ; j++) {
                int from = edges[j][0];
                int to = edges[j][1];
                int cost = edges[j][2];
                if(result[from] != Integer.MAX_VALUE && result[to] > result[from] + cost) {
                    result[to] = result[from] + cost;
                    if(i == n) loop = true;
                }
            }
        }

        if (loop) System.out.println(-1);
        else {
            StringBuilder sb = new StringBuilder();
            for (int i = 2; i <= n; i++) {
                sb.append(result[i] == Integer.MAX_VALUE ? -1 : result[i]);
                if (i != n) sb.append("\n");
            }
            System.out.println(sb);
        }

    }
}