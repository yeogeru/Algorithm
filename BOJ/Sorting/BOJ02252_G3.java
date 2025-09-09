import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 * @author : Yeogeru
 * @description : Topological Sorting
 * @since : 2025-09-09
 */
public class BOJ02252_G3 {
    static int n, m;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        int[] depth = new int[n + 1];
        List<List<Integer>> list = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            list.add(new ArrayList<>());
        }

        for (int t = 0; t < m; t++) {
            st = new StringTokenizer(br.readLine(), " ");
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            ++depth[b];
            list.get(a).add(b);
        }

        Queue<Integer> que = new ArrayDeque<>();

        for (int i = 1; i <= n; i++) {
            if (depth[i] == 0) {
                que.add(i);
            }
        }

        StringBuilder sb = new StringBuilder();
        int cnt = 0;
        while (!que.isEmpty()) {
            int t = que.poll();
            sb.append(t);
            if (++cnt < n) sb.append(" ");

            for (int i : list.get(t)) {
                --depth[i];
                if (depth[i] == 0) que.add(i);
            }
        }

        System.out.println(sb.toString());


    }


}