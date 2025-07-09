import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 * @author : Yeogeru
 * @description : Implementation, Greedy
 * @since : 2025-07-09
 */
public class BOJ20207_G5 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st;
        List<int[]> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            list.add(new int[]{a, b + 1});
        }
        Collections.sort(list, (o1, o2) -> {
            if (o1[0] == o2[0]) return Integer.compare(o2[1], o1[1]);
            return Integer.compare(o1[0], o2[0]);
        });

        int start = list.get(0)[0];
        int end = list.get(0)[1];
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        pq.add(list.get(0)[1]);
        int result = 0;
        for (int i = 1; i < n; i++) {
            if (list.get(i)[0] > end) {
                result += pq.size() * (end - start);
                pq.clear();
                start = list.get(i)[0];
                end = list.get(i)[1];
                pq.add(end);
                continue;
            }

            if (list.get(i)[0] >= pq.peek()) pq.poll();
            end = Math.max(end, list.get(i)[1]);
            pq.add(list.get(i)[1]);
        }

        if (!pq.isEmpty()) {
            result += pq.size() * (end - start);
        }

        System.out.println(result);

    }

}
