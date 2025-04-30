import java.io.*;
import java.util.*;

/**
 * @author : Yeogeru
 * @description : Data Structure, Stack
 * @since : 2025-04-30
 */

public class BOJ10828_S4 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        Stack<Integer> stack = new Stack<>();
        int test_case = Integer.parseInt(br.readLine());
        for (int t = 0; t < test_case; t++) {
            st = new StringTokenizer(br.readLine(), " ");
            String command = st.nextToken();
            switch (command) {
                case "push":
                    int n = Integer.parseInt(st.nextToken());
                    stack.push(n);
                    break;
                case "pop":
                    if (!stack.isEmpty()) bw.write(stack.pop() + "\n");
                    else bw.write("-1\n");
                    break;
                case "size":
                    bw.write(stack.size() + "\n");
                    break;
                case "empty":
                    bw.write((stack.isEmpty() ? 1 : 0) + "\n");
                    break;
                case "top":
                    bw.write((!stack.isEmpty() ? stack.peek() : -1) + "\n");
                    break;
            }
        }
        bw.flush();
        bw.close();
    }
}