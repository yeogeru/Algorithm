import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/**
 * @author yeogeru
 *	  Dynamic Programming
 */
public class BOJ01005_G3 {
	static int[] dp, time;
	static List<List<Integer>> prevList = new ArrayList<>();
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    	String[] input;
    	int tc = Integer.parseInt(br.readLine());
    	for(int t = 0 ; t < tc ; t++) {
    		input = (br.readLine()).split(" ");
    		int n = Integer.parseInt(input[0]);
    		int k = Integer.parseInt(input[1]);
    		input = (br.readLine()).split(" ");
    		time = new int[n+1];
    		for(int i = 1 ; i <= n ; i++) {
    			prevList.add(new ArrayList<>());
    			time[i] = Integer.parseInt(input[i-1]);
    		}
    		prevList.add(new ArrayList<>());
    		for(int i = 0 ; i < k ; i++) {
    			input = (br.readLine()).split(" ");
    			int a = Integer.parseInt(input[0]);
    			int b = Integer.parseInt(input[1]);
    			prevList.get(b).add(a);
    		}
    		int w = Integer.parseInt(br.readLine());
    		dp = new int[n+1];
    		Arrays.fill(dp, -1);
    		dfs(w);
    		bw.write(dp[w]+"\n");
    		prevList.clear();
    	}
    	bw.flush();
    	bw.close();
    	br.close();
    }
    
    static void dfs(int index) {
    	int max = 0;
    	for(int tempIdx : prevList.get(index)) {
    		if(dp[tempIdx]==-1) dfs(tempIdx);
    		max = Math.max(max, dp[tempIdx]);
    	}
    	dp[index] = time[index] + max;
    	return;
    }
}
