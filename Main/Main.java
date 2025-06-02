import java.io.*;
import java.util.*;

/**
 * @author : Yeogeru
 * @description : Greedy
 * @since : 2025-06-02
 */
public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> {
            if (o1[1] == o2[1]) return Integer.compare(o1[0], o2[0]);
            return Integer.compare(o1[1], o2[1]);
        });
        StringTokenizer st;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            pq.offer(new int[]{a, b});
        }
        int total = 0;
        int last_time = 0;
        while (!pq.isEmpty()) {
            int[] t = pq.poll();
            if (t[0] < last_time) continue;
            last_time = t[1];
            ++total;
        }
        System.out.println(total);
    }
}
