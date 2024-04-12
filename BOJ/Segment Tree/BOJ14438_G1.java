import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;
/**
 * 
 * @author yeogeru
 *
 *	Segment Tree
 */
public class BOJ14438_G1 {
	static int n, start;
	static int[] tree;
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    	n = Integer.parseInt(br.readLine());
    	int h = (int)Math.ceil(Math.log(n)/Math.log(2));
    	start = (int)Math.pow(2, h);
    	tree = new int[start*2];
    	Arrays.fill(tree, Integer.MAX_VALUE);
    	StringTokenizer st = new StringTokenizer(br.readLine(), " ");;
    	for(int i = 0 ; i < n ; i++) {
    		tree[start+i] = Integer.parseInt(st.nextToken());
    	}
    	for(int i = tree.length-1 ; i > 0 ; i--) {
    		tree[i/2] = Math.min(tree[i/2], tree[i]);
    	}
    	int m = Integer.parseInt(br.readLine());
    	for(int i = 0 ; i < m ; i++) {
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
    			tree[temp/2] = Math.min(tree[temp], tree[temp+1]);
    		} else {
    			tree[temp/2] = Math.min(tree[temp], tree[temp-1]);
    		}
    		temp/=2;
    	}
    }
    
    static long calc(int left, int right) {
    	left += start - 1;
    	right += start - 1;
    	int total = Integer.MAX_VALUE;
    	while(left<=right) {
    		if(left%2==0) left/=2;
    		else {
    			total = Math.min(total, tree[left]);
    			left = left/2+1;
    		}
    		if(right%2==0) {
    			total = Math.min(total, tree[right]);
    			right = right/2-1;
    		} else right/=2;
    	}
    	return total;
    }
}
