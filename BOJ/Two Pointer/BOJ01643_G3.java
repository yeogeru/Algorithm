import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/**
 * @author yeogeru
 *	  Two Pointer, Math
 */
public class BOJ01643_G3 {
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	int n = Integer.parseInt(br.readLine());
    	boolean[] prime = new boolean[n+1];
    	prime[0] = prime[1] = true;
    	int total = 0;
    	List<Integer> list = new ArrayList<>();
    	int sqr = (int)Math.sqrt(n)+1;
    	for(int i = 2 ; i <= sqr ; i++) {
    		for(int j = 2 ; i*j <= n ; j++) prime[i*j] = true;
    	}
    	for(int i = 2 ; i <= n ; i++) {
    		if(!prime[i]) {
    			if(list.isEmpty()) list.add(i);
    			else list.add(i+list.get(list.size()-1));
    		}
    	}
    	int start = 0;
    	int end = 0;
    	for(int i = 0 ; i < list.size() ; i++) {
    		if(list.get(i) >= n) {
    			end = i;
    			if(list.get(i) == n) total++;
    			break;
    		} 
    	}
    	int temp = 0;
    	while(start<list.size()) {
    		temp = list.get(end)-list.get(start);
    		if(temp > n) start++;
    		 else if(temp < n) {
    			 if(end==list.size()-1) break;
    			 end++;
    		 }
    		 else {
    			total++;
    			if(end==list.size()-1) break;
    			end++;
    			start++;
    		}
    	}
    	System.out.println(total);
    }
}
