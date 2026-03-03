import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author : Yeogeru
 * @description : Brute Force
 * @since : 2026-03-03
 */
public class BOJ14500_G4_2 {
    static int n, m;
    static int[][] graph;
    static int[][] dtr = {
            {0, 0, 0, 0}, {0, 1, 2, 3},
            {0, 1, 0, 1},
            {0, 0, 0, 1}, {0, 0, 0, -1}, {0, 0, 0, 1}, {0, 0, 0, -1}, {0, 1, 2, 0}, {0, 1, 2, 0}, {0, 1, 2, 2}, {0, 1, 2, 2},
            {0, 0, 1, 1}, {0, 0, -1, -1}, {0, 1, 1, 2}, {0, 1, 1, 2},
            {0, 0, 0, -1}, {0, 0, 0, 1}, {0, 1, 2, 1}, {0, 1, 2, 1}
    };
    static int[][] dtc = {
            {0, 1, 2, 3}, {0, 0, 0, 0},
            {0, 0, 1, 1},
            {0, 1, 2, 0}, {0, 1, 2, 0}, {0, 1, 2, 2}, {0, 1, 2, 2}, {0, 0, 0, -1}, {0, 0, 0, 1}, {0, 0, 0, -1}, {0, 0, 0, 1},
            {0, 1, 1, 2}, {0, 1, 1, 2}, {0, 0, 1, 1}, {0, 0, -1, -1},
            {0, 1, 2, 1}, {0, 1, 2, 1}, {0, 0, 0, -1}, {0, 0, 0, 1}
    };

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine(), " ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        graph = new int[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < m; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int max = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                for (int k = 0; k < dtr.length; k++) {
                    int total = 0;
                    int r, c;
                    for (int l = 0; l < 4; l++) {
                        r = i + dtr[k][l];
                        c = j + dtc[k][l];
                        if (!inRange(r, c)) break;
                        total += graph[r][c];
                        if (l == 3 && max < total) {
                            max = total;
                        }
                    }
                }
            }
        }

        System.out.println(max);

    }

    static boolean inRange(int r, int c) {
        return r >= 0 && r < n && c >= 0 && c < m;
    }


}
