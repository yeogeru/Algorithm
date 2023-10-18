import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
/**
 * 
 * @author paternalism532
 *
 * DFS, DP, PriorityQueue 구현
 */
public class BOJ29792 {
	static int n, m, k;
	static long[] maplechar;
	static PriorityQueue<Long> charmeso = new PriorityQueue<>(new Comparator<Long>() {
		@Override
		public int compare(Long o1, Long o2) {
			return Long.compare(-o1, -o2);
		}
	});
	static long[][] bossmob;
	static int charidx = 0;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		maplechar = new long[n]; // 데미지
		bossmob = new long[k][2]; // 0:체력, 1:메소
		for(int i = 0 ; i < n ; i++) {
			maplechar[i] = Long.parseLong(br.readLine());
		}
		for(int i = 0 ; i < k ; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			bossmob[i][0] = Long.parseLong(st.nextToken());
			bossmob[i][1] = Long.parseLong(st.nextToken());
		}
		for(int i = 0 ; i < n ; i++) {
			charidx = i;
			charmeso.add(huntingboss(0, 0, 0, 900, new long[k]));
		}
		long result = 0;
		for(int i = n-1 ; i > n-m-1 ; i--) {
			result+=charmeso.poll();
		}
		System.out.println(result);
	}
	
	public static long huntingboss(int index, int start, long meso, long time, long[] dp) {
		if(index == k) return meso;
		boolean boss_no_die = true;
		for(int i = start ; i < k ; i++) {
			if(maplechar[charidx]*time>=bossmob[i][0]) {
				long bosstime = bossmob[i][0]/maplechar[charidx];
				if(bossmob[i][0]%maplechar[charidx]!=0) bosstime++;
				dp[index] = Math.max(dp[index], huntingboss(index+1, i+1, meso+bossmob[i][1], time-bosstime, dp));
				boss_no_die = false;
			}
		}
		if(boss_no_die) dp[index] = Math.max(dp[index], meso);
		return dp[index];
	}
}
