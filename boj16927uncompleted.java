import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	static int[][] graph, total;
	static int min, n, m, r;
	static int[][] dt = {{1,0},{0,1},{-1,0},{0,-1}};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		String[] input = (br.readLine()).split(" ");
		n = Integer.parseInt(input[0]);
		m = Integer.parseInt(input[1]);
		min = Math.min(n, m);
		r = Integer.parseInt(input[2]);
		graph = new int[n][m];
		for(int i = 0 ; i < n ; i++) {
			input = (br.readLine()).split(" ");
			for(int j = 0 ; j < m ; j++) {
				graph[i][j] = Integer.parseInt(input[j]);
			}
		}
		calc(0);
		for(int i = 0 ; i < n ; i++) {
			for(int j = 0 ; j < m ; j++) {
				bw.write(total[i][j]+" ");
			}
			bw.write("\n");
		}
		bw.flush();
		bw.close();
		br.close();
	}
	
	static void calc(int cnt) {
		if(cnt==min/2) {
			return;
		}
		int round = (2*(n-(2*cnt)))+(2*m-(2*cnt))-4;
		int num = r%round;
		int index = 0;
		int d = 0;
		int x = cnt, y = cnt;
		while(index < round) {
			if(index+num<=n-(2*cnt)-1) {
				total[cnt][cnt] = graph[x+=dt[d][0]][y+=dt[d][1]];
			} else if(index+num<=n+m-(2*(2*cnt))-2) {
				total[n-(2*cnt)-1][cnt] = graph[x+=dt[d][0]][y+=dt[d][1]];
			} else if(index+num<=2*n+m-(3*(2*cnt))-3) {
				total[(n-(2*cnt)-1)][m-(2*cnt)-1] = graph[x+=dt[d][0]][y+=dt[d][1]];
			} else {
				total[cnt][(m-(2*cnt)-1)] = graph[x+=dt[d][0]][y+=dt[d][1]];
			}
			if(index == n-(2*cnt)-1) {
				d = 1;
			} else if(index == n+m-(2*cnt)-2) {
				d = 2;
			} else if(index == 2*n+m-(2*cnt)-3) {
				d = 3;
			} else {
				d = 0;
			}
			index++;
		}
		calc(cnt+1);
	}
}
