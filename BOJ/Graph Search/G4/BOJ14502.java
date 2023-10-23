import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;
/**
 * 
 * @author yeogeru
 *
 *	BFS, Combination implementation
 */
public class BOJ14502 {
	static int[][] map;
	static List<int[]> list = new ArrayList<>();
	static List<int[]> vir = new ArrayList<>();
	static int result = 0;
	static int n, m;
	static int[] dtx = {-1,1,0,0};
	static int[] dty = {0,0,-1,1};
	static int listsize = 0;
	static int[][] arr;
	static boolean test = false;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		map = new int[n][m];
		arr = new int[3][2];
		for(int i = 0 ; i < n ; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j = 0 ; j < m ; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j]==0) list.add(new int[] {i, j});
				if(map[i][j]==2) vir.add(new int[] {i, j});
			}
		}
		listsize = list.size();
		makeWall(0, 0);
		System.out.println(result);
	}
	public static void makeWall(int index, int start) {
		if(index==3) {
			virus();
			return;
		}
		for(int i = start ; i < listsize ; i++) {
			arr[index][0] = list.get(i)[0];
			arr[index][1] = list.get(i)[1];
			makeWall(index+1, i+1);
		}
	}
	
	public static void virus() {
		boolean[][] visit = new boolean[n][m];
		int total = 0;
		for(int i = 0 ; i < 3 ; i++) {
			map[arr[i][0]][arr[i][1]] = 1;
		}
		Queue<int[]> que = new LinkedList<>();
		for(int i = 0 ; i < vir.size() ; i++) {
			if(visit[vir.get(i)[0]][vir.get(i)[1]]) continue;
			que.add(new int[] {vir.get(i)[0], vir.get(i)[1]});
			visit[vir.get(i)[0]][vir.get(i)[1]] = true;
			while(!que.isEmpty()) {
				int[] temp = que.poll();
				for(int d = 0 ; d < 4 ; d++) {
					int dx = temp[0]+dtx[d];
					int dy = temp[1]+dty[d];
					if(isPossible(dx, dy)) {
						if(visit[dx][dy] || map[dx][dy]==1) continue;
						visit[dx][dy] = true;
						que.add(new int[] {dx, dy});
					}
				}
			}
		}
		for(int i = 0 ; i < listsize ; i++) {
			int x = list.get(i)[0];
			int y = list.get(i)[1];
			if(!visit[x][y]) total++;
		}
		for(int i = 0 ; i < 3 ; i++) {
			map[arr[i][0]][arr[i][1]] = 0;
		}
		result = Math.max(result, total-3);
	}
	
	public static boolean isPossible(int x, int y) {
		if(x < 0 || x >= n || y < 0 || y >= m) return false;
		return true;
	}
}
