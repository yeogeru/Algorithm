import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
 /**
 * 
 * @author yeogeru
 *         DP, LCS
 */
public class BOJ13711_P5 {
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	int n = Integer.parseInt(br.readLine());
    	int[] a = new int[n+1];
    	int[] idx_a = new int[n+1];
    	String[] input = (br.readLine()).split(" ");
    	for(int i = 1 ; i <= n ; i++) {
    		a[i] = Integer.parseInt(input[i-1]);
    		idx_a[Integer.parseInt(input[i-1])] = i;
    	}
    	int[] b = new int[n+1];
    	int[] idx_b = new int[n+1];
    	input = (br.readLine()).split(" ");
    	for(int i = 1 ; i <= n ; i++) {
    		b[i] = Integer.parseInt(input[i-1]);
    		idx_b[Integer.parseInt(input[i-1])] = i;
    	}
    	int[] dp = new int[n+1];
    	for(int i = 1 ; i <= n ; i++) {
    		int tempidx = idx_a[b[i]];
    		dp[tempidx]++;
        	for(int j = tempidx ; j < n ; j++) {
        		if(dp[tempidx] > dp[j+1]) dp[j+1] = dp[j];
        		else break;
        	}
    	}
    	System.out.println(dp[n]);
    }
}
