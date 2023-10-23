import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/**
 * 
 * @author yeogeru
 *	Graph Search, implementation
 */
public class BOJ17144 {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int[] dtx = {-1, 0, 1, 0};
		int[] dty = {0, 1, 0, -1};
		int r = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		int t = Integer.parseInt(st.nextToken());
		int[][] map_original = new int[r][c];
		int[][] aircleaner = new int[2][2];
		for(int i = 0, k = 0 ; i < r ; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j = 0 ; j < c ; j++) {
				map_original[i][j] = Integer.parseInt(st.nextToken());
				if(map_original[i][j]==-1) {
					aircleaner[k][0] = i;
					aircleaner[k][1] = j;
					++k;
				}
			}
		}
		for(int T = 0 ; T < t ; T++) {
			int[][] map_temp = new int[r][c];
			for(int i = 0 ; i < r ; i++) {
				for(int j = 0 ; j < c ; j++) {
					if(map_original[i][j] >= 5) {
						int cnt = 0;
						for(int d = 0 ; d < 4 ; d++) {
							int dx = i+dtx[d];
							int dy = j+dty[d];
							if(dx < 0 || dx >= r || dy < 0 || dy >= c) continue;
							if(map_original[dx][dy]==-1) continue;
							++cnt;
							map_temp[dx][dy]+=map_original[i][j]/5;
						}
						map_temp[i][j]+=map_original[i][j]-((map_original[i][j]/5)*cnt);
					} else if(map_original[i][j] > 0) map_temp[i][j]+=map_original[i][j];
				}
			}
			for(int p = 0, k = -1 ; p < 2 ; p++, k+=2) {
				int d = 2*p;
				int x = aircleaner[p][0]+k;
				int y = aircleaner[p][1];
				while(!(x==aircleaner[p][0] && y==aircleaner[p][1])) {
					int dx = x+dtx[(d+2)%4];
					int dy = y+dty[(d+2)%4];
					map_temp[dx][dy] = map_temp[x][y];
					map_temp[x][y] = 0;
					dx = x+dtx[d];
					dy = y+dty[d];
					if(p==0 && (dx < 0 || dx >= r || dy < 0 || dy >= c || dx > aircleaner[p][0])) d=(d-k+4)%4;
					else if(p==1 && (dx < 0 || dx >= r || dy < 0 || dy >= c || dx < aircleaner[p][0])) d=(d-k+4)%4;
					x+=dtx[d];
					y+=dty[d];
				}
			}
			for(int i = 0 ; i < r ; i++) map_original[i] = map_temp[i].clone();
			for(int k = 0 ; k < 2 ; k++) map_original[aircleaner[k][0]][aircleaner[k][1]] = -1;
		}
		int total = 2;
		for(int i = 0 ; i < r ; i++) {
			for(int j = 0 ; j < c ; j++) {
				total+=map_original[i][j];
			}
		}
		System.out.println(total);
	}
}
