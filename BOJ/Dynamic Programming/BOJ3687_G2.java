import java.io.*;
import java.util.*;

/**
 * @author : Yeogeru
 * @description : Dynamic Programming
 * @since : 2025-04-24
 */

public class BOJ3687_G2 {

    static long[] dp_min = new long[101];
    static int[] req_num = {0, 0, 1, 7, 4, 2, 0, 8};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb;
        int test_case = Integer.parseInt(br.readLine());
        Arrays.fill(dp_min, Long.MAX_VALUE);
        dp_min[2] = 1;
        dp_min[3] = 7;
        dp_min[4] = 4;
        dp_min[5] = 2;
        dp_min[6] = 6;
        dp_min[7] = 8;
        dp_min[8] = 10;

        for (int i = 9; i <= 100; i++) {
            for (int j = 2; j <= 7; j++) {
                String temp = String.valueOf(dp_min[i - j]) + String.valueOf(req_num[j]);
                dp_min[i] = Math.min(Long.parseLong(temp), dp_min[i]);
            }
        }

        for (int t = 0; t < test_case; t++) {
            int n = Integer.parseInt(br.readLine());
            sb = new StringBuilder();

            sb.append(dp_min[n]);
            sb.append(" ");

            if (n % 2 == 0) sb.append(calc_max(n));
            else {
                sb.append("7");
                sb.append(calc_max(n - 3));
            }
            sb.append("\n");
            bw.write(sb.toString());
        }
        bw.flush();
        bw.close();
    }

    public static String calc_max(int n) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n / 2; i++) {
            sb.append("1");
        }
        return sb.toString();
    }

}
