import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
 /**
 * 
 * @author yeogeru
 *         DP, LIS
 */
public class BOJ14002 {
	static int[] dp, arr;
	static int n;
	static HashMap<Integer, Integer> map = new HashMap<>();
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    	n = Integer.parseInt(br.readLine());
    	String[] input = (br.readLine()).split(" ");
    	arr = new int[n];
    	dp = new int[n];
    	int max = 0;
    	int findIdx = 0;
    	for(int i = 0 ; i < n ; i++) arr[i] = Integer.parseInt(input[i]);
    	for(int i = 0 ; i < n ; i++) {
    		if(dp[i]==0) dp[i] = calc(i);
    		if(max < dp[i]) {
    			max = dp[i];
    			findIdx = i;
    		}
    	}
    	bw.write(max+"\n"+arr[findIdx]+"");
    	while(map.containsKey(findIdx)) {
    		int temp = map.get(findIdx);
    		bw.write(" "+arr[temp]);
    		findIdx = temp;
    	}
    	bw.flush();
    	bw.close();
    	br.close();
    }
    
    static int calc(int index) {
    	int max = 1;
    	int maxIdx = index;
    	for(int i = index+1 ; i < n ; i++) {
    		if(arr[index] < arr[i]) {
    			if(dp[i]==0) calc(i);
    			if(dp[index] < dp[i]+1) {
    				dp[index] = dp[i]+1;
    				maxIdx = i;
    			}
    		}
    	}
    	max = Math.max(max, dp[index]);
    	if(max==1) dp[index]=1;
    	else map.put(index, maxIdx);
    	return max;
    }
}
