import java.io.*;
import java.util.*;

/**
 * @author : Yeogeru
 * @description : Greedy
 * @since : 2025-05-12
 */
public class BOJ11000_G5 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        List<int[]> list = new ArrayList<>();
        int n = Integer.parseInt(br.readLine());
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int s = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());
            list.add(new int[]{s, t});
        }
        Collections.sort(list, (o1, o2) -> {
            if (o1[0] == o2[0]) return Integer.compare(o1[1], o2[1]);
            return Integer.compare(o1[0], o2[0]);
        });

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        pq.add(list.get(0)[1]);

        for (int i = 1; i < n; i++) {
            if (pq.peek() <= list.get(i)[0]) pq.poll();
            pq.add(list.get(i)[1]);
        }

        System.out.println(pq.size());
    }
}
