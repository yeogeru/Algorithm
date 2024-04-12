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
public class BOJ14428_G1 {
	static class Node {
		int val, idx;
		public Node(int x, int y) {
			super();
			this.val = x;
			this.idx = y;
		}
	}
	static int n, start;
	static Node[] tree;
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	n = Integer.parseInt(br.readLine());
    	start = (int) Math.pow(2, (int)Math.ceil(Math.log(n)/Math.log(2)));
    	tree = new Node[start * 2];
    	String[] input = (br.readLine()).split(" ");
    	for(int i = 0 ; i < tree.length ; i++) {
    		tree[i] = new Node(Integer.MAX_VALUE, 0);
    	}
    	for(int i = 0 ; i < n ; i++) {
    		tree[i+start].val = Integer.parseInt(input[i]);
    		tree[i+start].idx = i+1;
    	}
    	for(int i = tree.length - 1;  i >= 1 ; i--) {
    		if(tree[i/2].val > tree[i].val) {
    			tree[i/2].val = tree[i].val;
    			tree[i/2].idx = tree[i].idx;
    		} else if(tree[i / 2].val == tree[i].val) {
    			tree[i/2].idx = Math.min(tree[i/2].idx, tree[i].idx);
    		}
    	}
    	int k = Integer.parseInt(br.readLine());
    	for(int i = 0 ; i < k ; i++) {
    		input = (br.readLine()).split(" ");
    		if(input[0].equals("1")) {
    			changeValue(Integer.parseInt(input[1]), Integer.parseInt(input[2]));
    		}
    		else {
    			bw.write(getmin(Integer.parseInt(input[1]), Integer.parseInt(input[2]))+"\n");
    		}
    	}
    	bw.flush();
    	bw.close();
    	br.close();
    }
    static void changeValue(int index, int val) {
    	index += start-1;
    	tree[index].val = val;
    	for(int i = index ; i >= 1 ; i/=2) {
    		if(i%2==0) {
    			if(tree[i].val > tree[i+1].val) {
    				tree[i/2].val = tree[i+1].val;
    				tree[i/2].idx = tree[i+1].idx;
    			} else if(tree[i].val < tree[i+1].val) {
    				tree[i/2].val = tree[i].val;
    				tree[i/2].idx = tree[i].idx;
    			} else {
    				tree[i/2].val = tree[i].val;
    				tree[i/2].idx = Math.min(tree[i].idx, tree[i+1].idx);
    			}
    		} else {
    			if(tree[i].val > tree[i-1].val) {
    				tree[i/2].val = tree[i-1].val;
    				tree[i/2].idx = tree[i-1].idx;
    			} else if(tree[i].val < tree[i-1].val) {
    				tree[i/2].val = tree[i].val;
    				tree[i/2].idx = tree[i].idx;
    			} else {
    				tree[i/2].val = tree[i].val;
    				tree[i/2].idx = Math.min(tree[i].idx, tree[i-1].idx);
    			}
    		}
    	}
    }
    
    static int getmin(int from, int to) {
    	from += start-1;
    	to += start-1;
    	int min = Integer.MAX_VALUE;
    	int minIndex = Integer.MAX_VALUE;
    	while(from<=to) {
    		if(from%2 == 0) from /= 2;
    		else {
    			if(min > tree[from].val) {
    				min = tree[from].val;
    				minIndex = tree[from].idx;
    			} else if(min == tree[from].val) minIndex = Math.min(minIndex, tree[from].idx);
    			from = (from+1)/2; 
    		}
    		if(to%2 == 0) {
    			if(min > tree[to].val) {
    				min = tree[to].val;
    				minIndex = tree[to].idx;
    			} else if(min == tree[to].val) minIndex = Math.min(minIndex, tree[to].idx);
    			to = (to-1)/2;
    		} else to /= 2;
    	}
    	if(minIndex==Integer.MAX_VALUE) return -1;
    	return minIndex;
    }
}
