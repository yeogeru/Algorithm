import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * @author : Yeogeru
 * @description : Dijkstra
 * @since : 2026-03-25
 */
public class BOJ02665_G4 {
    static int n;
    static boolean[][] map;
    static int[][] data;
    static int[] dtr = {-1, 1, 0, 0};
    static int[] dtc = {0, 0, -1, 1};

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        map = new boolean[n][n];
        data = new int[n][n];

        for (int i = 0; i < n; i++) {
            char[] input = br.readLine().toCharArray();
            for (int j = 0; j < n; j++) {
                if (input[j] == '1') map[i][j] = true;
            }
            Arrays.fill(data[i], Integer.MAX_VALUE);
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1[2], o2[2]));
        pq.offer(new int[]{0, 0, 0});
        while (!pq.isEmpty()) {
            int[] temp = pq.poll();
            int r = temp[0];
            int c = temp[1];
            int cur = temp[2];
            for (int d = 0; d < 4; d++) {
                int nr = r + dtr[d];
                int nc = c + dtc[d];
                if (!inRange(nr, nc)) continue;
                int next = map[nr][nc] ? cur : cur + 1;
                if (data[nr][nc] <= cur) continue;
                data[nr][nc] = cur;
                pq.offer(new int[]{nr, nc, next});
            }
        }

        System.out.println(data[n - 1][n - 1]);

    }

    static boolean inRange(int r, int c) {
        return r >= 0 && r < n && c >= 0 && c < n;
    }


}
