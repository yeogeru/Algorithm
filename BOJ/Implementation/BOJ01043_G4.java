import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
/**
 * 
 * @author yeogeru
 *
 *	Graph Search
 */
public class BOJ01043_G4 {
	static List<List<Integer>> party = new ArrayList<>();
	static boolean[] trueman;
	static int n, m;
	static int max = 0;
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine(), " ");
    	n = Integer.parseInt(st.nextToken());
    	m = Integer.parseInt(st.nextToken());
    	st = new StringTokenizer(br.readLine(), " ");
    	int t = Integer.parseInt(st.nextToken());
    	trueman = new boolean[n+1];
    	for(int i = 0 ; i < t ; i++) {
    		trueman[Integer.parseInt(st.nextToken())] = true;
    	}
    	for(int i = 0 ; i < m ; i++) {
    		st = new StringTokenizer(br.readLine(), " ");
    		party.add(new ArrayList<>());
    		int partysize = Integer.parseInt(st.nextToken());
    		for(int j = 0 ; j < partysize ; j++) {
    			party.get(i).add(Integer.parseInt(st.nextToken()));
    		}
    	}
    	gura(0, new boolean[n+1], new boolean[n+1], 0);
    	System.out.println(max);
    }
    
    static void gura(int index, boolean[] heardlie, boolean[] heardtrue, int liecnt) {
    	if(index==m) {
    		max = Math.max(max, liecnt);
    		return;
    	}
    	boolean[] temphl = new boolean[n+1];
    	boolean[] tempht = new boolean[n+1];
    	for(int i = 1 ; i <= n ; i++) {
    		temphl[i] = heardlie[i];
    		tempht[i] = heardtrue[i];
    	}
    	boolean truePossible = true;
    	boolean liePossible = true;
    	for(int i = 0 ; i < party.get(index).size() ; i++) {
    		int tempIdx = party.get(index).get(i);
    		if(heardlie[tempIdx]) {
    			truePossible = false;
    			break;
    		}
    		tempht[tempIdx] = true;
    	}
    	for(int i = 0 ; i < party.get(index).size() ; i++) {
    		int tempIdx = party.get(index).get(i);
    		if(trueman[tempIdx]) {
    			liePossible = false;
    			break;
    		}
    		if(heardtrue[tempIdx]) {
    			liePossible = false;
    			break;
    		}
    		temphl[tempIdx] = true;
    	}
    	if(liePossible) {
    		gura(index+1, temphl, heardtrue, liecnt+1);
    	}
    	if(truePossible) {
    		gura(index+1, heardlie, tempht, liecnt);
    	}
    }
}
