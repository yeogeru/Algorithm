import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
/**
 * 
 * @author yeogeru
 *
 *	DP, Knapsack
 */
public class BOJ07579_G3 {
	static final int COST_MAX_COUNT = 100*100;
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	String[] input = (br.readLine()).split(" ");
    	int n = Integer.parseInt(input[0]);
    	int m = Integer.parseInt(input[1]);
    	int[][] app = new int[n+1][2];
    	input = (br.readLine()).split(" ");
    	for(int i = 1 ; i <= n ; i++) app[i][0] = Integer.parseInt(input[i-1]);
    	input = (br.readLine()).split(" ");
    	for(int i = 1 ; i <= n ; i++) app[i][1] = Integer.parseInt(input[i-1]);
    	long[][] dp = new long[n+1][COST_MAX_COUNT+1];
    	for(int i = 1 ; i <= n ; i++) {
    		for(int j = 0 ; j <= COST_MAX_COUNT ; j++) {
    			dp[i][j] = dp[i-1][j];
    			if(j>=app[i][1]) dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-app[i][1]]+app[i][0]);
    		}
    	}
    	int min = Integer.MAX_VALUE;
    	for(int i = 1 ; i <= n ; i++) {
    		for(int j = 1 ; j <= COST_MAX_COUNT ; j++) {
    			if(dp[i][j] >= m) min = Math.min(min, j);
    		}
    	}
    	System.out.println(min);
    }
}
