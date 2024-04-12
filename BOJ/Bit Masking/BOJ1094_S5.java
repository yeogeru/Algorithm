import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
/**
 * @author yeogeru
 *	  Bit Masking
 */
public class BOJ1094_S5 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String x = Integer.toBinaryString(Integer.parseInt(br.readLine()));
		int total = 0;
		for(int i = 0 ; i < x.length() ; i++) {
			if(x.charAt(i)=='1') ++total;
		}
		System.out.println(total);
	}
}
