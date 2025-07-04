import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 * @author : Yeogeru
 * @description : Greedy
 * @since : 2025-07-04
 */
public class BOJ19598_G5 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st;
        List<int[]> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            list.add(new int[]{a, b});
        }

        Collections.sort(list, (o1, o2) -> {
            if (o1[0] == o2[0]) return Integer.compare(o2[1], o1[1]);
            return Integer.compare(o1[0], o2[0]);
        });

        PriorityQueue<Integer> pq = new PriorityQueue<>(); // ~에 종료되는 희의실
        pq.add(list.get(0)[1]);
        for (int i = 1; i < n; i++) {
            if (pq.peek() > list.get(i)[0]) {
                pq.add(list.get(i)[1]);
                continue;
            }
            pq.poll();
            pq.add(list.get(i)[1]);
        }

        System.out.println(pq.size());

    }
}
