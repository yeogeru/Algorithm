import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;
/**
 * 
 * @author paternalism532
 *
 *	Segment Tree
 */
public class BOJ11505 {
	static int n, start;
	static long[] tree;
	static final int MOD = 1_000_000_007;
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    	StringTokenizer st = new StringTokenizer(br.readLine(), " ");
    	n = Integer.parseInt(st.nextToken());
    	int m = Integer.parseInt(st.nextToken());
    	int k = Integer.parseInt(st.nextToken());
    	int h = (int)Math.ceil(Math.log(n)/Math.log(2));
    	start = (int)Math.pow(2, h);
    	tree = new long[start*2];
    	Arrays.fill(tree, 1);
    	for(int i = 0 ; i < n ; i++) {
    		tree[start+i] = Integer.parseInt(br.readLine());
    	}
    	int cnt = 1;
    	int temp = start;
    	while(temp > 1) {
    		for(int i = temp ; i < temp+(start/cnt) ; i++) {
    			tree[i/2] = tree[i/2]*tree[i]%MOD;
    		}
    		cnt*=2;
    		temp/=2;
    	}
    	for(int i = 0 ; i < m+k ; i++) {
    		st = new StringTokenizer(br.readLine(), " ");
    		if(st.nextToken().equals("1")) {
    			update(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
    		} else {
    			bw.write(calc(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()))+"\n");
    		}
    	}
    	bw.flush();
    	bw.close();
    	br.close();
    }
    
    static void update(int index, int num) {
    	int temp = index+start-1;
    	tree[temp] = num;
    	while(temp > 1) {
    		if(temp%2==0) {
    			tree[temp/2] = (tree[temp]%MOD)*(tree[temp+1]%MOD)%MOD;
    		} else {
    			tree[temp/2] = (tree[temp]%MOD)*(tree[temp-1]%MOD)%MOD;
    		}
    		temp/=2;
    	}
    }
    
    static long calc(int left, int right) {
    	left += start - 1;
    	right += start - 1;
    	long total = 1;
    	while(left<=right) {
    		if(left%2==0) left/=2;
    		else {
    			total*=tree[left];
    			total%=MOD;
    			left = left/2+1;
    		}
    		if(right%2==0) {
    			total*=tree[right];
    			total%=MOD;
    			right = right/2-1;
    		} else right/=2;
    	}
    	return total%MOD;
    }
}
