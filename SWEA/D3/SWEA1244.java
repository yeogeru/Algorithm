import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA1244 {
    static int result = 0;
    static char[] arr;
    static int n;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int test_case = 1; test_case <= T; test_case++) {
            st = new StringTokenizer(br.readLine(), " ");
            arr = st.nextToken().toCharArray();
            n = Integer.parseInt(st.nextToken());

            play(0, 0);

            sb.append("#").append(test_case).append(" ").append(result);
            if (test_case < T) sb.append("\n");
            result = 0;
        }

        System.out.print(sb.toString());

    }

    static void play(int depth, int cnt) {
        if (cnt == n) {
            int total = Integer.parseInt(new String(arr));
            result = Math.max(result, total);
            return;
        }

        for (int i = depth; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                change(i, j);
                play(i, cnt + 1);
                change(i, j);
            }
        }


    }

    static void change(int a, int b) {
        char temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

}