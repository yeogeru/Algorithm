import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/**
 * 
 * @author yeogeru
 *
 *	Brute Force, Backtracking
 */
public class BOJ16987_G5 {
	static int n, atk[], maxtotal = 0;
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	n = Integer.parseInt(br.readLine());
    	atk = new int[n];
    	int[] hp = new int[n];
    	for(int i = 0 ; i < n ; i++) {
    		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
    		hp[i] = Integer.parseInt(st.nextToken());
    		atk[i] = Integer.parseInt(st.nextToken());
    	}
    	breakEgg(0, hp);
    	System.out.println(maxtotal);
    }
    static void breakEgg(int index, int[] egg) {
    	if(index == n) {
    		int total = 0;
    		for(int i = 0 ; i < n ; i++) {
    			if(egg[i] <= 0) total++;
    		}
    		maxtotal = Math.max(maxtotal, total);
    		return;
    	}
    	if(egg[index] <= 0) {
    		breakEgg(index+1, egg);
    	} else {
    		boolean eggbroken = true;
    		for(int i = 0 ; i < n ; i++) {
    			if(i==index) continue;
    			if(egg[i] > 0) {
    				int[] newEgg = new int[n];
    				for(int j = 0 ; j < n ; j++) {
    					newEgg[j] = egg[j];
    				}
    				eggbroken = false;
    				newEgg[i]-=atk[index];
    				newEgg[index]-=atk[i];
    				breakEgg(index+1, newEgg);
    			}
    		}
    		if(eggbroken) breakEgg(index+1, egg);
    	}
    }
}
