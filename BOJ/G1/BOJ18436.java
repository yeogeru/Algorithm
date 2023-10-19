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
 *	yeogeru
 */
public class BOJ18436 {
	static int tree[][], start;
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    	int n = Integer.parseInt(br.readLine());
    	int h = (int)Math.ceil(Math.log(n)/Math.log(2));
    	start = (int)Math.pow(2, h);
    	tree = new int[2][start*2];
    	StringTokenizer st = new StringTokenizer(br.readLine(), " ");
    	for(int i = start ; i < start+n ; i++) {
    		int input = Integer.parseInt(st.nextToken());
    		if(input%2==0) tree[0][i] = 1;
    		else tree[1][i] = 1;
    	}
    	for(int i = tree[0].length-1 ; i > 0 ; i--) {
    		tree[0][i/2] += tree[0][i];
    		tree[1][i/2] += tree[1][i];
    	}
    	int m = Integer.parseInt(br.readLine());
    	for(int i = 0 ; i < m ; i++) {
    		st = new StringTokenizer(br.readLine(), " ");
    		int type = Integer.parseInt(st.nextToken());
    		if(type==1) {
    			update(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
    		} else {
    			bw.write(calc(type, Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()))+"\n");
    		}
    	}
    	bw.flush();
    	bw.close();
    	br.close();
    }
    static void update(int index, int num) {
    	index += start-1;
    	if(num%2==0) {
    		tree[0][index] = 1;
    		tree[1][index] = 0;
    	} else {
    		tree[0][index] = 0;
    		tree[1][index] = 1;
    	}
    	while(index > 0) {
    		if(index%2==0) {
    			tree[0][index/2] = tree[0][index]+tree[0][index+1];
    			tree[1][index/2] = tree[1][index]+tree[1][index+1];
    		} else {
    			tree[0][index/2] = tree[0][index]+tree[0][index-1];
    			tree[1][index/2] = tree[1][index]+tree[1][index-1];
    		}
    		index/=2;
    	}
    }
    static int calc(int type, int left, int right) {
    	left+= start-1;
    	right+= start-1;
    	int result = 0;
    	while(left<=right) {
    		if(left%2==0) left/=2; 
    		else {
    			if(type==2) result+=tree[0][left];
    			else result+=tree[1][left];
    			left = (left+1)/2;
    		}
    		if(right%2==0) {
    			if(type==2) result+=tree[0][right];
    			else result+=tree[1][right];
    			right = (right-1)/2;
    		} else right/=2;
    	}
    	return result;
    }
}
