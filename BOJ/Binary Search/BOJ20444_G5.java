import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author : Yeogeru
 * @description : Binary Search
 * @since : 2025-11-24
 */
public class BOJ20444_G5 {
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        long n = Long.parseLong(st.nextToken());
        long k = Long.parseLong(st.nextToken());

        long left = 0;
        long right = n;
        boolean result = false;
        while (left <= right) {
            long mid = (left + right) / 2;
            long total = (mid + 1) * (n - mid + 1);
            if (total == k) {
                result = true;
                break;
            }
            if (total > k) right = mid - 1;
            else left = mid + 1;

        }

        System.out.println(result ? "YES" : "NO");


    }


}