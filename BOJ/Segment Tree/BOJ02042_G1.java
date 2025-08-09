import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author : Yeogeru
 * @description : Segment Tree
 * @since : 2025-08-09
 */
public class BOJ02042_G1 {
    static int n, start;
    static long[] tree;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        StringBuilder sb = new StringBuilder();
        n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int h = (int) Math.ceil(Math.log(n) / Math.log(2));
        start = (int) Math.pow(2, h);
        tree = new long[start * 2];

        for (int i = 0; i < n; i++) {
            tree[start + i] = Long.parseLong(br.readLine());
        }

        for (int i = tree.length - 1; i > 0; i--) {
            tree[i / 2] += tree[i];
        }

        for (int i = 0; i < m + k; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int index = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            long b = Long.parseLong(st.nextToken());
            if (index == 1) update(a, b);
            else if (index == 2) sb.append(sum(a, (int) b)).append("\n");
        }

        System.out.println(sb);

    }

    static void update(int index, long value) {
        index += start - 1;
        tree[index] = value;
        while (index > 0) {
            if (index % 2 == 0) {
                tree[index / 2] = tree[index] + tree[index + 1];
            } else {
                tree[index / 2] = tree[index] + tree[index - 1];
            }
            index /= 2;
        }
    }

    static long sum(int left, int right) {
        left += start - 1;
        right += start - 1;
        long total = 0;
        while (left <= right) {
            if (left % 2 == 0) left /= 2;
            else {
                total += tree[left];
                left = (left + 1) / 2;
            }

            if (right % 2 != 0) right /= 2;
            else {
                total += tree[right];
                right = (right - 1) / 2;
            }

        }

        return total;

    }

}
