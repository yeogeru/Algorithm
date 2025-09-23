import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA1206 {
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        for (int test_case = 1; test_case <= 10; test_case++) {
            int n = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine(), " ");
            int[] building = new int[n];
            int total = 0;
            for (int i = 0; i < n; i++) {
                building[i] = Integer.parseInt(st.nextToken());
            }

            for (int i = 2; i < n - 2; i++) {
                int max = 0;
                for (int j = i - 2; j <= i + 2; j++) {
                    if (j == i) continue;
                    max = Math.max(max, building[j]);
                }
                if (max < building[i]) total += (building[i] - max);
            }
            sb.append("#").append(test_case).append(" ").append(total).append("\n");
        }

        System.out.println(sb.toString());
    }
}