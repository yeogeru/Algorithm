import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
 /**
 * 
 * @author yeogeru
 *         BFS 그래프탐색 구현
 */
public class BOJ16234 {
	static int n;
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	String[] input = (br.readLine()).split(" ");
    	n = Integer.parseInt(input[0]);
    	int l = Integer.parseInt(input[1]);
    	int r = Integer.parseInt(input[2]);
    	int[][] dt = {{-1,0},{1,0},{0,-1},{0,1}};
    	int[][] map = new int[n][n];
    	for(int i = 0 ; i < n ; i++) {
    		input = (br.readLine()).split(" ");
    		for(int j = 0 ; j < n ; j++) {
    			map[i][j] = Integer.parseInt(input[j]);
    		}
    	}
    	boolean isPossible = true;
    	int cnt = 0;
    	while(isPossible) {
    		isPossible = false;
        	boolean[][] visit = new boolean[n][n];
        	Queue<Point> que = new LinkedList<Point>();
			List<Point> list = new ArrayList<Point>();
    		for(int i = 0 ; i < n ; i++) {
    			for(int j = 0 ; j < n ; j++) {
    				if(!visit[i][j]) {
        				int total = 0;
        				int totalIdx = 0;
        				que.add(new Point(i, j));
    					visit[i][j] = true;
    					list.clear();
        				while(!que.isEmpty()) {
        					Point temp = que.poll();
        					list.add(new Point(temp.x, temp.y));
        					total+=map[temp.x][temp.y];
        					totalIdx++;
        					for(int d = 0 ; d < 4 ; d++) {
        						int dx = temp.x+dt[d][0];
        						int dy = temp.y+dt[d][1];
        						if(!movePossible(dx, dy)) continue;
        						int diff = Math.abs(map[temp.x][temp.y]-map[dx][dy]);
        						if(visit[dx][dy]) continue;
        						if(diff >= l && diff <= r) {
                					visit[dx][dy] = true;
            						que.add(new Point(dx, dy));
        						}
        					}
        				}
        				if(list.size()==1) continue;
        				isPossible = true;
        				int temp = total / totalIdx;
        				for(int k = 0 ; k < list.size() ; k++) {
        					map[list.get(k).x][list.get(k).y] = temp;
        				}
        			}
    			}
    		}
    		if(isPossible) cnt++;
    	}
    	System.out.println(cnt);
    	
    }
    
    static boolean movePossible(int x, int y) {
    	if(x < 0 || x > n-1 || y < 0 || y > n-1) return false;
    	return true;
    }
}
