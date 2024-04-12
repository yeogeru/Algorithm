import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
/**
 * @author yeogeru
 *	  BackTracking, Implementation
 */
public class BOJ02239_G4 {
	static class Sudoku {
		int x, y, pos;
		public Sudoku(int x, int y, int pos) {
			super();
			this.x = x;
			this.y = y;
			this.pos = pos;
		}
	}
	static int[][] graph;
	static List<Sudoku> sudoku;
	static boolean madeSudoku = false;
	static String result = "";
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    graph = new int[9][9];
	    String input;
	    sudoku = new ArrayList<>();
	    for(int i = 0 ; i < 9 ; i++) {
	    	input = br.readLine();
	    	for(int j = 0 ; j < 9 ; j++) {
	    		graph[i][j] = (int)(input.charAt(j)-'0');
	    		if(graph[i][j]==0) {
	    		sudoku.add(new Sudoku(i, j, 0));
	    		}
	    	}
	    }
	    for(int i = 0 ; i < sudoku.size() ; i++) {
	    	int x = sudoku.get(i).x;
	    	int y = sudoku.get(i).y;
	    	int cnt = 0;
	    	for(int j = 0 ; j < 9 ; j++) {
	    		if(graph[x][j] > 0) {
	    			if((sudoku.get(i).pos&(1<<graph[x][j]))==0) cnt++;
		    		sudoku.get(i).pos|=(1<<graph[x][j]);
		    	}
	    	}
	    	for(int j = 0 ; j < 9 ; j++) {
	    		if(graph[j][y] > 0) {
	    			if((sudoku.get(i).pos&(1<<graph[j][y]))==0) cnt++;
	    			sudoku.get(i).pos|=(1<<graph[j][y]);
	    		}
	    	}
	    	int part_x = (x/3)*3;
	    	int part_y = (y/3)*3;
	    	for(int j = part_x ; j < part_x+3 ; j++) {
	    		for(int k = part_y ; k < part_y+3 ; k++) {
	    			if(graph[j][k] > 0) {
	    				if((sudoku.get(i).pos&(1<<graph[j][k]))==0) cnt++;
	    				sudoku.get(i).pos|=(1<<graph[j][k]);
	    			}
	    		}
	    	}
	    	if(cnt==8) {
	    		for(int j = 1 ; j <= 9 ; j++) {
	    			if((sudoku.get(i).pos&(1<<j))==0) {
	    				graph[x][y] = j;
	    				sudoku.remove(i--);
	    				break;
	    			}
	    		}
	    	}
	    }
	    makeSudoku(0);
	    for(int i = 0 ; i < 9 ; i++) {
	    	System.out.println(result.substring(9*i, 9*i+9));
	    }
    }
    
    public static void makeSudoku(int index) {
    	if(madeSudoku) return;
    	if(index==sudoku.size()) {
    		StringBuilder sb = new StringBuilder();
    		madeSudoku = true;
			for(int j = 0 ; j < 9 ; j++) {
				for(int k = 0 ; k < 9 ; k++) {
					sb.append(graph[j][k]);
				}
			}
			result = sb.toString();
    		return;
    	}
    	Loop:
    	for(int i = 1 ; i <= 9 ; i++) {
    		Sudoku temp = sudoku.get(index);
    		if((temp.pos&(1<<i)) == 0) {
    			for(int j = 0 ; j < 9 ; j++) if(graph[temp.x][j] == i) continue Loop;
    			for(int j = 0 ; j < 9 ; j++) if(graph[j][temp.y] == i) continue Loop;
		    	int part_x = (temp.x/3)*3;
		    	int part_y = (temp.y/3)*3;
		    	for(int j = part_x ; j < part_x+3 ; j++) {
		    		for(int k = part_y ; k < part_y+3 ; k++) {
		    			if(graph[j][k] == i) continue Loop;
		    		}
		    	}
		    	graph[temp.x][temp.y] = i;
    			makeSudoku(index+1);
		    	graph[temp.x][temp.y] = 0;
    		}
    	}
    }
}
