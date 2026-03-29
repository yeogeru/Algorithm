import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * @author : Yeogeru
 * @description : Implementation
 * @since : 2026-03-29
 */
public class BOJ30892_S1 {
    static int n, k;
    static long t;

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        t = Integer.parseInt(st.nextToken());

        PriorityQueue<Integer> shark = new PriorityQueue<>();
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < n; i++) {
            shark.add(Integer.parseInt(st.nextToken()));
        }

        Stack<Integer> stack = new Stack<>();
        int count = 0;
        while (!shark.isEmpty()) {
            int cur = shark.poll();
            if (cur < t) {
                stack.push(cur);
                continue;
            }

            while (t <= cur && count < k) {
                if (!stack.isEmpty()) {
                    t += stack.pop();
                    ++count;
                } else {
                    shark.clear();
                    break;
                }
            }

            if (cur < t) stack.push(cur);

        }

        while (!stack.isEmpty() && count < k) {
            t += stack.pop();
            ++count;
        }

        System.out.println(t);

    }

}
