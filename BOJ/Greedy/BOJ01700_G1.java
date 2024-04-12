import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;
/**
 * 
 * @author yeogeru
 *
 *	Greedy Algorithm
 */
public class BOJ01700_G1{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int[] useSeq = new int[k];
        int[] use = new int[k+1];
        boolean[] curUse = new boolean[k+1];
        
        String[] input = (br.readLine()).split(" ");
        for(int i = 0 ; i < k ; i++) {
        	useSeq[i] = Integer.parseInt(input[i]);
        	use[useSeq[i]]++;
        }
        int total = 0;
        List<Integer> multitap = new ArrayList<Integer>();
        for(int i = 0 ; i < k ; i++) {
        	if(multitap.size() < n) {
        		if(!curUse[useSeq[i]]) {
        			multitap.add(useSeq[i]);
        		}
        	} else {
        		if(!curUse[useSeq[i]]) {
        			boolean alluse = true;
        			for(int j = 0 ; j < n ; j++) {
        				if(use[multitap.get(j)]==0) {
        					alluse = false;
        					curUse[multitap.get(j)] = false;
        					multitap.remove(j);
        					break;
        				}
        			}
        			if(alluse) {
        				int idx = 0;
        				boolean[] visit = new boolean[n];
        				for(int j = i+1 ; j < k ; j++) {
        					for(int t = 0 ; t < n ; t++) {
        						if(visit[t]) continue;
        						if(useSeq[j]==multitap.get(t)) {
        							visit[t] = true;
        							idx = t;
        						}
        					}
        				}
        				curUse[multitap.get(idx)] = false;
        				multitap.remove(idx);
        			}
        			multitap.add(useSeq[i]);
        			total++;
        		}
        	}
        	curUse[useSeq[i]] = true;
        	use[useSeq[i]]--;
        }
        System.out.println(total);
    }
}
