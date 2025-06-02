import java.io.*;
import java.util.*;

/**
 * @author : Yeogeru
 * @description : Greedy
 * @since : 2025-06-02
 */
public class BOJ20115_S3 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        double result = 0;
        int max_index = 0;
        double[] arr = new double[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Double.parseDouble(st.nextToken());
            if (result < arr[i]) {
                result = arr[i];
                max_index = i;
            }
        }
        for (int i = 0; i < n; i++) {
            if (i == max_index) continue;
            result += (arr[i] / 2);
        }
        System.out.println(result);
    }
}
