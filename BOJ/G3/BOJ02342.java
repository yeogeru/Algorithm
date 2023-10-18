import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
/**
 * 
 * @author paternalism532
 *
 *	DP
 */
public class BOJ02342 {
	static int n;
	static int[][][] dp;
	static int[] arr;
	static int[][] press = {
    		{0, 2, 2, 2, 2},
    		{0, 1, 3, 4, 3},
    		{0, 3, 1, 3, 4},
    		{0, 4, 3, 1, 3},
    		{0, 3, 4, 3, 1}
    	};
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	String[] input = (br.readLine()).split(" ");
    	if(input[0].equals("0")) System.out.println(0);
    	else {
    		n = input.length-1;
        	dp = new int[n][5][5];
        	arr = new int[n];
        	for(int i = 0 ; i < n ; i++) arr[i] = Integer.parseInt(input[i]);
        	int min = DDR(0, 0, 0);
    		System.out.println(min);
    	}
    }
    static int DDR(int index, int left, int right) {
    	if(index == n) return 0;
    	if(dp[index][left][right]!=0) return dp[index][left][right];
    	int a = DDR(index+1, arr[index], right) + press[left][arr[index]];
    	int b = DDR(index+1, left, arr[index]) + press[right][arr[index]];
    	dp[index][left][right] = Math.min(a, b);
    	return dp[index][left][right];
    }
}
