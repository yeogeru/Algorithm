import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * @author : Yeogeru
 * @description : Implementation
 * @since : 2025-08-13
 */
public class BOJ15683_G3 {
    static Map<Integer, int[]> map = new HashMap<>();
    static int n, m;
    static int[][] graph;
    static int[] dtr = {-1, 0, 1, 0};
    static int[] dtc = {0, 1, 0, -1};
    static int min = Integer.MAX_VALUE;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        graph = new int[n][m];
        int count = 0;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < m; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
                if (graph[i][j] > 0 && graph[i][j] < 6) map.put(count++, new int[]{i, j});
            }
        }

        calc(0);

        System.out.println(min);

    }

    static void calc(int index) {
        if (index == map.size()) {
            int total = 0;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (graph[i][j] == 0) ++total;
                }
            }
            min = Math.min(min, total);
            return;
        }
        int[] cur = map.get(index);
        int r = cur[0];
        int c = cur[1];
        switch (graph[cur[0]][cur[1]]) {
            case 1:
                for (int d = 0; d < 4; d++) {
                    r = cur[0] + dtr[d];
                    c = cur[1] + dtc[d];
                    observer(r, c, d, -1);
                    calc(index + 1);
                    observer(r, c, d, 1);
                }
                break;
            case 2:
                for (int d = 0; d < 2; d++) {
                    observer(r + dtr[d], c + dtc[d], d, -1);
                    observer(r + dtr[d + 2], c + dtc[d + 2], d + 2, -1);
                    calc(index + 1);
                    observer(r + dtr[d], c + dtc[d], d, 1);
                    observer(r + dtr[d + 2], c + dtc[d + 2], d + 2, 1);
                }
                break;
            case 3:
                for (int d = 0; d < 4; d++) {
                    observer(r + dtr[d], c + dtc[d], d, -1);
                    observer(r + dtr[(d + 1) % 4], c + dtc[(d + 1) % 4], (d + 1) % 4, -1);
                    calc(index + 1);
                    observer(r + dtr[d], c + dtc[d], d, 1);
                    observer(r + dtr[(d + 1) % 4], c + dtc[(d + 1) % 4], (d + 1) % 4, 1);
                }
                break;
            case 4:
                for (int d = 0; d < 4; d++) {
                    observer(r + dtr[d], c + dtc[d], d, -1);
                    observer(r + dtr[(d + 1) % 4], c + dtc[(d + 1) % 4], (d + 1) % 4, -1);
                    observer(r + dtr[(d + 2) % 4], c + dtc[(d + 2) % 4], (d + 2) % 4, -1);
                    calc(index + 1);
                    observer(r + dtr[d], c + dtc[d], d, 1);
                    observer(r + dtr[(d + 1) % 4], c + dtc[(d + 1) % 4], (d + 1) % 4, 1);
                    observer(r + dtr[(d + 2) % 4], c + dtc[(d + 2) % 4], (d + 2) % 4, 1);
                }
                break;
            case 5:
                for (int d = 0; d < 4; d++) {
                    observer(r + dtr[d], c + dtc[d], d, -1);
                }
                calc(index + 1);
                for (int d = 0; d < 4; d++) {
                    observer(r + dtr[d], c + dtc[d], d, 1);
                }
                break;
        }

    }

    static void observer(int r, int c, int d, int type) {
        while (inRange(r, c)) {
            if (graph[r][c] == 6) break;
            if (graph[r][c] <= 0) graph[r][c] += type;
            r += dtr[d];
            c += dtc[d];
        }
    }

    static boolean inRange(int r, int c) {
        return r >= 0 && r < n && c >= 0 && c < m;
    }

}
