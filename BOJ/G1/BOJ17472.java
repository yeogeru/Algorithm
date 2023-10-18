import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;
/**
 * 
 * @author paternalism532
 *
 *	BFS, MST 활용
 */
public class BOJ17472 {
	static class Bridge implements Comparable<Bridge>{
		int start, dest, leng;
		public Bridge(int start, int dest, int leng) {
			super();
			this.start = start;
			this.dest = dest;
			this.leng = leng;
		}
		@Override
		public int compareTo(Bridge o) {
			return Integer.compare(this.leng, o.leng);
		}
	}
	static int[] parent;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int[] dtx = {-1, 1, 0, 0};
		int[] dty = {0, 0, -1, 1};
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		boolean[][] graph = new boolean[n][m];
		for(int i = 0 ; i < n ;  i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j = 0 ; j < m ; j++) {
				if(st.nextToken().equals("1")) {
					graph[i][j] = true;
				}
			}
		}
		Deque<int[]> que = new ArrayDeque<>();
		List<List<int[]>> list = new ArrayList<>();
		HashMap<Point, Integer> map = new HashMap<>();
		List<Bridge> bridge = new ArrayList<>();
		boolean[][] visit = new boolean[n][m];
		int cnt = -1;
		// 각 섬 index와 좌표 도출
		for(int i = 0 ; i < n ; i++) {
			for(int j = 0 ; j < m ; j++) {
				if(!visit[i][j] && graph[i][j]) {
					visit[i][j] = true;
					que.add(new int[] {i, j});
					list.add(new ArrayList<>());
					++cnt;
					while(!que.isEmpty()) {
						int[] temp = que.poll();
						list.get(cnt).add(temp);
						map.put(new Point(temp[0], temp[1]), cnt);
						for(int d = 0 ; d < 4 ; d++) {
							int dx = temp[0]+dtx[d];
							int dy = temp[1]+dty[d];
							if(dx < 0 || dx >= n || dy < 0 || dy >= m) continue;
							if(graph[dx][dy] && !visit[dx][dy]) {
								visit[dx][dy] = true;
								que.add(new int[] {dx, dy});
							}
						}
					}
				}
			}
		}
		parent = new int[cnt+1];
		for(int i = 0 ; i <= cnt; i++) {
			parent[i] = i;
		}
		// 각 섬의 간선 도출
		for(int i = 0 ; i < list.size() ; i++) {
			for(int j = 0 ; j < list.get(i).size() ; j++) {
				int tx = list.get(i).get(j)[0];
				int ty = list.get(i).get(j)[1];
				if(graph[tx][ty]) {
					for(int d = 0 ; d < 4 ; d++) {
						int dx = tx + dtx[d];
						int dy = ty + dty[d];
						int bl = -1;
						Main:
						while(!(dx < 0 || dx >= n || dy < 0 || dy >= m)) {
							++bl;
							if(graph[dx][dy]) {
								int stidx = map.get(new Point(tx, ty));
								int dsidx = map.get(new Point(dx, dy));
								if(stidx==dsidx || bl<=1) break;
								if(dsidx < stidx) {
									int tidx = stidx;
									stidx = dsidx;
									dsidx = tidx;
								}
								for(int k = 0 ; k < bridge.size() ; k++) {
									if(bridge.get(k).start==stidx && bridge.get(k).dest==dsidx) {
										if(bl < bridge.get(k).leng) bridge.set(k, new Bridge(stidx, dsidx, bl));
										break Main;
									}
								}
								bridge.add(new Bridge(stidx, dsidx, bl));
								break;
							}
							dx+=dtx[d];
							dy+=dty[d];
						}
					}
				}
			}
		}
		Collections.sort(bridge);
		int total = 0;
		for(int i = 0 ; i < bridge.size() ; i++) {
			if(union(bridge.get(i).start, bridge.get(i).dest)) {
				total+=bridge.get(i).leng;
			}
		}
		boolean isLinked = true;
		for(int i = 0 ; i <= cnt ; i++) {
			parent[i] = find(i);
			if(parent[i]!=0) {
				isLinked = false;
				break;
			}
		}
		if(isLinked) {
			System.out.println(total);
		} else System.out.println(-1);
	}
	static int find(int a) {
		if(parent[a]==a) return a;
		return find(parent[a]);
	}
	static boolean union(int a, int b) {
		int aroot = find(a);
		int broot = find(b);
		if(aroot==broot) return false;
		if(aroot > broot) parent[aroot] = broot;
		else parent[broot] = aroot;
		return true;
	}
}
