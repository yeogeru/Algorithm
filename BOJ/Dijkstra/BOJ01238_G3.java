import java.io.*;
import java.util.*;

/**
 * @author : Yeogeru
 * @description : Dijkstra, 범위가 클 경우를 가정
 * @since : 2025-05-19
 */
public class BOJ01238_G3 {
    static class Node {
        int to, cost;

        public Node(int to, int cost) {
            this.to = to;
            this.cost = cost;
        }
    }

    static int N, M, X, max = 0;
    static Map<Integer, List<Node>> map = new HashMap<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            if (!map.containsKey(from)) map.put(from, new ArrayList<>());
            map.get(from).add(new Node(to, cost));
        }

        calc();
        System.out.println(max);
    }

    public static void calc() {
        int[] comebackDist = Dijkstra(X);
        for (int i = 1; i <= N; i++) {
            int[] result = Dijkstra(i);
            int total = result[X] + comebackDist[i];
            max = Math.max(max, total);
        }
    }

    static Set<Integer> visit = new HashSet<>();
    static PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1[1], o2[1]));

    public static int[] Dijkstra(int start) {
        int[] dist = new int[N + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        visit.clear();
        pq.clear();
        dist[start] = 0;
        pq.offer(new int[]{start, 0});
        while (!pq.isEmpty()) {
            int[] temp = pq.poll();
            int idx = temp[0];
            int curCost = temp[1];
            if (visit.contains(idx)) continue;
            visit.add(idx);
            if (!map.containsKey(idx)) continue;
            for (Node next : map.get(idx)) {
                int newDist = curCost + next.cost;
                boolean isMin = dist[next.to] > newDist;
                if (!visit.contains(next.to) && isMin) {
                    dist[next.to] = newDist;
                    pq.offer(new int[]{next.to, newDist});
                }
            }
        }
        return dist;
    }
}
