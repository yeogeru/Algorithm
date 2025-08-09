import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * @author : Yeogeru
 * @description : Dijkstra
 * @since : 2025-08-09
 */
public class BOJ04485_G4 {
    static int n;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        n = Integer.parseInt(br.readLine());
        int[] dtr = {-1, 1, 0, 0};
        int[] dtc = {0, 0, -1, 1};
        int test_case = 0;
        while (n > 0) {
            int[][] map = new int[n][n];
            int[][] cost = new int[n][n];
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < n; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
                Arrays.fill(cost[i], Integer.MAX_VALUE);
            }
            cost[0][0] = map[0][0];

            PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1[2], o2[2]));
            pq.add(new int[]{0, 0, map[0][0]});
            while (!pq.isEmpty()) {
                int[] t = pq.poll();
                int r = t[0];
                int c = t[1];
                int cur = t[2];
                for (int d = 0; d < 4; d++) {
                    int dr = r + dtr[d];
                    int dc = c + dtc[d];
                    if (!inRange(dr, dc)) continue;
                    if (cost[dr][dc] > cur + map[dr][dc]) {
                        cost[dr][dc] = cur + map[dr][dc];
                        pq.add(new int[]{dr, dc, cost[dr][dc]});
                    }
                }
            }

            sb.append("Problem ").append(++test_case).append(": ").append(cost[n - 1][n - 1]);

            n = Integer.parseInt(br.readLine());
            if (n > 0) sb.append("\n");
        }

        System.out.println(sb);


    }

    static boolean inRange(int r, int c) {
        return r >= 0 && r < n && c >= 0 && c < n;
    }


}
