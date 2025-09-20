import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 * @author : Yeogeru
 * @description : Implementation
 * @since : 2025-09-20
 */
public class BOJ17780_G2 {
    static int n, k;
    static int[][] board, horse;
    static int[] dtr = {0, 0, -1, 1};
    static int[] dtc = {1, -1, 0, 0};
    static List<List<Deque<Integer>>> list = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        board = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < n; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < n; i++) {
            list.add(new ArrayList<>());
            for (int j = 0; j < n; j++) {
                list.get(i).add(new ArrayDeque<>());
            }
        }

        horse = new int[k][3];
        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < 3; j++) {
                horse[i][j] = Integer.parseInt(st.nextToken()) - 1;
            }
            list.get(horse[i][0]).get(horse[i][1]).add(i);
        }

        System.out.println(play());

    }

    static int play() {
        int turn = 0;
        Main:
        while (++turn <= 1000) {
            for (int i = 0; i < k; i++) {
                int r = horse[i][0];
                int c = horse[i][1];
                int d = horse[i][2];
                int nr = r + dtr[d];
                int nc = c + dtc[d];
                int controller = list.get(r).get(c).peekFirst();
                if (controller != i) continue;
                if (!inRange(nr, nc) || board[nr][nc] == 2) {
                    int nd = (d + 1) % 2;
                    if (d >= 2) nd += 2;
                    nr = r + dtr[nd];
                    nc = c + dtc[nd];
                    horse[i][2] = nd;
                }
                if (!inRange(nr, nc)) continue;
                if (board[nr][nc] < 2) move(r, c, nr, nc);
                if (list.get(nr).get(nc).size() >= 4) break Main;
            }

            if (turn == 1000) {
                turn = -1;
                break;
            }
        }
        return turn;
    }

    static void move(int r, int c, int nr, int nc) {
        while (!list.get(r).get(c).isEmpty()) {
            int t = 0;
            if (board[nr][nc] == 1) t = list.get(r).get(c).pollLast();
            else t = list.get(r).get(c).pollFirst();
            list.get(nr).get(nc).add(t);
            horse[t][0] = nr;
            horse[t][1] = nc;
        }
    }


    static boolean inRange(int r, int c) {
        return r >= 0 && r < n && c >= 0 && c < n;
    }


}