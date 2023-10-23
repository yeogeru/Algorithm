import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/**
 * 
 * @author yeogeru
 * 		Binary Search, LIS
 */
public class BOJ12015 {
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    	int n = Integer.parseInt(br.readLine());
    	String[] input = (br.readLine()).split(" ");
    	List<Integer> list = new ArrayList<>();
    	for(int i = 0 ; i < n ; i++) {
    		int temp = Integer.parseInt(input[i]);
    		if(list.isEmpty() || list.get(list.size()-1) < temp) {
    			list.add(temp);
    			continue;
    		}
    		int index = Collections.binarySearch(list, temp);
    		if(index < 0) list.set(-index-1, temp); 
    		else list.set(index, temp);
    	}
    	bw.write(list.size()+"");
    	bw.flush();
    	bw.close();
    	br.close();
    }
}
