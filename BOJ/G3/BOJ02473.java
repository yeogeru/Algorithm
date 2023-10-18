import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
/**
 * 
 * @author paternalism532
 *
 *	투 포인터, 정렬
 */
public class BOJ02473 {
	static List<Long> list;
	static int[] minTotal = new int[3];
	static long min = Long.MAX_VALUE;
	static int n;
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	n = Integer.parseInt(br.readLine());
    	String[] input = (br.readLine()).split(" ");
    	list = new ArrayList<Long>();
    	for(int i = 0 ; i < n ; i++) list.add(Long.parseLong(input[i]));
    	Collections.sort(list);
    	for(int i = 0 ; i < n ; i++) chemistry(i);
    	Arrays.sort(minTotal);
    	System.out.println(list.get(minTotal[0])+" "+
    	list.get(minTotal[1])+" "+list.get(minTotal[2]));
    }
    
    static void chemistry(int index) {
    	int left = 0, right = n-1;
    	long temp = Long.MAX_VALUE;
    	while(left < right) {
    		if(left==index) left++;
    		if(right==index) right--;
    		if(left>=right) break;
    		temp = list.get(left)+list.get(right)+list.get(index);
    		if(min > Math.abs(temp)) {
    			min = Math.abs(temp);
    			minTotal[0] = left;
    			minTotal[1] = index;
    			minTotal[2] = right;
    		}
    		if(temp < 0) left++;
    		else right--;
    	}
    }
}
