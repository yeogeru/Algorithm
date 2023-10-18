import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;
/**
 * 
 * @author paternalism532
 *
 *	Deque 활용
 */
public class BOJ30039 {
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    	Deque<Integer> top = new LinkedList<>();
    	Deque<Integer> bottom = new LinkedList<>();
    	Deque<Integer> left = new LinkedList<>();
    	Deque<Integer> right = new LinkedList<>();
    	int n = Integer.parseInt(br.readLine());
    	for(int i = 0 ; i < n ; i++) {
    		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
    		String input = st.nextToken();
    		if(input.equals("hpush")) {
    			int num = Integer.parseInt(st.nextToken());
    			if(top.isEmpty()&&right.isEmpty()) top.offerLast(num);
    			right.offerLast(num);
    			if(right.size() > 1 && (left.size()+right.size())%2!=0) {
    				left.offerLast(right.pollFirst());
    				top.pollFirst();
    				top.offerFirst(right.peekFirst());
    			}
    		} else if(input.equals("hpop")) {
    			if(left.isEmpty()&&bottom.isEmpty()&&top.isEmpty()&&right.isEmpty()) bw.write(-1+"\n");
    			else {
    				if(!left.isEmpty()) {
    					bw.write(left.pollFirst()+"\n");
    					if((left.size()+right.size())%2!=0) {
    						left.offerLast(right.pollFirst());
    						top.pollFirst();
    						top.offerFirst(right.peekFirst());
    					}
    				}
    				else {
    					bw.write(right.pollFirst()+"\n");
    					top.pollFirst();
    					if(!right.isEmpty()) top.offerFirst(right.peekFirst());
    				}
    				if(right.isEmpty()&&!top.isEmpty()) {
    					if(!bottom.isEmpty()) {
    						if((top.size()+bottom.size())%2==0) {
    							top.offerFirst(bottom.pollLast());
    						}
    					}
    					right.offerFirst(top.peekFirst());
    				}
    			}
    		} else if(input.equals("hfront")) {
    			if(!left.isEmpty()) bw.write(left.peekFirst()+"\n");
    			else if(!right.isEmpty()) bw.write(right.peekFirst()+"\n");
    			else bw.write(-1+"\n");
    		} else if(input.equals("hback")) {
    			if(!right.isEmpty()) bw.write(right.peekLast()+"\n");
    			else bw.write(-1+"\n");
    		} else if(input.equals("hsize")) {
				bw.write(left.size()+right.size()+"\n");
    		} else if(input.equals("vpush")) {
    			int num = Integer.parseInt(st.nextToken());
    			if(top.isEmpty()&&right.isEmpty()) right.offerLast(num);
    			top.offerLast(num);
    			if(top.size() > 1 && (top.size()+bottom.size())%2!=0) {
    				bottom.offerLast(top.pollFirst());
    				right.pollFirst();
    				right.offerFirst(top.peekFirst());
    			}
    		} else if(input.equals("vpop")) {
    			if(left.isEmpty()&&bottom.isEmpty()&&top.isEmpty()&&right.isEmpty()) bw.write(-1+"\n");
    			else {
    				if(!bottom.isEmpty()) {
    					bw.write(bottom.pollFirst()+"\n");
    					if((bottom.size()+top.size())%2!=0) {
    						bottom.offerLast(top.pollFirst());
    						right.pollFirst();
    						right.offerFirst(top.peekFirst());
    					}
    				}
    				else {
    					bw.write(top.pollFirst()+"\n");
    					right.pollFirst();
    					if(!top.isEmpty()) right.offerFirst(top.peekFirst());
    				}
    				if(top.isEmpty()&&!right.isEmpty()) {
    					if(!left.isEmpty()) {
    						if((right.size()+left.size())%2==0) {
    							right.offerFirst(left.pollLast());
    						}
    					}
    					top.offerFirst(right.peekFirst());
    				}
    			}
    		} else if(input.equals("vfront")) {
    			if(!bottom.isEmpty()) bw.write(bottom.peekFirst()+"\n");
    			else if(!top.isEmpty()) bw.write(top.peekFirst()+"\n");
    			else bw.write(-1+"\n");
    		} else if(input.equals("vback")) {
    			if(!top.isEmpty()) bw.write(top.peekLast()+"\n");
    			else bw.write(-1+"\n");
    		} else if(input.equals("vsize")) {
    			bw.write(top.size()+bottom.size()+"\n");
    		} else if(input.equals("size")) {
    			int size = top.size()+bottom.size()+left.size()+right.size();
    			if(size!=0) size--;
    			bw.write(size+"\n");
    		} else if(input.equals("empty")) {
    			if(top.isEmpty()&&bottom.isEmpty()&&right.isEmpty()&&left.isEmpty()) bw.write(1+"\n");
    			else bw.write(0+"\n");
    		} else if(input.equals("middle")) {
    			if(!top.isEmpty()) bw.write(top.peekFirst()+"\n");
    			else bw.write(-1+"\n");
    		}
    	}
    	bw.flush();
    	bw.close();
    	br.close();
    }
}
