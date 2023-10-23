import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;
/**
 * 
 * @author yeogeru
 * 
 * 비트마스킹, BFS 활용 구현
 * 
 */
public class BOJ01194 {
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine(), " ");
    	int[] dtx = {-1, 1, 0, 0};
    	int[] dty = {0, 0, -1, 1};
    	int n = Integer.parseInt(st.nextToken());
    	int m = Integer.parseInt(st.nextToken());
    	char[][] map = new char[n][m];
    	int stx = 0, sty = 0;
    	for(int i = 0 ; i < n ; i++) {
    		String input = br.readLine();
    		for(int j = 0 ; j < m ; j++) {
    			map[i][j] = input.charAt(j);
    			if(map[i][j]=='0') {
    				stx = i;
    				sty = j;
    				map[i][j]='.';
    			}
    		}
    	}
    	boolean [][][] visit = new boolean[1<<6][n][m];
    	Deque<int[]> que = new ArrayDeque<>();
    	que.offer(new int[] {stx, sty, 0, 0});
    	visit[0][stx][sty] = true;
    	int total = 0;
    	boolean escaped = false;
    	Main:
    	while(!que.isEmpty()) {
    		int[] temp = que.poll();
			if(map[temp[0]][temp[1]]>='a' && map[temp[0]][temp[1]]<='f')
			temp[2]|=(1<<(map[temp[0]][temp[1]]-'a'));
			for(int d = 0 ; d < 4 ; d++) {
				int dx = temp[0]+dtx[d];
				int dy = temp[1]+dty[d];
				if(dx<0 || dx>n-1 || dy<0 || dy>m-1) continue;
				if(map[dx][dy]=='1') {
					escaped = true;
					total = temp[3]+1;
					break Main;
				}
				if(visit[temp[2]][dx][dy] || map[dx][dy]=='#') continue;
				if(map[dx][dy] >= 'A' && map[dx][dy] <= 'F') 
				if((temp[2]&(1<<(map[dx][dy]-'A'))) == 0) continue;
				visit[temp[2]][dx][dy] = true;
				que.offer(new int[] {dx, dy, temp[2], temp[3]+1});
			}
    	}
    	if(!escaped) total = -1;
    	System.out.println(total);
    }
}
