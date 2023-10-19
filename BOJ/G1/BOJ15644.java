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
 *         DFS & DP
 */
public class BOJ15644 {
	static class Marble {
		int rx, ry, bx, by;
		boolean redGoal, blueGoal;
		List<Integer> moveList;
		public Marble(int rx, int ry, int bx, int by, boolean redGoal, boolean blueGoal, List<Integer> moveList) {
			super();
			this.rx = rx;
			this.ry = ry;
			this.bx = bx;
			this.by = by;
			this.redGoal = redGoal;
			this.blueGoal = blueGoal;
			this.moveList = moveList;
		}
	}
	static int[][] dt = {{-1,0},{1,0},{0,-1},{0,1}};
	static char[][] graph;
	static int n, m, px = 0, py = 0;
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	String[] input = (br.readLine()).split(" ");
    	n = Integer.parseInt(input[0]);
    	m = Integer.parseInt(input[1]);
    	graph = new char[n][m];
    	boolean[][][][] visit = new boolean[n][m][n][m];
    	Marble init = new Marble(0, 0, 0, 0, false, false, new ArrayList<>());
    	
    	for(int i = 0 ; i < n ; i++) {
    		String input_ = br.readLine();
    		for(int j = 0 ; j < m ; j++) {
    			graph[i][j] = input_.charAt(j);
    			if(graph[i][j]=='R') {
    				init.rx = i;
    				init.ry = j;
    				graph[i][j]='.';
    			} else if(graph[i][j]=='B') {
    				init.bx = i;
    				init.by = j;
    				graph[i][j]='.';
    			} else if(graph[i][j]=='O') {
    				px = i;
    				py = j;
    				graph[i][j]='.';
    			} 
    		}
    	}
    	Queue<Marble> que = new LinkedList<Main.Marble>();
    	List<Integer> moveResult = new ArrayList<>();
    	que.add(init);
    	visit[init.rx][init.ry][init.bx][init.by] = true; 
    	int depth = 0;
    	boolean escapeSuccess = false;
    	List<Integer> tempList = new ArrayList<Integer>();
    	Loop:
    	while(!que.isEmpty()) {
    		int queSize = que.size();
    		for(int i = 0 ; i < queSize ; i++) {
    			Marble temp = que.poll();
    			Marble output;
    			for(int d = 0 ; d < 4 ; d++) {
    				output = new Marble(temp.rx, temp.ry, temp.bx, temp.by, false, false, new ArrayList<>());
    				for(int j = 0 ; j < temp.moveList.size() ; j++) {
        				output.moveList.add(temp.moveList.get(j));
    				}
    				output = moveMarble(output, d);
    				if(output.blueGoal) {
    					visit[output.rx][output.ry][output.bx][output.by] = true;
    					continue;
    				}
    				if(output.redGoal && !output.blueGoal) {
    					depth++;
    					escapeSuccess = true;
    					output.moveList.add(d);
    					for(int j = 0 ; j < output.moveList.size() ; j++) {
    						moveResult.add(output.moveList.get(j));
    					}
    					break Loop;
    				}
    				tempList.clear();
    				if(visit[output.rx][output.ry][output.bx][output.by]) continue;
					visit[output.rx][output.ry][output.bx][output.by] = true;
					Marble nextMarble = new Marble(output.rx, output.ry, output.bx, output.by, false, false, new ArrayList<>());
					for(int j = 0 ; j < output.moveList.size() ; j++) {
						nextMarble.moveList.add(output.moveList.get(j));
					}
					nextMarble.moveList.add(d);
					que.add(nextMarble);
    			}
    		}
    		if(++depth>=10) break;
    	}
    	if(!escapeSuccess) depth = -1;
    	System.out.println(depth);
    	for(int i = 0 ; i < moveResult.size() ; i++) {
    		char resultTemp='.';
    		switch(moveResult.get(i)) {
    		case 0: resultTemp = 'U';
    		break;
    		case 1: resultTemp = 'D';
    		break;
    		case 2: resultTemp = 'L';
    		break;
    		case 3: resultTemp = 'R';
    		break;
    		}
    		System.out.print(resultTemp);
    	}
    }
    static boolean movePossible(int x, int y) {
    	if(x < 0 || x > n-1 || y < 0 || y > m-1) return false;
    	return true;
    }
    static Marble moveMarble(Marble temp, int d) {
    	char[][] tempMap = new char[n][m];
    	int rx = temp.rx;
    	int ry = temp.ry;
    	int bx = temp.bx;
    	int by = temp.by;
    	for(int i = 0 ; i < n ; i++) {
    		for(int j = 0 ; j < m ; j++) {
    			tempMap[i][j] = graph[i][j];
    		}
    	}
    	tempMap[rx][ry] = 'R';
    	tempMap[bx][by] = 'B';
    	boolean redFirst = false;
    	if(d==0) {
    		if(bx > rx) redFirst = true;
    	}
    	else if(d==1) {
    		if(bx < rx) redFirst = true;
    	}
    	else if(d==2) {
    		if(by > ry) redFirst = true;
    	}
    	else if(d==3) {
    		if(by < ry) redFirst = true;
    	}
    	if(redFirst) {
    		tempMap[rx][ry] = '.';
    		while(movePossible(rx+dt[d][0], ry+dt[d][1])) {
    	    	if(tempMap[rx+dt[d][0]][ry+dt[d][1]]!='.') break;
        		rx+=dt[d][0];
        		ry+=dt[d][1];
        		if(rx==px && ry==py) {
        			temp.redGoal = true;
        			break;
        		}
        	}
    		if(!temp.redGoal) tempMap[rx][ry] = 'R';
    		tempMap[bx][by] = '.';
        	while(movePossible(bx+dt[d][0], by+dt[d][1])) {
    	    	if(tempMap[bx+dt[d][0]][by+dt[d][1]]!='.') break;
        		bx+=dt[d][0];
        		by+=dt[d][1];
        		if(bx==px && by==py) {
        			temp.blueGoal = true;
        			break;
        		}
        	}
        	tempMap[bx][by] = 'B';
    	} else {
    		tempMap[bx][by] = '.';
        	while(movePossible(bx+dt[d][0], by+dt[d][1])) {
    	    	if(tempMap[bx+dt[d][0]][by+dt[d][1]]!='.') break;
        		bx+=dt[d][0];
        		by+=dt[d][1];
        		if(bx==px && by==py) {
        			temp.blueGoal = true;
        			break;
        		}
        	}
    		if(!temp.blueGoal) tempMap[bx][by] = 'B';
        	tempMap[rx][ry] = '.';
    		while(movePossible(rx+dt[d][0], ry+dt[d][1])) {
    	    	if(tempMap[rx+dt[d][0]][ry+dt[d][1]]!='.') break;
        		rx+=dt[d][0];
        		ry+=dt[d][1];
        		if(rx==px && ry==py) {
        			temp.redGoal = true;
        			break;
        		}
        	}
    		tempMap[rx][ry] = 'R';
    	}
    	temp.rx = rx;
    	temp.ry = ry;
    	temp.bx = bx;
    	temp.by = by;
    	return temp;
    }
}
