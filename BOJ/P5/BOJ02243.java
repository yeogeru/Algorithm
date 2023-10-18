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
 *	Segment Tree, 이분탐색
 */
public class BOJ02243 {
	static int start;
	static int[] tree;
	static final int CANDY = 1_000_000;
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    	int n = Integer.parseInt(br.readLine());
    	int h = (int)Math.ceil(Math.log(CANDY)/Math.log(2));
    	start = (int)Math.pow(2, h);
    	tree = new int[start * 2];
    	StringTokenizer st;
    	for(int i = 0 ; i < n; i++) {
    		st = new StringTokenizer(br.readLine(), " ");
    		int ctg = Integer.parseInt(st.nextToken());
    		if(ctg==1) {
    			int a = Integer.parseInt(st.nextToken());
    			int b = candylove(a);
    			tree[b+start-1]--;
    			if(tree[b+start-1] < 0) tree[b+start-1] = 0;
    			bw.write(b+"\n");
    			update(b);
    		} else {
    			int a = Integer.parseInt(st.nextToken());
    			int b = Integer.parseInt(st.nextToken());
    			tree[a+start-1]+=b;
    			if(tree[a+start-1] < 0) tree[a+start-1] = 0;
    			update(a);
    		}
    	}
    	bw.flush();
    	bw.close();
    }
    
    static void update(int a) {
    	int temp = a+start-1;
    	while(temp / 2 > 0) {
    		tree[temp/2] = tree[temp];
    		if(temp%2==0) tree[temp/2] += tree[temp+1];
    		else tree[temp/2] += tree[temp-1];
    		temp/=2;
    	}
    }
    
    static int candylove (int input) {
    	int n = 1;
    	while(n*2 < tree.length) {
    		if(input <= tree[n*2]) {
    			n*=2;
    		} else {
    			input-=tree[n*2];
    			n=n*2+1;
    		}
    	}
    	return n-start+1;
    }
}
