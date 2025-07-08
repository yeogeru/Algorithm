import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * @author : Yeogeru
 * @description : Binary Search
 * @since : 2025-07-07
 */
public class BOJ18114_G5 {
    static int n, c;
    static int[] weight;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        weight = new int[n];
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < n; i++) {
            weight[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(weight);

        boolean result = binarySearch(0, 0);

        for (int i = 0; i < n; i++) {
            if (weight[i] > c || result) break;
            result = binarySearch(i + 1, weight[i]);

            for (int j = i + 1; j < n; j++) {
                if (weight[i] + weight[j] > c || result) break;
                result = binarySearch(j + 1, weight[i] + weight[j]);
            }
        }
        
        System.out.println(result ? 1 : 0);

    }

    static boolean binarySearch(int left, int k) {
        int right = n - 1;
        int mid = (left + right) / 2;
        while (left <= right) {
            int val = k + weight[mid];
            if (val > c) right = mid - 1;
            if (val < c) left = mid + 1;
            if (val == c) return true;
            mid = (left + right) / 2;
        }
        return false;
    }

}
