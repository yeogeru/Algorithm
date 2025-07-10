import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * @author : Yeogeru
 * @description : Implementation
 * @since : 2025-07-10
 */
public class BOJ20165_G5 {
    static int n, m;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        int round = Integer.parseInt(st.nextToken());
        int[][] map = new int[n + 1][m + 1];
        int[][] origin = new int[n + 1][m + 1];
        int[] dtr = {0, 0, 1, -1};
        int[] dtc = {1, -1, 0, 0};
        Map<Character, Integer> direction = new HashMap<>();
        direction.put('E', 0);
        direction.put('W', 1);
        direction.put('S', 2);
        direction.put('N', 3);
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 1; j <= m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                origin[i][j] = map[i][j];
            }
        }

        int total = 0;
        for (int t = 0; t < round; t++) {
            st = new StringTokenizer(br.readLine(), " ");
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int d = direction.get(st.nextToken().charAt(0));
            int num = map[r][c] - 1;
            if (map[r][c] > 0) ++total;
            map[r][c] = 0;
            int cur = 0;
            while (cur++ < num) {
                int dr = r + dtr[d];
                int dc = c + dtc[d];
                if (outRange(dr, dc)) break;
                r = dr;
                c = dc;
                num = Math.max(num, cur + map[dr][dc] - 1);
                if (map[r][c] > 0) ++total;
                map[r][c] = 0;
            }

            st = new StringTokenizer(br.readLine(), " ");
            r = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());
            map[r][c] = origin[r][c];
        }

        System.out.println(total);
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (map[i][j] > 0) sb.append('S');
                else sb.append('F');
                if (j < m) sb.append(" ");
            }
            if (i < n) sb.append("\n");
        }
        System.out.println(sb.toString());


    }

    static boolean outRange(int r, int c) {
        return r <= 0 || r > n || c <= 0 || c > m;
    }


}
