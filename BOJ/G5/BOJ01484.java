import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
/**
 * 
 * @author paternalism532
 *
 *	수학
 */
public class BOJ01484 {
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    	int g = Integer.parseInt(br.readLine());
    	boolean nothing = false;
        Loop:
    	for(long i = 2 ; i <= 50000 ; i++) {
            if((i*i)-((i-1)*(i-1)) > g) break;
    		for(long j = i-1 ; j > 0 ; j--) {
    			if((i*i)-(j*j)==g) {
    				nothing = true;
    				bw.write(i+"\n");
    				break;
    			}
    		}
    	}
    	if(!nothing) bw.write(-1+"");
    	bw.flush();
    	bw.close();
    }
}
