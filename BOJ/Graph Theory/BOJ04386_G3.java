import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/**
 * 
 * @author yeogeru
 *
 *	MST
 */
public class BOJ04386_G3 {
	static class Star implements Comparable<Star>{
		int prev, next;
		double dist;
		public Star(int prev, int next, double dist) {
			super();
			this.prev = prev;
			this.next = next;
			this.dist = dist;
		}
		@Override
		public int compareTo(Star o) {
			return Double.compare(this.dist, o.dist);
		}
	}
	static int[] parents;
	public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	int n = Integer.parseInt(br.readLine());
    	parents = new int[n];
    	for(int i = 0 ; i < n ; i++) parents[i] = i;
    	double[][] graph = new double[n][2];
    	for(int i = 0 ; i < n ; i++) {
    		String[] input = (br.readLine()).split(" ");
    		graph[i][0] = Double.parseDouble(input[0]);
    		graph[i][1] = Double.parseDouble(input[1]);
    	}
    	List<Star> list = new ArrayList<Main.Star>();
    	for(int i = 0 ; i < n-1 ; i++) {
    		for(int j = i+1 ; j < n ; j++) {
    			double dist_x = graph[i][0]-graph[j][0];
    			double dist_y = graph[i][1]-graph[j][1];
    			double distance = Math.sqrt((dist_x*dist_x)+(dist_y*dist_y));
    			list.add(new Star(i, j, distance));
    		}
    	}
    	Collections.sort(list);
    	double total = 0;
    	for(int i = 0 ; i < list.size() ; i++) {
    		int a = list.get(i).prev;
    		int b = list.get(i).next;
    		if(union(a, b)) {
    			total+=list.get(i).dist;
    		}
    	}
    	System.out.println(String.format("%.2f", total));
    }
	static int find(int a) {
		if(a==parents[a]) return a;
		return parents[a] = find(parents[a]);
	}
	static boolean union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		if(aRoot==bRoot) return false;
		if(aRoot > bRoot) parents[bRoot] = aRoot;
		else parents[aRoot] = bRoot;
		return true;
	}
}
