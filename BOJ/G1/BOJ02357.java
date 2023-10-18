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
public class BOJ02357 {
	static int tree[][], start;
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    	StringTokenizer st = new StringTokenizer(br.readLine(), " ");
    	int n = Integer.parseInt(st.nextToken());
    	int m = Integer.parseInt(st.nextToken());
    	int h = (int)Math.ceil(Math.log(n)/Math.log(2));
    	start = (int)Math.pow(2, h);
    	tree = new int[2][start*2];
    	Arrays.fill(tree[0], Integer.MIN_VALUE);
    	Arrays.fill(tree[1], Integer.MAX_VALUE);
    	for(int i = start ; i < start+n ; i++) {
    		tree[0][i] = tree[1][i] = Integer.parseInt(br.readLine());
    	}
    	for(int i = tree[0].length-1 ; i > 0 ; i--) {
    		tree[0][i/2] = Math.max(tree[0][i/2], tree[0][i]);
    		tree[1][i/2] = Math.min(tree[1][i/2], tree[1][i]);
    	}
    	for(int i = 0 ; i < m ; i++) {
    		st = new StringTokenizer(br.readLine(), " ");
    		int[] result = calc(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
    		bw.write(result[1]+" "+result[0]+"\n");
    	}
    	bw.flush();
    	bw.close();
    	br.close();
    }
    static int[] calc(int left, int right) {
    	left+= start-1;
    	right+= start-1;
    	int[] result = new int[2];
    	result[0] = Integer.MIN_VALUE;
    	result[1] = Integer.MAX_VALUE;
    	while(left<=right) {
    		if(left%2==0) {
    			left/=2;
    		} else {
    			result[0] = Math.max(result[0], tree[0][left]);
    			result[1] = Math.min(result[1], tree[1][left]);
    			left = (left+1)/2;
    		}
    		if(right%2==0) {
    			result[0] = Math.max(result[0], tree[0][right]);
    			result[1] = Math.min(result[1], tree[1][right]);
    			right = (right-1)/2;
    		} else {
    			right/=2;
    		}
    	}
    	return result;
    }
}
