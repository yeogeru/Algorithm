import java.io.*;
import java.util.*;

/**
 * @author      : Yeogeru
 * @since       : 2025-03-19
 * @description : BOJ_20055 컨테이너 벨트 위의 로봇
 */

public class BOJ20055_G5 {
    static int N, K;
    static int[] durability;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        // 벨트의 내구도
        durability = new int[2 * N];
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < (2 * N); i++) {
            durability[i] = Integer.parseInt(st.nextToken());
        }

        System.out.println(play());
    }

    public static int play() {
        Queue<Integer> robot = new ArrayDeque<>();
        boolean[] belt_zero = new boolean[2 * N];
        int belt_count = 0;
        int result = 0;

        while (belt_count < K) {
            // 벨트 회전
            ++result;
            int first_index = (2 * N) - ((result - 1) % (2 * N)) - 1;
            int qSize = robot.size();
            for (int i = 0; i < qSize; i++) {
                int t = robot.poll();
                if (t + 1 == N - 1) continue;
                robot.add(t + 1);
            }

            // 로봇 이동
            qSize = robot.size();
            int last_index = -10;
            for (int i = 0; i < qSize; i++) {
                int t = robot.poll();
                int next_index = (first_index + t + 1) % (2 * N);
                if(t + 1 == last_index) {
                    robot.add(t);
                    last_index = t;
                    continue;
                }
                last_index = t;
                if (durability[next_index] == 0) {
                    robot.add(t);
                    continue;
                }
                if (--durability[next_index] == 0 && !belt_zero[next_index]) {
                    belt_zero[next_index] = true;
                    ++belt_count;
                }
                last_index = t + 1;
                if (t + 1 == N - 1) continue;
                robot.add(t + 1);
            }

            // 로봇 올리기
            if (durability[first_index] > 0) {
                robot.add(0);
                if (--durability[first_index] == 0 && !belt_zero[first_index]) {
                    belt_zero[first_index] = true;
                    ++belt_count;
                }
            }
        }
        return result;
    }

}
