import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;
/**
 * 
 * @author paternalism532
 *
 *	Segment Tree 활용
 */
public class BOJ16978 {
	static class SegQuery implements Comparable<SegQuery>{
		int idx, type, seq, a, b;
		public SegQuery(int idx, int type, int seq, int a, int b) {
			super();
			this.idx = idx;
			this.type = type;
			this.seq = seq;
			this.a = a;
			this.b = b;
		}
		@Override
		public int compareTo(SegQuery o) {
			if(o.seq==this.seq) {
				return Integer.compare(this.type, o.type);
			}
			return Integer.compare(this.seq, o.seq);
		}
	}
	static long tree[];
	static int start;
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    	int n = Integer.parseInt(br.readLine());
    	StringTokenizer st = new StringTokenizer(br.readLine(), " ");
    	int h = (int)Math.ceil(Math.log(n)/Math.log(2));
    	start = (int)Math.pow(2, h);
    	tree = new long[start*2];
    	for(int i = start ; i < start+n ; i++) {
    		tree[i] = Integer.parseInt(st.nextToken());
    	}
    	for(int i = tree.length-1 ; i > 0 ; i--) {
    		tree[i/2] += tree[i];
    	}
    	int m = Integer.parseInt(br.readLine());
    	SegQuery[] list = new SegQuery[m];
    	
    	int cnt1 = 1;
    	int cnt2 = 0;
    	for(int i = 0 ; i < m ; i++) {
    		st = new StringTokenizer(br.readLine(), " ");
    		if(st.nextToken().equals("1")) {
    			int a = Integer.parseInt(st.nextToken());
    			int b = Integer.parseInt(st.nextToken());
    			list[i] = new SegQuery(0, 1, cnt1++, a, b);
    		} else {
    			int a = Integer.parseInt(st.nextToken());
    			int b = Integer.parseInt(st.nextToken());
    			int c = Integer.parseInt(st.nextToken());
    			list[i] = new SegQuery(cnt2++, 2, a, b, c);
    		}
    	}
    	Arrays.sort(list);
    	HashMap<Integer, Long> result = new HashMap<>();
    	for(int i = 0 ; i < m ; i++) {
    		if(list[i].type==1) {
    			update(list[i].a, list[i].b);
    		} else {
    			result.put(list[i].idx, calc(list[i].a, list[i].b));
    		}
    	}
    	for(int i = 0 ; i < cnt2 ; i++) {
    		bw.write(result.get(i)+"\n");
    	}
    	bw.flush();
    	bw.close();
    	br.close();
    }
    
    static void update(int index, int num) {
    	index += start-1;
    	tree[index] = num;
    	for(int i = index ; i > 0 ; i/=2) {
    		if(i%2==0) {
    			tree[i/2] = tree[i]+tree[i+1];
    		} else {
    			tree[i/2] = tree[i]+tree[i-1];
    		}
    	}
    }
    
    static long calc(int left, int right) {
    	left+= start-1;
    	right+= start-1;
    	long result = 0;
    	while(left<=right) {
    		if(left%2==0) {
    			left/=2;
    		} else {
    			result+=tree[left];
    			left = (left+1)/2;
    		}
    		if(right%2==0) {
    			result+=tree[right];
    			right = (right-1)/2;
    		} else {
    			right/=2;
    		}
    	}
    	return result;
    }
}
