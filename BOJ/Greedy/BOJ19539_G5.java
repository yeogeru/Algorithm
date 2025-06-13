import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author : Yeogeru
 * @description : Greedy
 * @since : 2025-06-12
 */
public class BOJ19539_G5 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int sum = 0;
        int hol = 0;
        for (int i = 0; i < n; i++) {
            int t = Integer.parseInt(st.nextToken());
            sum += t;
            if (t % 2 == 1) ++hol;
        }

        System.out.println(sum % 3 == 0 && hol < sum / 3 ? "YES" : "NO");

    }
}
