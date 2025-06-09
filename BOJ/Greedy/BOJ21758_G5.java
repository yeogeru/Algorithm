import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author : Yeogeru
 * @description : Greedy
 * @since : 2025-06-09
 */
public class BOJ21758_G5 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int[] arr = new int[n];
        int[] sum = new int[n];
        int total = 0;
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            total += arr[i];
            sum[i] = total;
        }
        int result = 0;
        for (int i = 1; i < n - 1; i++) {
            result = Math.max(result, (total - arr[0] - arr[i]) + (total - sum[i]));
            result = Math.max(result, (total - arr[0] - arr[n - 1]) + arr[i]);
            result = Math.max(result, (total - arr[n - 1] - arr[i]) + sum[i - 1]);
        }
        System.out.println(result);
    }
}
