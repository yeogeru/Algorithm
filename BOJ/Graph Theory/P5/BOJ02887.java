import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
/**
 * 
 * @author yeogeru
 *
 *	MST, Sorting
 */
public class BOJ02887 {
	static class Planet{
		int idx, x, y, z;
		public Planet(int idx, int x, int y, int z) {
			super();
			this.idx = idx;
			this.x = x;
			this.y = y;
			this.z = z;
		}
		@Override
		public String toString() {
			return "Planet [idx=" + idx + ", x=" + x + ", y=" + y + ", z=" + z + "]";
		}
		
	}
	static class Turnel implements Comparable<Turnel> {
		int from, to, dist;
		public Turnel(int from, int to, int dist) {
			super();
			this.from = from;
			this.to = to;
			this.dist = dist;
		}
		@Override
		public int compareTo(Turnel o) {
			return Integer.compare(this.dist, o.dist);
		}
		@Override
		public String toString() {
			return "Turnel [from=" + from + ", to=" + to + ", dist=" + dist + "]";
		}
		
	}
	static List<Planet> map = new ArrayList<>();
	static List<Turnel> turnel = new ArrayList<>();
	static int[] parents;
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	int n = Integer.parseInt(br.readLine());
    	parents = new int[n];
    	for(int i = 0 ; i < n ; i++) parents[i] = i;
    	for(int i = 0 ; i < n ; i++) {
    		String[] input = (br.readLine()).split(" ");
    		int x = Integer.parseInt(input[0]);
    		int y = Integer.parseInt(input[1]);
    		int z = Integer.parseInt(input[2]);
    		map.add(new Planet(i, x, y, z));
    	}
    	for(int d = 0 ; d < 3 ; d++) {
    		sortPlanet(d);
    		for(int i = 0 ; i < n-1 ; i++) {
    			switch(d) {
    			case 0:
        			turnel.add(new Turnel(map.get(i).idx, map.get(i+1).idx, Math.abs(map.get(i+1).x - map.get(i).x)));
    				break;
    			case 1:
        			turnel.add(new Turnel(map.get(i).idx, map.get(i+1).idx, Math.abs(map.get(i+1).y - map.get(i).y)));
    				break;
    			case 2:
        			turnel.add(new Turnel(map.get(i).idx, map.get(i+1).idx, Math.abs(map.get(i+1).z - map.get(i).z)));
    				break;
    			}
    		}
    	}
    	Collections.sort(turnel);
    	int total = 0;
    	for(Turnel i : turnel) {
    		if(union(i.from, i.to)) {
    			total+=i.dist;
    		}
    	}
    	System.out.println(total);
    }
    static int find(int a) {
    	if(parents[a] == a) return a;
    	return parents[a] = find(parents[a]);
    }
    static boolean union(int a, int b) {
    	int aRoot = find(a);
    	int bRoot = find(b);
    	if(aRoot == bRoot) return false;
    	if(aRoot < bRoot) parents[bRoot] = aRoot;
    	else parents[aRoot] = bRoot;
    	return true;
    }
    static void sortPlanet(int st) {
    	Collections.sort(map, new Comparator<Planet>() {
			@Override
			public int compare(Planet o1, Planet o2) {
				switch(st) {
				case 0:
					return Integer.compare(o1.x, o2.x);
				case 1:
					return Integer.compare(o1.y, o2.y);
				default:
					return Integer.compare(o1.z, o2.z);
				}
			}
		});
    }
}
