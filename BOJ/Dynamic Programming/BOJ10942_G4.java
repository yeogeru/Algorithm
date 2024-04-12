import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
/**
 * @author yeogeru
 *	  Dynamic Programming
 */
public class BOJ10942_G4 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int n = Integer.parseInt(br.readLine());
		String[] input = (br.readLine()).split(" ");
		int[] arr = new int[n];
		for(int i = 0 ; i < n ; i++) {
			arr[i] = Integer.parseInt(input[i]);
		}
		int m = Integer.parseInt(br.readLine());
		Loop:
		for(int i = 0 ; i < m ; i++) {
			input = (br.readLine()).split(" ");
			int a = Integer.parseInt(input[0]);
			int b = Integer.parseInt(input[1]);
			for(int x = a-1, y = b-1, cnt = 0 ; cnt <= (b-a)/2 ; x++, y--, cnt++) {
				if(arr[x]!=arr[y]) {
					bw.write("0\n");
					continue Loop;
				}
			}
			bw.write("1\n");
		}
		bw.flush();
		bw.close();
		br.close();
	}
}
