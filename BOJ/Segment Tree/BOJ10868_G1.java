import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
 /**
 * 
 * @author yeogeru
 *         Segment Tree
 */
public class BOJ10868_G1 {
	static int n, start;
	static int[] tree;
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	String[] input = (br.readLine()).split(" ");
    	n = Integer.parseInt(input[0]);
    	int k = Integer.parseInt(input[1]);
    	start = (int) Math.pow(2, (int)Math.ceil(Math.log(n)/Math.log(2)));
    	tree = new int[start * 2];
    	for(int i = 0 ; i < tree.length ; i++) {
    		tree[i] = Integer.MAX_VALUE;
    	}
    	for(int i = 0 ; i < n ; i++) {
    		tree[i+start] = Integer.parseInt(br.readLine());
    	}
    	for(int i = tree.length - 1;  i >= 1 ; i--) {
    		if(tree[i/2] > tree[i]) {
    			tree[i/2] = tree[i];
    			tree[i/2] = tree[i];
    		} else if(tree[i / 2] == tree[i]) {
    			tree[i/2] = Math.min(tree[i/2], tree[i]);
    		}
    	}
    	for(int i = 0 ; i < k ; i++) {
    		input = (br.readLine()).split(" ");
    		bw.write(getmin(Integer.parseInt(input[0]), Integer.parseInt(input[1]))+"\n");
    	}
    	bw.flush();
    	bw.close();
    	br.close();
    }
    static int getmin(int from, int to) {
    	from += start-1;
    	to += start-1;
    	int min = Integer.MAX_VALUE;
    	while(from<=to) {
    		if(from%2 == 0) from /= 2;
    		else {
    			min = Math.min(min, tree[from]);
    			from = (from+1)/2; 
    		}
    		if(to%2 == 0) {
    			min = Math.min(min, tree[to]);
    			to = (to-1)/2;
    		} else to /= 2;
    	}
    	return min;
    }
}
