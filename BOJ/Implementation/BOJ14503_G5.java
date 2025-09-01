import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author : Yeogeru
 * @description : Simulation
 * @since : 2025-09-01
 */
public class BOJ14503_G5 {
    static int[] dtr = {-1, 0, 1, 0};
    static int[] dtc = {0, 1, 0, -1};
    static int n, m;
    static int[][] map;
    static int result = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m];

        st = new StringTokenizer(br.readLine(), " ");
        int str = Integer.parseInt(st.nextToken());
        int stc = Integer.parseInt(st.nextToken());
        int std = Integer.parseInt(st.nextToken());
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        play(str, stc, std);

        System.out.println(result);

    }

    static void play(int r, int c, int d) {
        boolean isWorking = true;
        while (isWorking) {
            if (map[r][c] == 0) {
                ++result;
                map[r][c] = -1;
            }
            boolean cleaned = false;
            for (int i = 0; i < 4; i++) {
                d = (d + 3) % 4;
                int nr = r + dtr[d];
                int nc = c + dtc[d];
                if (!inRange(nr, nc)) continue;
                if (map[nr][nc] == 0) {
                    r = nr;
                    c = nc;
                    cleaned = true;
                    break;
                }
            }

            if (!cleaned) {
                int nr = r - dtr[d];
                int nc = c - dtc[d];
                if (!inRange(nr, nc)) {
                    isWorking = false;
                    continue;
                }
                if (map[nr][nc] > 0) isWorking = false;
                r = nr;
                c = nc;
            }

        }
    }

    static boolean inRange(int r, int c) {
        return r < n && r >= 0 && c < m && c >= 0;
    }

}
