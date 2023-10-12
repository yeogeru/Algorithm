import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/**
 * 
 * @author paternalism532
 * 		구현
 */
public class BOJ28325 {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		long[] arr = new long[n];
		long max = 0;
		int maxidx = 0;
		for(int i = 0 ; i < n ; i++) {
			arr[i] = Long.parseLong(st.nextToken());
			if(max < arr[i]) {
				max = arr[i];
				maxidx = i;
			}
		}
		int curidx = (maxidx+1)%n;
		boolean[] nojjokroom = new boolean[n];
		long total = arr[maxidx];
		while(curidx!=maxidx) {
			if(arr[curidx]==0) {
				int left = curidx-1;
				int right = curidx+1;
				if(left==-1) left=n-1;
				if(right==n) right=0;
				if(!nojjokroom[left]&&!nojjokroom[right]) {
					nojjokroom[curidx] = true;
					++total;
				}
			} else total+=arr[curidx];
			if(++curidx==n) curidx=0;
		}
		System.out.println(total);
	}
}
