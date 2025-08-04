import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author : Yeogeru
 * @description : Backtracking
 * @since : 2025-08-04
 */
public class BOJ17136_G2 {
    static int n = 10;
    static boolean[][] paper;
    static int[][] result;
    static int[] count = {5, 5, 5, 5, 5};
    static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        paper = new boolean[n][n];
        result = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < n; j++) {
                if (Integer.parseInt(st.nextToken()) == 1) paper[i][j] = true;
            }
        }

        calc(0, 0, 0);
        if (min == Integer.MAX_VALUE) min = -1;
        System.out.println(min);

    }

    static void calc(int r, int c, int cur) {
        if (min <= cur) return;
        while (r < n && c < n) {
            if (paper[r][c]) break;
            if (c + 1 < n) {
                ++c;
                continue;
            }
            ++r;
            c = 0;
        }

        if (r >= n) {
            min = Math.min(min, cur);
            return;
        }

        for (int i = 0; i < count.length; i++) {
            if (count[i] == 0) continue;
            if (!isSquare(r, c, i)) break;
            --count[i];
            attach(r, c, i, false);
            if (c + 1 < n) calc(r, c + 1, cur + 1);
            else calc(r + 1, 0, cur + 1);
            ++count[i];
            attach(r, c, i, true);
        }

    }


    static void attach(int r, int c, int depth, boolean type) {
        for (int i = r; i <= r + depth; i++) {
            for (int j = c; j <= c + depth; j++) {
                paper[i][j] = type;
            }
        }
    }

    static boolean isSquare(int r, int c, int depth) {
        for (int i = r; i <= r + depth; i++) {
            for (int j = c; j <= c + depth; j++) {
                if (i < 0 || i >= n || j < 0 || j >= n) return false;
                if (!paper[i][j]) return false;
            }
        }
        return true;
    }


}
