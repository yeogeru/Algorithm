import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 * @author : Yeogeru
 * @description : Topological Sort
 * @since : 2025-08-02
 */
public class BOJ1005_G3 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int test_case = Integer.parseInt(br.readLine());
        StringTokenizer st;
        List<List<Integer>> next = new ArrayList<>();
        Queue<int[]> que = new ArrayDeque<>();
        StringBuilder sb = new StringBuilder();
        for (int T = 1; T <= test_case; T++) {
            st = new StringTokenizer(br.readLine(), " ");
            int n = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());
            int[] cost = new int[n + 1];
            next.clear();

            st = new StringTokenizer(br.readLine(), " ");
            next.add(new ArrayList<>());
            for (int i = 1; i <= n; i++) {
                cost[i] = Integer.parseInt(st.nextToken());
                next.add(new ArrayList<>());
            }

            int[] depth = new int[n + 1];
            int[] result = new int[n + 1];
            for (int i = 0; i < k; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                ++depth[b];
                next.get(a).add(b);
            }

            int dest = Integer.parseInt(br.readLine());
            que.clear();
            for (int i = 1; i <= n; i++) {
                if (depth[i] == 0) que.add(new int[]{i, 0});
            }

            while (!que.isEmpty()) {
                int[] t = que.poll();
                int index = t[0];
                int cur = t[1];
                if (index == dest) break;
                for (int i : next.get(index)) {
                    result[i] = Math.max(result[i], cur + cost[index]);
                    if (--depth[i] == 0) {
                        que.add(new int[]{i, result[i]});
                    }
                }
            }
            sb.append(result[dest] + cost[dest]);
            if(T != test_case) sb.append("\n");
        }

        System.out.println(sb);
    }

}
