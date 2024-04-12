import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
/**
 * @author yeogeru
 *	  Implementation, Data Structure
 */
public class BOJ02143_G3 {
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	int t = Integer.parseInt(br.readLine());
    	int n = Integer.parseInt(br.readLine());
    	int[] input_a = new int[n+1];
    	String[] input = (br.readLine()).split(" ");
    	for(int i = 1 ; i <= n ; i++) {
    		input_a[i] = input_a[i-1]+Integer.parseInt(input[i-1]);
    	}
    	int m = Integer.parseInt(br.readLine());
    	int[] input_b = new int[m+1];
    	input = (br.readLine()).split(" ");
    	for(int i = 1 ; i <= m ; i++) {
    		input_b[i] = input_b[i-1]+Integer.parseInt(input[i-1]);
    	}
    	HashMap<Integer, Integer> map = new HashMap<>();
    	long total = 0;
    	for(int i = 1 ; i <= n ; i++) {
    		for(int j = 0 ; j < i ; j++) {
    			int a = input_a[i]-input_a[j];
    			if(!map.containsKey(a)) map.put(a, 0);
    			map.replace(a, map.get(a)+1);
    		}
    	}
    	for(int i = 1 ; i <= m ; i++) {
    		for(int j = 0 ; j < i ; j++) {
    			int b = input_b[i]-input_b[j];
    			if(map.containsKey(t-b)) total+=map.get(t-b);
    		}
    	}
    	System.out.println(total);
    }
}
