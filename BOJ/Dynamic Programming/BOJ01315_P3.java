import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringTokenizer;

/**
 * @author yeogeru
 * @description Dynamic Programming
 * @since 2024-09-23
 */
public class BOJ01315_P3 {
    static int[][] dp = new int[1001][1001];
    static int[] STR_Q, INT_Q, PNT_Q;
    static boolean[] clear;
    static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        StringTokenizer st;
        STR_Q = new int[n];
        INT_Q = new int[n];
        PNT_Q = new int[n];
        clear = new boolean[n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            STR_Q[i] = a;
            INT_Q[i] = b;
            PNT_Q[i] = c;
        }
        for (int i = 0; i <= 1000; i++) Arrays.fill(dp[i], -1);
        System.out.println(playRPG(1, 1, 0));
    }

    static int playRPG(int STR, int INT, int PNT) {
        if (dp[STR][INT] != -1) return dp[STR][INT];
        dp[STR][INT] = 0;
        Deque<Integer> que = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            if (STR >= STR_Q[i] || INT >= INT_Q[i]) {
                ++dp[STR][INT];
                if (clear[i]) continue;
                PNT += PNT_Q[i];
                clear[i] = true;
                que.offer(i);
            }
        }
        for (int i = 0; i < n; i++) {
            if (clear[i]) continue;
            int STRP = STR_Q[i] - STR;
            int INTP = INT_Q[i] - INT;
            if (STRP < 0) STRP = 0;
            if (INTP < 0) INTP = 0;
            if (PNT >= STRP) {
                clear[i] = true;
                dp[STR][INT] = Math.max(dp[STR][INT], playRPG(STR + STRP, INT, PNT - STRP + PNT_Q[i]));
                clear[i] = false;
            }
            if (PNT >= INTP) {
                clear[i] = true;
                dp[STR][INT] = Math.max(dp[STR][INT], playRPG(STR, INT + INTP, PNT - INTP + PNT_Q[i]));
                clear[i] = false;
            }
        }
        int qsize = que.size();
        for (int i = 0; i < qsize; i++) {
            clear[que.poll()] = false;
        }
        return dp[STR][INT];
    }
}
