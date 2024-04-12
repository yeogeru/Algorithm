import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
/**
 * 
 * @author yeogeru
 *
 *	Brute Force
 */
public class BOJ01107_G5 {
	static int integer_N;
	static String n;
	static boolean[] error;
	static int total = 0;
	static int output = 100;
	static int max = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	n = br.readLine();
    	integer_N = Integer.parseInt(n);
    	int m = Integer.parseInt(br.readLine());
    	error = new boolean[10];
    	if(m!=0) {
    		String[] input = (br.readLine()).split(" ");
    		for(int i = 0 ; i < m ; i++) {
        		error[Integer.parseInt(input[i])] = true;
        	}
    	}
    	max = Math.abs(integer_N - 100);
    	calc(new ArrayList<Integer>());
		int outputleng = String.valueOf(output).length();
		if(output==100) outputleng = 0;
		total+=outputleng+Math.abs(integer_N-output);
		System.out.println(total);
    }
    
    static void calc(List<Integer> list) {
    	if(list.size()==7) return;
    	if(list.size()!=0) {
    		int temp = 0;
        	for(int i = 0 ; i < list.size() ; i++) {
        		temp+=list.get(i);
        		if(i!=list.size()-1) temp*=10;
        	}
        	int templeng = String.valueOf(temp).length();
        	if(temp==100) templeng = 0;
        	if(max > Math.abs(integer_N-temp)+templeng) {
        		max = Math.abs(integer_N-temp)+templeng;
        		output = temp;
        	}
    	}
    	for(int i = 0 ; i <= 9 ; i++) {
    		if(error[i]) continue;
    		list.add(i);
    		calc(list);
    		list.remove(list.size()-1);
    	}
    }
}
