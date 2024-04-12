import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
/**
 * @author yeogeru
 *	  Brute Force, Implementation
 */
public class BOJ14500_G4 {
	static int[][] dt = {{-1,0},{1,0},{0,-1},{0,1}};
	static int[][] graph;
	static int total = Integer.MIN_VALUE;
	static int n, m;
	static int cnt = 0;
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	String[] input = (br.readLine()).split(" ");
    	
    	n = Integer.parseInt(input[0]);
    	m = Integer.parseInt(input[1]);
    	graph = new int[n][m];
    	for(int i = 0 ; i < n ; i++) {
    		input = (br.readLine()).split(" ");
    		for(int j = 0 ; j < m ; j++) {
    			graph[i][j] = Integer.parseInt(input[j]);
    		}
    	}
    	boolean[][] visit = new boolean[n][m];
    	for(int i = 0 ; i < n ; i++) {
    		for(int j = 0 ; j < m ; j++) {
    			visit[i][j] = true;
    			dfs(1, i, j, 0, graph[i][j], visit);
    			visit[i][j] = false;
    		}
    	}
    	System.out.println(total);
    }
    
    static void dfs(int index, int x, int y, int delt, int sum, boolean[][] visit) {
    	if(index==4) {
    		total = Math.max(total, sum);
    		return;
    	}
    	int dx, dy, dx_, dy_;
    	if(index==2) {
    		for(int d = 0 ; d < 4 ; d++) {
    			if(d==delt) continue;
    			for(int d_ = 0 ; d_ < 4 ; d_++) {
    				if(d==d_ || d_==delt) continue;
    	    		dx = x+dt[d][0];
    	    		dy = y+dt[d][1];
    	    		dx_ = x+dt[d_][0];
    	    		dy_ = y+dt[d_][1];
    	    		if(dx>=0 && dx<n && dy>=0 && dy<m) {
    	    			if(dx_>=0 && dx_<n && dy_>=0 && dy_<m) {
    	    				if(visit[dx][dy] || visit[dx_][dy_]) continue;
        	    			total = Math.max(total, sum+graph[dx][dy]+graph[dx_][dy_]);
        	    		}
    	    		}
    				
    			}
    		}
    	}
    	for(int d = 0 ; d < 4 ; d++) {
    		dx = x+dt[d][0];
    		dy = y+dt[d][1];
    		if(dx>=0 && dx<n && dy>=0 && dy<m) {
    			if(visit[dx][dy]) continue;
        		if(d%2==0) delt=d+1;
        		else delt=d-1;
        		visit[dx][dy] = true;
    			dfs(index+1, dx, dy, delt, sum+graph[dx][dy], visit);
    			visit[dx][dy] = false;
    		}
    	}
    }
    
}
