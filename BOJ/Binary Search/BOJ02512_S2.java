import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * @author : Yeogeru
 * @description : Binary Search
 * @since : 2025-04-09
 */

public class BOJ02512_S2 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int n = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine(), " ");
        int[] arr = new int[n];
        int arr_max = 0;
        int arr_total = 0;
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            arr_max = Math.max(arr_max, arr[i]);
            arr_total += arr[i];
        }

        int m = Integer.parseInt(br.readLine());
        int result = 0;
        if (arr_total > m) {
            int low = 0, high = m;
            while (low <= high) {
                int mid = (low + high) / 2;
                int total = 0;
                for (int i = 0; i < n; i++) {
                    total += arr[i];
                    if (arr[i] > mid) total -= (arr[i] - mid);
                }

                if (total > m) high = mid - 1;
                else {
                    result = Math.max(result, mid);
                    low = mid + 1;
                }
            }
        } else result = arr_max;

        System.out.println(result);

    }

}
