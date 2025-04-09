import java.io.*;
import java.util.*;

/**
 * @author : Yeogeru
 * @description : Two Pointer
 * @since : 2025-04-09
 */
public class BOJ02470_G5_ {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);

        int left = 0, right = n - 1;
        int min = Integer.MAX_VALUE;
        int res_left = 0, res_right = 0;
        while (left < right) {
            int total = arr[left] + arr[right];
            int total_abs = Math.abs(total);
            if (min > total_abs) {
                min = total_abs;
                res_left = arr[left];
                res_right = arr[right];
            }

            if(total == 0) break;
            else if(total < 0) ++left;
            else --right;

        }

        System.out.println(res_left + " " + res_right);

    }
}
