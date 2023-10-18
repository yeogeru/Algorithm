import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;
/**
 * 
 * @author paternalism532
 * 		BFS
 */
public class BOJ03055 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int[] dtx = {-1, 1, 0, 0};
		int[] dty = {0, 0, -1, 1};
		int r = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		int stx = 0, sty = 0, destx = 0, desty = 0;
		boolean escaped = false;
		int total = 0;
		boolean[][] visit = new boolean[r][c];
		boolean[][] rock = new boolean[r][c];
		Deque<int[]> que = new ArrayDeque<>();
		Deque<int[]> quew = new ArrayDeque<>();
		for(int i = 0 ; i < r ; i++) {
			String input = br.readLine();
			for(int j = 0 ; j < c ; j++) {
				if(input.charAt(j)=='S') {
					stx = i;
					sty = j;
				} else if(input.charAt(j)=='D') {
					destx = i;
					desty = j;
				} else if(input.charAt(j)=='*') {
					visit[i][j] = true;
					quew.offer(new int[] {i, j});
				} else if(input.charAt(j)=='X') {
					rock[i][j] = true;
				}
			}
		}
		que.add(new int[] {stx, sty, 0});
		visit[stx][sty] = true;
		Main:
		while(!que.isEmpty()) {
			int quesize = que.size();
			int watersize = quew.size();
			for(int i = 0 ; i < watersize ; i++) {
				int[] tempw = quew.poll();
				for(int d = 0 ; d < 4 ; d++) {
					int dx = tempw[0]+dtx[d];
					int dy = tempw[1]+dty[d];
					if(dx < 0 || dx >= r || dy < 0 || dy >= c) continue;
					if(visit[dx][dy] || rock[dx][dy] || (dx==destx && dy==desty)) continue;
					visit[dx][dy] = true;
					quew.offer(new int[] {dx, dy});
				}
			}
			for(int i = 0 ; i < quesize ; i++) {
				int[] temp = que.poll();
				for(int d = 0 ; d < 4 ; d++) {
					int dx = temp[0]+dtx[d];
					int dy = temp[1]+dty[d];
					if(dx < 0 || dx >= r || dy < 0 || dy >= c) continue;
					if(dx==destx && dy==desty) {
						escaped = true;
						total = temp[2]+1;
						break Main;
					}
					if(visit[dx][dy] || rock[dx][dy] || visit[dx][dy]) continue;
					visit[dx][dy] = true;
					que.offer(new int[] {dx, dy, temp[2]+1});
				}
			}
		}
		if(escaped) System.out.println(total);
		else System.out.println("KAKTUS");
	}
}
