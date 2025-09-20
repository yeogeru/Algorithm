import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * @author : Yeogeru
 * @description : Dynamic Programming
 * @since : 2025-09-20
 */
public class BOJ02141_G4 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st;
        int[][] arr = new int[n][2];
        long total = 0;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
            total += arr[i][1];
        }
        Arrays.sort(arr, (o1, o2) -> Integer.compare(o1[0], o2[0]));

        long result = 0;
        for (int i = 0; i < n; i++) {
            result += arr[i][1];
            if ((total + 1) / 2 <= result) {
                System.out.println(arr[i][0]);
                break;
            }
        }

    }

}