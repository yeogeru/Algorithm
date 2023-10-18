import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.StringTokenizer;
/**
 * 
 * @author paternalism532
 *	그래프 탐색 구현
 */
public class BOJ2458 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");;
        List<List<Integer>> list = new ArrayList<>();
        Deque<Integer> que = new ArrayDeque<>();
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int total = 0;
        for(int i = 0 ; i <= n ; i++) list.add(new ArrayList<>());
        int[] count = new int[n+1];
        for(int i = 0 ; i < m ; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            list.get(a).add(b);
        }
        for(int i = 1 ; i <= n; i++) {
            boolean[] visit = new boolean[n+1];
            que.add(i);
            visit[i] = true;
            while(!que.isEmpty()) {
                int temp = que.poll();
                ++count[i];
                ++count[temp];
                for(int j = 0 ; j < list.get(temp).size() ; j++) {
                    int temp_ = list.get(temp).get(j);
                    if(visit[temp_]) continue;
                    visit[temp_] = true;
                    que.add(temp_);
                }
            }
        }
        for(int i = 1 ; i <= n ; i++) if(count[i]==n+1) ++total;
        System.out.println(total);
    }
}
