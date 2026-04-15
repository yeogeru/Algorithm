import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * @author : Yeogeru
 * @description : BFS
 * @since : 2026-04-05
 */
public class BOJ02206_G3 {
    static int n, m;
    static boolean[][] map;
    static boolean[][][] visit;
    static int[] dtr = {1, -1, 0, 0};
    static int[] dtc = {0, 0, -1, 1};

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            char[] input = br.readLine().toCharArray();
            for (int j = 0; j < m; j++) {
                map[i][j] = input[j] == '0';
            }
        }

        visit = new boolean[n][m][2];

        Queue<int[]> que = new ArrayDeque<>();
        // 0 : r, 1 : c, 2 : dist, 3 : break

        que.add(new int[]{0, 0, 1, 0});
        visit[0][0][0] = true;
        int result = -1;

        while (!que.isEmpty()) {
            int[] temp = que.poll();
            int r = temp[0];
            int c = temp[1];
            int dist = temp[2];
            int isBreak = temp[3];
            if (r == n - 1 && c == m - 1) {
                result = dist;
                que.clear();
                break;
            }
            for (int d = 0; d < 4; d++) {
                int nr = r + dtr[d];
                int nc = c + dtc[d];
                if (!inRange(nr, nc)) continue;
                if (isBreak == 1 && !map[nr][nc]) continue;
                int next_break = isBreak;
                if (isBreak == 0 && !map[nr][nc]) next_break = 1;
                if (visit[nr][nc][next_break]) continue;
                visit[nr][nc][next_break] = true;
                que.add(new int[]{nr, nc, dist + 1, next_break});

            }

        }

        System.out.println(result);


    }

    static boolean inRange(int r, int c) {
        return r >= 0 && r < n && c >= 0 && c < m;
    }

}
