import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/**
 * 
 * @author yeogeru
 *
 *	DP, Knapsack
 */
public class BOJ12865 {
	static final int COST_MAX_COUNT = 100*100;
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine(), " ");
    	int n = Integer.parseInt(st.nextToken());
    	int k = Integer.parseInt(st.nextToken());
    	int[][] knapsack = new int[n+1][2];
    	for(int i = 1 ; i <= n ; i ++) {
    		st = new StringTokenizer(br.readLine(), " ");
    		knapsack[i][0] = Integer.parseInt(st.nextToken());
    		knapsack[i][1] = Integer.parseInt(st.nextToken());
    	}
    	
    	int[][] dp = new int[n+1][k+1];
    	for(int i = 1 ; i <= n ; i++) {
    		for(int j = 1 ; j <= k ; j++) {
    			if(knapsack[i][0] > j) {
    				dp[i][j] = dp[i-1][j];
    			} else {
    				dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-knapsack[i][0]]+knapsack[i][1]);
    			}
    		}
    	}
    	System.out.println(dp[n][k]);
    }
}
