import java.io.*;
import java.util.*;

public class Main {
    static final long H = 2001;
    static int sum;

    static long encode(int a, int b, int d) {
        return (a * H * H) + (b * H) + d;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        sum = 3 * n;
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        Queue<int[]> que = new ArrayDeque<>();
        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        boolean[][] visit = new boolean[2 * n][2 * n];
        long[][] parent = new long[2 * n][2 * n];
        visit[A][B] = true;

        que.add(new int[]{A, B});

        int[] change = new int[3];
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < 3; i++) {
            change[i] = Integer.parseInt(st.nextToken());
        }

        int total = 0;
        parent[A][B] = -1;
        long endKey = -1;

        Main:
        while (!que.isEmpty()) {
            int qSize = que.size();
            for (int q = 0; q < qSize; q++) {
                int[] cur = que.poll();
                for (int d = 0; d < 3; d++) {
                    int[] next = {cur[0], cur[1], sum - cur[0] - cur[1]};
                    int minusIdx = (d + 2) % 3;

                    next[d] += change[d];
                    next[minusIdx] -= change[d];

                    if (next[d] >= 2 * n || next[minusIdx] <= 0) continue;

                    if (visit[next[0]][next[1]]) continue;
                    visit[next[0]][next[1]] = true;

                    que.add(new int[]{next[0], next[1]});
                    long nextKey = encode(cur[0], cur[1], d);
                    parent[next[0]][next[1]] = nextKey;

                    boolean isEqual = next[d] == next[minusIdx] && next[(d + 1) % 3] == next[d];
                    if (isEqual && next[d] == n) {
                        endKey = nextKey;
                        break Main;
                    }
                }
            }
            ++total;
        }
        System.out.println(endKey != -1 ? total + 1 : -1);
        if (endKey != -1) {
            StringBuilder sb = new StringBuilder();
            long curKey = endKey;
            while (curKey != -1) {
                int a = (int) (curKey / (H * H));
                int b = (int) ((curKey / H) % H);
                int d = (int) (curKey % H);
                sb.append((char) ('A' + d));
                curKey = parent[a][b];
            }
            System.out.println(sb.reverse());
        }

    }

}
