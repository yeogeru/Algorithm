import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;
/**
 * 
 * @author paternalism532
 *
 *	BFS, 백트래킹 구현
 */
public class BOJ9205 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		Deque<int[]> que = new ArrayDeque<>();
		int test_case = Integer.parseInt(br.readLine());
		for(int t = 0 ; t < test_case ; t++) {
			int n = Integer.parseInt(br.readLine());
			int[] CUx = new int[n];
			int[] CUy = new int[n];
			st = new StringTokenizer(br.readLine(), " ");
			int stx = Integer.parseInt(st.nextToken());
			int sty = Integer.parseInt(st.nextToken());
			for(int i = 0 ; i < n ; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				CUx[i] = Integer.parseInt(st.nextToken());
				CUy[i] = Integer.parseInt(st.nextToken());
			}
			st = new StringTokenizer(br.readLine(), " ");
			int dtx = Integer.parseInt(st.nextToken());
			int dty = Integer.parseInt(st.nextToken());
			boolean[] visit = new boolean[n];
			boolean happyhappyhappyhappyhappy = false;
			que.clear();
			que.add(new int[] {stx, sty});
			while(!que.isEmpty()) {
				int[] temp = que.poll();
				if(Math.abs(dtx-temp[0])+Math.abs(dty-temp[1])<= 1000) {
					happyhappyhappyhappyhappy = true;
					break;
				}
				for(int i = 0 ; i < n ; i++) {
					if(!visit[i]&&Math.abs(CUx[i]-temp[0])+Math.abs(CUy[i]-temp[1])<=1000) {
						visit[i] = true;
						que.offer(new int[] {CUx[i], CUy[i]});
					}
				}
			}
			if(happyhappyhappyhappyhappy) bw.write("happy\n");
			else bw.write("sad\n");
		}
		bw.flush();
		bw.close();
		br.close();
	}
}
