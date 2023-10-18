import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
/**
 * 
 * @author paternalism532
 *
 *	Segment Tree
 */
public class BOJ01275 {
	static long tree[];
	static int start;
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    	StringTokenizer st = new StringTokenizer(br.readLine(), " ");
    	int n = Integer.parseInt(st.nextToken());
    	int m = Integer.parseInt(st.nextToken());
    	int h = (int)Math.ceil(Math.log(n)/Math.log(2));
    	start = (int)Math.pow(2, h);
    	tree = new long[start*2];
    	st = new StringTokenizer(br.readLine(), " ");
    	for(int i = start ; i < start+n ; i++) {
    		tree[i] = Integer.parseInt(st.nextToken());
    	}
    	for(int i = tree.length-1 ; i > 0 ; i--) {
    		tree[i/2] += tree[i];
    	}
    	for(int i = 0 ; i < m ; i++) {
    		st = new StringTokenizer(br.readLine(), " ");
    		int a = Integer.parseInt(st.nextToken());
    		int b = Integer.parseInt(st.nextToken());
    		if(a > b) {
    			int temp = a;
    			a = b;
    			b = temp;
    		}
    		bw.write(calc(a, b)+"\n");
    		update(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
    	}
    	bw.flush();
    	bw.close();
    	br.close();
    }
    static void update(int index, int num) {
    	index += start-1;
    	tree[index] = num;
    	while(index > 0) {
    		if(index%2==0) {
    			tree[index/2] = tree[index] + tree[index+1];
    		} else {
    			tree[index/2] = tree[index] + tree[index-1];
    		}
    		index/=2;
    	}
    }
    static long calc(int left, int right) {
    	left+= start-1;
    	right+= start-1;
    	long result = 0;
    	while(left<=right) {
    		if(left%2==0) left/=2; 
    		else {
    			result += tree[left];
    			left = (left+1)/2;
    		}
    		if(right%2==0) {
    			result += tree[right];
    			right = (right-1)/2;
    		} else right/=2;
    	}
    	return result;
    }
}
