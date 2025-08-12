import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author : Yeogeru
 * @description : Implementation
 * @since : 2025-08-12
 */
public class BOJ14891_G5 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        boolean[][] state = new boolean[4][8];
        for (int i = 0; i < 4; i++) {
            char[] input = br.readLine().toCharArray();
            for (int j = 0; j < 8; j++) {
                state[i][j] = input[j] == '1';
            }
        }
        int[] cur = {0, 0, 0, 0};
        int T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            st = new StringTokenizer(br.readLine(), " ");
            int index = Integer.parseInt(st.nextToken()) - 1;
            int rotate = -Integer.parseInt(st.nextToken());
            int left = index;
            int right = index;
            boolean leftIsRotate = true;
            boolean rightIsRotate = true;
            int[] nextState = {0, 0, 0, 0};
            nextState[index] += rotate;
            while (leftIsRotate || rightIsRotate) {
                if (left > 0 && leftIsRotate) {
                    if (state[left][(cur[left] + 6) % 8] == state[left - 1][(cur[left - 1] + 2) % 8]) {
                        leftIsRotate = false;
                    } else nextState[left - 1] += (-nextState[left]);
                    --left;
                } else leftIsRotate = false;

                if (right < 3 && rightIsRotate) {
                    if (state[right][(cur[right] + 2) % 8] == state[right + 1][(cur[right + 1] + 6) % 8]) {
                        rightIsRotate = false;
                    } else nextState[right + 1] += (-nextState[right]);
                    ++right;
                } else rightIsRotate = false;

            }

            for (int i = 0; i < 4; i++) {
                cur[i] = (cur[i] + nextState[i]) % 8;
                if (cur[i] < 0) cur[i] += 8;
            }
        }

        int total = 0;
        for (int i = 0; i < 4; i++) {
            if (state[i][cur[i]]) total += (int) Math.pow(2, i);
        }
        System.out.println(total);

    }

}
