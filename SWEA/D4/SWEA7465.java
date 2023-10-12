import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
/**
 * 
 * @author paternalism532
 *  union & find
 */
public class SWEA7465 {
    static int[] parent;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int test_case = Integer.parseInt(br.readLine());
        StringTokenizer st;
        for(int t = 1 ; t <= test_case ; t++) {
            st = new StringTokenizer(br.readLine(), " ");
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            boolean[] swarm = new boolean[n+1];
            parent = new int[n+1];
            for(int i = 1 ; i <= n ; i++) {
                parent[i] = i;
            }
            for(int i = 0 ; i < m ; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                union(Integer.parseInt(st.nextToken()) , Integer.parseInt(st.nextToken()));
            }
            int total = 0;
            for(int i = 1 ; i <= n ; i++) {
                parent[i] = find(i);
                if(!swarm[parent[i]]) {
                    swarm[parent[i]] = true;
                    ++total;
                }
            }
            bw.write("#"+t+" "+total+"\n");
        }
        bw.flush();
        bw.close();
    }
    static int find(int a) {
        if(parent[a]==a) return a;
        return find(parent[a]);
    }
    static boolean union(int a, int b) {
        int aRoot = find(a);
        int bRoot = find(b);
        if(aRoot==bRoot) return false;
        if(aRoot > bRoot) parent[aRoot] = bRoot;
        else parent[bRoot] = aRoot;
        return true;
    }
}
