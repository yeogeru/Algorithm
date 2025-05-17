import java.io.*;
import java.util.*;

/**
 * @author : Yeogeru
 * @description : Two Pointer
 * @since : 2025-05-16
 */
public class BOJ17609_G5 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int test_case = Integer.parseInt(br.readLine());
        for (int t = 0; t < test_case; t++) {
            String input = br.readLine();
            int result = calc(input, 0, input.length() - 1, false);
            sb.append(result).append("\n");
        }
        System.out.println(sb);
    }

    public static int calc(String i, int left, int right, boolean retry) {
        while (left < right) {
            if (i.charAt(left) != i.charAt(right)) {
                if (retry) return 2;
                int result = calc(i, left + 1, right, true);
                if (result == 0) return 1;
                result = calc(i, left, right - 1, true);
                if (result == 0) return 1;
                return 2;
            }
            ++left;
            --right;
        }
        return 0;
    }
}
