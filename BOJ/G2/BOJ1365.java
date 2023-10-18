import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
/**
 * 
 * @author paternalism532
 *
 *	이분탐색
 */
public class BOJ1365 {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		List<Integer> list = new ArrayList<>();
		for(int i = 0 ; i < n ; i++) {
			int temp = Integer.parseInt(st.nextToken());
			if(list.isEmpty() || list.get(list.size()-1) < temp) {
				list.add(temp);
				continue;
			}
			int left = 0, right = list.size()-1, mid = 0;
			while(left < right) {
				mid = (left+right)/2;
				if(list.get(mid) < temp) left = mid+1;
				else right = mid;
			}
			list.set(left, temp);
		}
		System.out.println(n-list.size());
	}
}
