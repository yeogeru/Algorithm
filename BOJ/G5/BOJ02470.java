import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/**
 * 
 * @author paternalism532
 *
 *	투 포인터, 정렬
 */
public class BOJ02470 {
	static List<Integer> arr = new ArrayList<>();
	static int[] total = new int[2];
	static int min = Integer.MAX_VALUE;
	static int n;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		String[] input = (br.readLine()).split(" ");
		for(int i = 0 ; i < n ; i++) arr.add(Integer.parseInt(input[i]));
        Collections.sort(arr);    
        int start = 0;
		int end = n-1;
		int temp = 0;
		int mintotal = Integer.MAX_VALUE;
		int[] minArr = new int[2];
		while(start!=end) {
			temp = arr.get(start)+arr.get(end);
			if(temp > 0) {
				if(mintotal > Math.abs(temp)) {
					minArr[0] = arr.get(start);
					minArr[1] = arr.get(end);
					mintotal = Math.abs(temp);
				}
				end--;
			} else if(temp < 0) {
				if(mintotal > Math.abs(temp)) {
					minArr[0] = arr.get(start);
					minArr[1] = arr.get(end);
					mintotal = Math.abs(temp);
				}
				start++;
			} else {
				minArr[0] = arr.get(start);
				minArr[1] = arr.get(end);
				break;
			}
		}
		System.out.println(minArr[0]+" "+minArr[1]);
	}
}
