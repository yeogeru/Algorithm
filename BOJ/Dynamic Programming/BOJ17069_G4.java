import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
/**
 * 
 * @author yeogeru
 * 		DFS & DP
 */
public class BOJ17069_G4 {
	static int[] dtx = {0, 1, 1};
	static int[] dty = {1, 0, 1};
	static boolean[][] map;
	static int n;
	static long[][][] dp;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		n = Integer.parseInt(br.readLine());
		StringTokenizer st;
		map = new boolean[n][n];
		dp = new long[3][n][n];
		for(int i = 0 ; i < n ; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j = 0 ; j < n ; j++) {
				if(st.nextToken().equals("1")) map[i][j] = true;
			}
		}
		System.out.println(pipe(0,0,0,1,0));
	}
	
	static long pipe(int px, int py, int x, int y, int direction) {
		if(dp[direction][x][y]!=0) return dp[direction][x][y];
		for(int d = 0 ; d < 3 ; d++) {
			if((direction==0 && d==1)||(direction==1 && d==0)) continue;
			int dx = x+dtx[d];
			int dy = y+dty[d];
			if(dx < 0 || dx >= n || dy < 0 || dy >= n) continue;
			if(d!=2) {
				if(map[dx][dy]) continue;
			} else {
				if(map[x+dtx[0]][y+dty[0]] || map[x+dtx[1]][y+dty[1]] || map[dx][dy]) continue;
			}
			if(dx==n-1 && dy==n-1) ++dp[direction][x][y];
			else dp[direction][x][y]+=pipe(x, y, dx, dy, d);
		}
		return dp[direction][x][y];
	}
}
