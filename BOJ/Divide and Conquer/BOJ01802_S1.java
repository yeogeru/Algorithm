import java.io.*;
import java.util.*;

/**
 * @author : Yeogeru
 * @since : 2025-05-12
 */
public class BOJ01802_S1 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int test_case = Integer.parseInt(br.readLine());
        for (int t = 0; t < test_case; t++) {
            char[] s = br.readLine().toCharArray();
            int left = 0, right = s.length - 1;
            boolean isPossible = true;
            Main:
            while (left < right) {
                int mid = (left + right) / 2;
                for (int i = 0; i < mid; i++) {
                    if (s[i] == s[right - i]) {
                        isPossible = false;
                        break Main;
                    }
                }
                right = mid - 1;
            }
            bw.write(isPossible ? "YES\n" : "NO\n");
        }
        bw.flush();
        bw.close();
    }
}
