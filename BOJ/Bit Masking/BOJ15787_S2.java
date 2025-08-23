import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

/**
 * @author : Yeogeru
 * @description : Bitmasking
 * @since : 2025-08-23
 */
public class BOJ15787_S2 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[] train = new int[n];
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int command = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = 0;
            int next = 0;
            switch (command) {
                case 1:
                    b = Integer.parseInt(st.nextToken()) - 1;
                    next = 1 << b;
                    train[a] |= next;
                    break;
                case 2:
                    b = Integer.parseInt(st.nextToken()) - 1;
                    next = ~(1 << b);
                    train[a] &= next;
                    break;
                case 3:
                    next = train[a] << 1;
                    next &= ~(1 << 20);
                    train[a] = next;
                    break;
                case 4:
                    next = train[a] >> 1;
                    next &= ~(1 << 20);
                    train[a] = next;
                    break;
            }
        }

        for (int i = 0; i < n; i++) {
            set.add(train[i]);
        }

        System.out.println(set.size());

    }
}
