import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
/**
 * 
 * @author yeogeru
 *	Segment Tree, Binary Search
 */
public class BOJ01168_P3 {
    static int[] tree;
    static int start, n, k;
	public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    	String[] input = (br.readLine()).split(" ");
    	n = Integer.parseInt(input[0]);
    	k = Integer.parseInt(input[1]);
    	int h = (int) Math.ceil(Math.log(n)/Math.log(2));
    	start = (int) Math.pow(2, h);
    	tree = new int[start*2];
    	for(int i = start, e = start+n ; i < e ; i++) tree[i] = 1;
    	for(int i = tree.length-1 ; i >= 1 ; i--) tree[i / 2] += tree[i];
    	int num = k;
    	bw.write("<");
    	for(int i = 1 ; i <= n ; i++) {
    		bw.write(yosepus(num)+"");
    		num+=(k-1);
    		if(i!=n) {
    			bw.write(", ");
    			num=(num-1)%(n-i)+1;
    		}
    	}
    	bw.write(">");
    	bw.flush();
    	bw.close();
    	br.close();
    }
	
	static int yosepus(int num) {
		int index = 1;
		while(index < start) {
			if(tree[index*2] >= num) index*=2;
			else {
				num-=tree[index*2];
				index=index*2+1;
			}
		}
		tree[index] = 0;
		for(int i = index ; i > 1 ; i/=2) tree[i/2]--;
		return index-start+1;
	}
}
