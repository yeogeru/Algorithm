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
public class BOJ05676 {
	static int tree[], start;
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    	while(true) {
    		String testcase = br.readLine();
    		if(testcase==null || testcase.equals("")) break;
    		StringTokenizer st = new StringTokenizer(testcase, " ");
        	int n = Integer.parseInt(st.nextToken());
        	int m = Integer.parseInt(st.nextToken());
        	int h = (int)Math.ceil(Math.log(n)/Math.log(2));
        	start = (int)Math.pow(2, h);
        	tree = new int[start*2];
        	Arrays.fill(tree, 1);
        	st = new StringTokenizer(br.readLine(), " ");
        	for(int i = start ; i < start+n ; i++) {
        		int input = Integer.parseInt(st.nextToken());
        		if(input > 0) tree[i] = 1;
        		else if(input < 0) tree[i] = -1;
        		else tree[i] = 0;
        	}
        	for(int i = tree.length-1 ; i > 0 ; i--) {
        		tree[i/2] *= tree[i];
        	}
        	for(int i = 0 ; i < m ; i++) {
        		st = new StringTokenizer(br.readLine(), " ");
        		if(st.nextToken().equals("P")) {
        			int output = calc(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        			if(output > 0) bw.write("+");
        			else if(output < 0) bw.write("-");
        			else bw.write("0");
        		} else {
        			update(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        		}
        	}
        	bw.write("\n");
    	}
    	bw.flush();
    	bw.close();
    	br.close();
    }
    static void update(int index, int num) {
    	index += start-1;
    	if(num > 0) tree[index] = 1;
    	else if(num < 0) tree[index] = -1;
    	else tree[index] = 0;
    	while(index > 0) {
    		if(index%2==0) {
    			tree[index/2] = tree[index] * tree[index+1];
    		} else {
    			tree[index/2] = tree[index] * tree[index-1];
    		}
    		index/=2;
    	}
    }
    static int calc(int left, int right) {
    	left+= start-1;
    	right+= start-1;
    	int result = 1;
    	while(left<=right) {
    		if(left%2==0) left/=2; 
    		else {
    			result *= tree[left];
    			left = (left+1)/2;
    		}
    		if(right%2==0) {
    			result *= tree[right];
    			right = (right-1)/2;
    		} else right/=2;
    	}
    	return result;
    }
}
