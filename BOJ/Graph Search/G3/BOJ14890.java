import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/**
 * 
 * @author yeogeru
 *	    Graph Search
 */
public class BOJ14890 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int n = Integer.parseInt(st.nextToken());
        int x = Integer.parseInt(st.nextToken());
        int[][] graph = new int[n][n];
        for(int i = 0 ; i < n ; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for(int j = 0 ; j < n ; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int total = 0;
        for(int i = 0 ; i < 2 ; i++) {
            Main:
            for(int j = 0 ; j < n ; j++) {
                boolean[] visit = new boolean[n];
                for(int k = 0 ; k < n-1 ; k++) {
                    if(i==0) {
                        if(graph[j][k] < graph[j][k+1]) {
                            for(int o = 1 ; o <= x ; o++) {
                                if(k+1-o < 0) continue Main;
                                if(visit[k+1-o] || graph[j][k+1]-1!=graph[j][k+1-o]) continue Main;
                                visit[k+1-o] = true;
                            }
                        } else if(graph[j][k] > graph[j][k+1]) {
                            for(int o = 1 ; o <= x ; o++) {
                                if(k+o >= n) continue Main;
                                if(visit[k+o] || graph[j][k]-1!=graph[j][k+o]) continue Main;
                                visit[k+o] = true;
                            }
                        }
                    } else if(i==1) {
                        if(graph[k][j] < graph[k+1][j]) {
                            for(int o = 1 ; o <= x ; o++) {
                                if(k+1-o < 0) continue Main;
                                if(visit[k+1-o] || graph[k+1][j]-1!=graph[k+1-o][j]) continue Main;
                                visit[k+1-o] = true;
                            }
                        } else if(graph[k][j] > graph[k+1][j]) {
                            for(int o = 1 ; o <= x ; o++) {
                                if(k+o >= n) continue Main;
                                if(visit[k+o] || graph[k][j]-1!=graph[k+o][j]) continue Main;
                                visit[k+o] = true;
                            }
                        }
                    }
                }
                ++total;
            }
        }
        System.out.println(total);
    }
}
