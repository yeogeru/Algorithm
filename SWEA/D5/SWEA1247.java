import java.awt.Point;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
/**
 * 
 * @author paternalism532
 *      NP
 */
public class SWEA1247 {
    static Point[] clients;
    static int[] indexArr;
    static int n;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int test_case = Integer.parseInt(br.readLine());
        for(int t = 1 ; t <= test_case ; t++) {
            n = Integer.parseInt(br.readLine());
            int min = Integer.MAX_VALUE;
            String[] input = (br.readLine()).split(" ");
            Point company = new Point(Integer.parseInt(input[0]), Integer.parseInt(input[1]));
            Point home = new Point(Integer.parseInt(input[2]), Integer.parseInt(input[3]));
            clients = new Point[n];
            indexArr = new int[n];
            for(int i = 0 ; i < n ; i++) {
                indexArr[i] = i;
                clients[i] = new Point(Integer.parseInt(input[(i+1)*2+2]), Integer.parseInt(input[((i+1)*2)+3]));
            }
            do {
                int cnt = 0;
                int temp_total = Math.abs(clients[indexArr[0]].x-company.x)+Math.abs(clients[indexArr[0]].y-company.y);
                while(++cnt < n) {
                    temp_total += Math.abs(clients[indexArr[cnt]].x-clients[indexArr[cnt-1]].x)+Math.abs(clients[indexArr[cnt]].y-clients[indexArr[cnt-1]].y);
                }
                temp_total += Math.abs(clients[indexArr[n-1]].x-home.x)+Math.abs(clients[indexArr[n-1]].y-home.y);
                min = Math.min(min, temp_total);
            } while(NP());
            bw.write("#"+t+" "+min+"\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }
     
    public static boolean NP() {
        int lp = n-1;
        while(lp > 0 && indexArr[lp-1] >= indexArr[lp]) lp--;
        if(lp==0) return false;
         
        int nb = n-1;
        while(indexArr[lp-1] >= indexArr[nb]) nb--;
        swap(indexArr, nb, lp-1);
         
        for(int left = lp, right = indexArr.length-1 ; left < right ; left++, right--) swap(indexArr, left, right);
        return true;
    }
     
    public static void swap(int[] indexArr, int i, int j) {
        int temp = indexArr[i];
        indexArr[i] = indexArr[j];
        indexArr[j] = temp;
    }
     
}
