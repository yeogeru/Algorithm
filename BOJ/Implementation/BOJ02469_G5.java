import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ02469_G5 {

    static int k, n, index;
    static boolean[][] ladder;

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        k = Integer.parseInt(br.readLine());
        n = Integer.parseInt(br.readLine());
        char[] end = br.readLine().toCharArray();
        ladder = new boolean[n][k - 1];
        index = 0;
        for (int i = 0; i < n; i++) {
            char[] input = br.readLine().toCharArray();
            for (int j = 0; j < k - 1; j++) {
                if (input[j] == '?') {
                    index = i;
                    break;
                }
                if (input[j] == '-') ladder[i][j] = true;
            }
        }

        char[] first = play_up(end);
        char[] second = play_down();

        char[] result = new char[k - 1];
        Arrays.fill(result, '*');
        for (int i = 0; i < k - 1; i++) {
            if (first[i] == second[i]) continue;
            if (first[i] == second[i + 1] && first[i + 1] == second[i]) {
                result[i] = '-';
                ++i;
                continue;
            }
            Arrays.fill(result, 'x');
            break;
        }

        System.out.println(String.valueOf(result));

    }

    static char[] play_up(char[] result) {
        for (int i = n - 1; i > index; i--) {
            for (int j = 0; j < k - 1; j++) {
                if (ladder[i][j]) {
                    char temp = result[j + 1];
                    result[j + 1] = result[j];
                    result[j] = temp;
                    ++j;
                }
            }
        }

        return result;
    }

    static char[] play_down() {
        char[] result = new char[k];
        for (int i = 0; i < k; i++) {
            result[i] = (char) ('A' + i);
        }

        for (int i = 0; i < index; i++) {
            for (int j = 0; j < k - 1; j++) {
                if (ladder[i][j]) {
                    char temp = result[j + 1];
                    result[j + 1] = result[j];
                    result[j] = temp;
                    ++j;
                }

            }
        }

        return result;
    }


}