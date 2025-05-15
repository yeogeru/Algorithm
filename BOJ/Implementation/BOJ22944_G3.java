import java.io.*;
import java.util.*;

/**
 * @author : Yeogeru
 * @description : Graph Search
 * @since : 2025-04-22
 */

public class BOJ22944_G3 {

    static int N, H, D;
    static List<int[]> list = new ArrayList<>();
    static int[] start = new int[2];
    static int[] end = new int[2];
    static int result = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N; i++) {
            char[] input = br.readLine().toCharArray();
            for (int j = 0; j < N; j++) {
                if (input[j] == 'S') {
                    start[0] = i;
                    start[1] = j;
                }
                if (input[j] == 'E') {
                    end[0] = i;
                    end[1] = j;
                }
                if (input[j] == 'U') list.add(new int[]{i, j});
            }
        }

        play(start[0], start[1], H, 0, new boolean[N][N], 0);

        System.out.println(result != Integer.MAX_VALUE ? result : -1);

    }

    static void play(int r, int c, int h, int u, boolean[][] visit, int dist) {
        int toEndDist = Math.abs(r - end[0]) + Math.abs(c - end[1]);
        if (toEndDist <= h + u) {
            result = Math.min(result, dist + toEndDist);
            return;
        }
        for (int i = 0; i < list.size(); i++) {
            int nr = list.get(i)[0];
            int nc = list.get(i)[1];
            if (visit[nr][nc]) continue;
            int toUmbDist = Math.abs(r - nr) + Math.abs(c - nc);
            if (toUmbDist > h + u) continue;
            int damage = toUmbDist - u;
            if (damage < 0) damage = 0;
            visit[nr][nc] = true;
            play(nr, nc, h - damage, D, visit, dist + toUmbDist);
            visit[nr][nc] = false;
        }
    }

}
