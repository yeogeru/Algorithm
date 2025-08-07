import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 * @author : Yeogeru
 * @description : Dijkstra
 * @since : 2025-08-07
 */
public class BOJ14938_G4 {
    static class Edge implements Comparable<Edge> {
        int to, weight;

        public Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            return Integer.compare(this.weight, o.weight);
        }

    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int r = Integer.parseInt(st.nextToken());

        int[] items = new int[n + 1];
        st = new StringTokenizer(br.readLine(), " ");
        List<List<Edge>> list = new ArrayList<>();
        list.add(new ArrayList<>());
        for (int i = 1; i <= n; i++) {
            items[i] = Integer.parseInt(st.nextToken());
            list.add(new ArrayList<>());
        }

        for (int i = 0; i < r; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            list.get(start).add(new Edge(end, weight));
            list.get(end).add(new Edge(start, weight));
        }

        int[] dist = new int[n + 1];
        boolean[] visit = new boolean[n + 1];

        PriorityQueue<Edge> pq = new PriorityQueue<>();
        int max = 0;
        for (int i = 1; i <= n; i++) {
            Arrays.fill(dist, Integer.MAX_VALUE);
            Arrays.fill(visit, false);
            pq.clear();
            pq.add(new Edge(i, 0));
            dist[i] = 0;

            while (!pq.isEmpty()) {
                Edge t = pq.poll();
                int index = t.to;
                if (visit[index]) continue;
                visit[index] = true;

                for (Edge e : list.get(index)) {
                    if (visit[e.to]) continue;
                    if (dist[e.to] > dist[index] + e.weight) {
                        dist[e.to] = dist[index] + e.weight;
                        pq.add(new Edge(e.to, dist[e.to]));
                    }
                }
            }

            int total = 0;
            for (int j = 1; j <= n; j++) {
                if (dist[j] <= m) total += items[j];
            }

            max = Math.max(max, total);

        }

        System.out.println(max);


    }

}
