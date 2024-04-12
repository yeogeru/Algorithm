import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
/**
 * 
 * @author yeogeru
 *
 *	Topological Sorting
 */
public class BOJ02623_G3 {
	static class Singer {
		List<Integer> prev, next;
		int idx;
		boolean selected;
		public Singer(List<Integer> prev, List<Integer> next, int idx, boolean selected) {
			super();
			this.prev = prev;
			this.next = next;
			this.idx = idx;
			this.selected = selected;
		}
	}
	public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    	String[] input = (br.readLine()).split(" ");
    	int n = Integer.parseInt(input[0]);
    	List<Singer> list = new ArrayList<Main.Singer>();
    	list.add(null);
    	for(int i = 1 ; i <= n ; i++) list.add(new Singer(new ArrayList<>(), new ArrayList<>(), i, false));
    	int m = Integer.parseInt(input[1]);
    	for(int i = 0 ; i < m ; i++) {
    		input = (br.readLine()).split(" ");
    		int k = Integer.parseInt(input[0]);
    		for(int j = 1 ; j < k ; j++) {
    			int a = Integer.parseInt(input[j]);
    			int b = Integer.parseInt(input[j+1]);
    			list.get(a).next.add(b);
    			list.get(b).prev.add(a);
    		}
    	}
    	Queue<Integer> que = new LinkedList<Integer>();
    	Queue<Integer> total = new LinkedList<Integer>();
    	for(int i = 1 ; i <= n ; i++) {
    		if(list.get(i).prev.size()==0) que.offer(i);
    	}
    	if(que.isEmpty()) bw.write(0+"");
    	else {
    		while(!que.isEmpty()) {
    			int quesize = que.size();
    			for(int d = 0 ; d < quesize ; d++) {
    				int temp = que.poll();
    				list.get(temp).selected = true;
    				total.offer(temp);
    				for(int i = 0 ; i < list.get(temp).next.size() ; i++) {
    					int temp_ = list.get(temp).next.get(i);
    					list.get(temp_).prev.remove(list.get(temp_).prev.indexOf(temp));
    				}
    			}
    			for(int i = 1 ; i <= n ; i++) {
    	    		if(list.get(i).prev.size()==0 && !list.get(i).selected) que.offer(i);
    	    	}
    		}
    		if(total.size()!=n) bw.write(0+"");
    		else {
    			for(int i = 0 ; i < n ; i++) {
    				bw.write(total.poll()+"\n");
    			}
    		}
    	}
		bw.flush();
		bw.close();
		br.close();
    }
}
