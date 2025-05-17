import java.io.*;
import java.util.*;

/**
 * @author : Yeogeru
 * @description : KMP
 * @since : 2025-05-18
 */
public class BOJ01786_P5 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String t = br.readLine();
        String p = br.readLine();
        StringBuilder sb = new StringBuilder();
        int[] arr = new int[p.length()];
        int cnt = 0;
        for (int i = 1; i < p.length(); i++) {
            while (cnt > 0 && p.charAt(i) != p.charAt(cnt)) cnt = arr[cnt - 1];
            if (p.charAt(i) == p.charAt(cnt)) arr[i] = ++cnt;
        }
        int total = 0;
        cnt = 0;
        Queue<Integer> result = new ArrayDeque<>();
        for (int i = 0; i < t.length(); i++) {
            while (cnt > 0 && t.charAt(i) != p.charAt(cnt)) cnt = arr[cnt - 1];
            if (t.charAt(i) == p.charAt(cnt)) {
                if (cnt == p.length() - 1) {
                    ++total;
                    result.add(i - cnt + 1);
                    cnt = arr[cnt];
                    continue;
                }
                ++cnt;
            }
        }
        System.out.println(total);
        while (!result.isEmpty()) {
            sb.append(result.poll());
            if (!result.isEmpty()) sb.append(" ");
        }
        System.out.println(sb);
    }
}
