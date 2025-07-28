import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 * @author : Yeogeru
 * @description : Topology Sort
 * @since : 2025-07-28
 */
public class BOJ14567_G5 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[] depth = new int[n + 1];
        List<List<Integer>> list = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            list.add(new ArrayList<>());
        }
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            list.get(a).add(b);
            ++depth[b];
        }

        Queue<Integer> que = new ArrayDeque<>();
        for (int i = 1; i <= n; i++) {
            if (depth[i] == 0) que.add(i);
        }

        int[] result = new int[n + 1];
        int count = 1;
        boolean changed = true;
        while (changed) {
            changed = false;
            int qSize = que.size();
            for(int q = 0 ; q < qSize ; q++) {
                int t = que.poll();
                result[t] = count;
                for (int i : list.get(t)) {
                    if (--depth[i] == 0) {
                        que.add(i);
                        changed = true;
                    }
                }
            }
            ++count;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            sb.append(result[i]);
            if (i != n) sb.append(" ");
        }

        System.out.println(sb);

    }

}
