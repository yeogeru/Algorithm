import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static int[][] graph, total;
	static int min, n, m, r;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] input = (br.readLine()).split(" ");
		n = Integer.parseInt(input[0]);
		m = Integer.parseInt(input[1]);
		min = Math.min(n, m);
		r = Integer.parseInt(input[2]);
		for(int i = 0 ; i < n ; i++) {
			input = (br.readLine()).split(" ");
			for(int j = 0 ; j < m ; j++) {
				graph[i][j] = Integer.parseInt(input[j]);
			}
		}
		calc(0);
	}
	
	static void calc(int cnt) {
		if(cnt==min/2) {
			return;
		}
		int round = (2*(n-(2*cnt)))+(2*m-(2*cnt))-4;
		int num = r%round;
		int index = 0;
		while(index < round) {
			if(index+num <= n-(2*cnt)-1) {
				total[cnt+num][cnt] = graph[cnt][cnt];
			} else if(index+num < n+m-(2*(2*cnt))-2) {
				
			} else if(index+num < 2*n+m-(3*(2*cnt))-3) {
				
			} else {
				
			}
			index++;
		}
		
	}
}
