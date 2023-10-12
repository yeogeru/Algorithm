import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;
/**
 * 
 * @author paternalism532
 *      구현
 */
public class SWEA9760 {
    static class Card implements Comparable<Card>{
        char pattern;
        int num;
        public Card(char pattern, int num) {
            super();
            this.pattern = pattern;
            this.num = num;
        }
        @Override
        public int compareTo(Card o) {
            return Integer.compare(this.num, o.num);
        }
    }
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int test_case = Integer.parseInt(br.readLine());
        StringTokenizer st;
        for(int t = 1 ; t <= test_case ; t++) {
            st = new StringTokenizer(br.readLine(), " ");
            List<Card> poker = new ArrayList<>();
            for(int i = 0 ; i < 5 ; i++) {
                String input = st.nextToken();
                int num = 0;
                char inputnum = input.charAt(1);
                if(inputnum>='A') {
                    switch(inputnum) {
                    case 'T': num=10; break;
                    case 'J': num=11; break;
                    case 'Q': num=12; break;
                    case 'K': num=13; break;
                    case 'A': num=14; break;
                    }
                } else num = inputnum-'0';
                poker.add(new Card(input.charAt(0), num));
            }
            Collections.sort(poker);
            boolean flush = true;
            boolean fourcard = false;
            boolean straight = true;
            boolean triple = false;
            boolean twopair = false;
            boolean onepair = false;
            char calcflush = poker.get(0).pattern;
            for(int i = 1 ; i < 5; i++) {
                if(poker.get(i).pattern!=calcflush) {
                    flush = false;
                    break;
                }
            }
            int calcstraight = poker.get(0).num;
            int c = 0;
            if(poker.get(4).num==14) {
                calcstraight = 1;
                c = -1;
            }
            for(int i = 1+c ; i < 5+c; i++) {
                int temp = calcstraight+i-c;
                if(poker.get(i).num!=temp) {
                    straight = false;
                    break;
                }
            }
            int[] numequal = new int[15];
            for(int i = 0 ; i < 5 ; i++) {
                ++numequal[poker.get(i).num];
            }
            for(int i = 0 ; i < 15 ; i++) {
                if(numequal[i]==4) {
                    fourcard = true;
                    break;
                }
                else if(numequal[i]==3) triple = true;
                else if(numequal[i]==2) {
                    if(onepair) {
                        onepair = false;
                        twopair = true;
                    } else onepair = true;
                }
            }
            String total = "High card";
            if(flush&&straight) total = "Straight Flush";
            else if(fourcard) total = "Four of a Kind";
            else if(triple&&onepair) total = "Full House";
            else if(flush) total = "Flush";
            else if(straight) total = "Straight";
            else if(triple) total = "Three of a kind";
            else if(twopair) total = "Two pair";
            else if(onepair) total = "One pair";
             
            bw.write("#"+t+" "+total+"\n");
        }
        bw.flush();
        bw.close();
    }
}
