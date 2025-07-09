import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 * @author : Yeogeru
 * @description : Data Structure, Tree
 * @since : 2025-07-08
 */
public class BOJ01068_G5 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        List<List<Integer>> tree = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            tree.add(new ArrayList<>());
        }

        int root = 0;
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < n; i++) {
            int t = Integer.parseInt(st.nextToken());
            if (t == -1) {
                root = i;
                continue;
            }
            tree.get(t).add(i);
        }

        int target = Integer.parseInt(br.readLine());
        int result = 0;
        Queue<Integer> que = new ArrayDeque<>();
        if (root != target) que.add(root);
        while (!que.isEmpty()) {
            int t = que.poll();
            if (tree.get(t).isEmpty()) ++result;
            for (int i : tree.get(t)) {
                if (i == target) {
                    if (tree.get(t).size() == 1) ++result;
                    continue;
                }
                que.add(i);
            }
        }

        System.out.println(result);

    }

}