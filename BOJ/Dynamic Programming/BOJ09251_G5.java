import java.io.*;
import java.util.*;
/**
 * @author : Yeogeru
 * @description : Dynamic Programming
 * @since : 2025-05-29
 */
public class BOJ09251_G5 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] a = br.readLine().toCharArray();
        char[] b = br.readLine().toCharArray();
        int[][] lcs = new int[a.length + 1][b.length + 1];
        for (int i = 1; i <= a.length; i++) {
            for (int j = 1; j <= b.length; j++) {
                lcs[i][j] = Math.max(lcs[i - 1][j], lcs[i][j - 1]);
                if (a[i - 1] == b[j - 1]) lcs[i][j] = lcs[i - 1][j - 1] + 1;
            }
        }
        System.out.println(lcs[a.length][b.length]);
    }
}
