import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
/**
 * 
 * @author paternalism532
 *
 *	조합, BFS
 */
public class BOJ01941_G3 {
	static boolean[][] map = new boolean[5][5];
	static int result = 0;
	static int[][] dt = {{-1,0},{1,0},{0,-1},{0,1}};
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	for(int i = 0 ; i < 5 ; i++) {
    		String input = br.readLine();
    		for(int j = 0 ; j < 5 ; j++) {
    			if(input.charAt(j)=='S') map[i][j] = true;
    		}
    	}
    	makecombination(0, 0, new int[7], 0, new boolean[25]);
    	System.out.println(result);
    }
    static int test = 0;
    static void makecombination(int index, int start, int[] arr, int total, boolean[] arrvisit) {
    	if((index==6 && total < 3) || (index==5 && total < 2) || (index==4 && total < 1)) return;
    	if(index==7) {
    		if(total < 4) return;
    		Queue<Point> que = new LinkedList<>();
    		int visitcnt = 0;
    		boolean[][] visit = new boolean[5][5];
    		que.offer(new Point(arr[0]/5, arr[0]%5));
    		visit[arr[0]/5][arr[0]%5] = true;
    		while(!que.isEmpty()) {
    			Point temp = que.poll();
				visitcnt++;
    			for(int d = 0 ; d < 4 ; d++) {
    				for(int f = 0 ; f < 7 ; f++) {
    					if(visit[arr[f]/5][arr[f]%5]) continue;
    					if(temp.x+dt[d][0]==arr[f]/5 && temp.y+dt[d][1]==arr[f]%5) {
    						visit[arr[f]/5][arr[f]%5] = true;
    						que.offer(new Point(arr[f]/5, arr[f]%5));
    					}
    				}
    			}
    		}
    		if(visitcnt==7) result++;
    		return;
    	}
    	for(int i = start ; i < 25 ; i++) {
    		if(arrvisit[i]) continue;
    		arrvisit[i] = true;
    		arr[index] = i;
    		if(map[i/5][i%5]) makecombination(index+1, i+1, arr, total+1, arrvisit);
    		else makecombination(index+1, i+1, arr, total, arrvisit);
			arrvisit[i] = false;
    	}
    }
}
