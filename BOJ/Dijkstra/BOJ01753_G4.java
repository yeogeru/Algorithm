import java.io.*;
import java.util.*;

/**
 * @author : Yeogeru
 * @description : Dijkstra
 * @since : 2025-05-15
 */
class Edge {
    int to, weight;

    public Edge(int t, int w) {
        this.to = t;
        this.weight = w;
    }
}

public class BOJ01753_G4 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int v = Integer.parseInt(st.nextToken());
        int e = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(br.readLine());
        List<List<Edge>> list = new ArrayList<>();

        for (int i = 0; i <= v; i++) {
            list.add(new ArrayList<>());
        }

        for (int i = 0; i < e; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            list.get(from).add(new Edge(to, weight));
        }

        boolean[] visit = new boolean[v + 1];
        int[] dist = new int[v + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[k] = 0;
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1[1], o2[1]));
        pq.add(new int[]{k, 0});
        while (!pq.isEmpty()) {
            int[] temp = pq.poll();
            int from = temp[0];
            int curWeight = temp[1];
            if (!visit[from]) visit[from] = true;
            for (Edge next : list.get(from)) {
                if (!visit[next.to] && dist[next.to] > curWeight + next.weight) {
                    dist[next.to] = curWeight + next.weight;
                    pq.add(new int[]{next.to, dist[next.to]});
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= v; i++) {
            sb.append(dist[i] == Integer.MAX_VALUE ? "INF" : dist[i]).append("\n");
        }

        System.out.println(sb);

    }
}
