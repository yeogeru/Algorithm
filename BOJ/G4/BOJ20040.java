import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/**
 * 
 * @author paternalism532
 *
 *	MST
 */
public class BOJ20040 {
	static int[] parents;
	static boolean union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		if(aRoot==bRoot) return false;
		if(a < b) parents[aRoot] = bRoot;
		else parents[bRoot] = aRoot;
		return true;
	}
	static int find(int a) {
		if(a==parents[a]) return a;
		return parents[a] = find(parents[a]);
	}
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	String[] input = (br.readLine()).split(" ");
    	int n = Integer.parseInt(input[0]);
    	int m = Integer.parseInt(input[1]);
    	String output = "0";
    	boolean isCycle = false;
    	parents = new int[n];
    	for(int i = 0 ; i < n ; i++) {
    		parents[i] = i;
    	}
    	StringTokenizer st;
    	for(int i = 1 ; i <= m ; i++) {
    		st = new StringTokenizer(br.readLine(), " ");
    		if(isCycle) continue;
    		if(!union(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()))) {
    			output = String.valueOf(i);
    			isCycle = true;
    		}
    	}
    	System.out.println(output);
    }
}
