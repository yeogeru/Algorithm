import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
/**
 * 
 * @author paternalism532
 *
 *	BFS
 */
public class BOJ07576 {
	static class Point {
		int x, y;
		public Point(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}
	static int n, m;
	public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	String[] input = (br.readLine()).split(" ");
    	int[][] dt = {{-1,0},{1,0},{0,-1},{0,1}};
    	m = Integer.parseInt(input[0]);
    	n = Integer.parseInt(input[1]);
    	boolean[][] visit = new boolean[n][m];
    	Queue<Point> que = new LinkedList<Main.Point>();
    	int[][] tomato = new int[n][m];
    	int noripe = 0;
    	for(int i = 0 ; i < n ; i++) {
    		input = (br.readLine()).split(" ");
    		for(int j = 0 ; j < m ; j++) {
    			tomato[i][j] = Integer.parseInt(input[j]);
    			if(tomato[i][j]==1) {
    				visit[i][j] = true;
    				que.add(new Point(i, j));
    			} else if(tomato[i][j]==0) noripe++;
    		}
    	}
    	int day = -1;
    	while(!que.isEmpty()) {
    		int quesize = que.size();
    		for(int q = 0 ; q < quesize ; q++) {
    			Point temp = que.poll();	
    			for(int d = 0 ; d < 4 ; d++) {
    				int dx = temp.x+dt[d][0];
    				int dy = temp.y+dt[d][1];
    				if(!outofmap(dx, dy)) {
    					if(visit[dx][dy] || tomato[dx][dy]!=0) continue;
    					visit[dx][dy] = true;
    					noripe--;
    					que.offer(new Point(dx, dy));
    				}
    			}
    		}
    		day++;
    	}
    	if(noripe!=0) System.out.println(-1);
    	else System.out.println(day);
    }
	
	static boolean outofmap(int x, int y) {
		if(x<0 || x>n-1 || y<0 || y>m-1) return true;
		return false;
	}
}
