import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
public class Boj12094 {
    static int n, max = 2;
    static int[][] map;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());
        map = new int[n][n];
        for(int i = 0 ; i < n ; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for(int j = 0 ; j < n ; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        if(n==1) System.out.println(map[0][0]);
        else {
        	play2048(1, map, new boolean[4]);
        	System.out.println(max);
        }
    }
    static void play2048(int cnt, int[][] map, boolean[] notmoved) {
        for(int d = 0 ; d < 4 ; d++) {
        	if(notmoved[d]) {
        		notmoved[d] = false;
        		continue;
        	}
        	int[][] tmap = new int[n][n];
            for(int i = 0 ; i < n ; i++) {
            	for(int j = 0 ; j < n ; j++) {
            		tmap[i][j] = map[i][j];
            	}
            }
            int notmovecnti = 0;
            int notmovecntj = 0;
            if(d==0) {
            	for(int i = 0 ; i < n ; i++) {
            		int idx = 0, block = 0;
        			notmovecntj = 0;
            		for(int j = 0 ; j < n ; j++) {
            			if(tmap[j][i]!=0) {
            				if(block == tmap[j][i]) {
            					tmap[idx - 1][i] = block *2;
            					block = 0;
            					tmap[j][i] = 0;
            				} else {
            					block = tmap[j][i];
            					tmap[j][i] = 0;
            					tmap[idx][i] = block;
            					++idx;
            				}
            			}
            			if(tmap[i][j]==map[i][j]) ++notmovecntj;
            		}
            		if(notmovecntj==n) ++notmovecnti;
            	}
            } else if(d==1) {
            	for(int i = 0 ; i < n ; i++) {
            		int idx = n-1, block = 0;
        			notmovecntj = 0;
            		for(int j = n-1 ; j >= 0 ; j--) {
            			if(tmap[j][i]!=0) {
            				if(block == tmap[j][i]) {
            					tmap[idx + 1][i] = block *2;
            					block = 0;
            					tmap[j][i] = 0;
            				} else {
            					block = tmap[j][i];
            					tmap[j][i] = 0;
            					tmap[idx][i] = block;
            					--idx;
            				}
            			}
            			if(tmap[j][i]==map[j][i]) ++notmovecntj;
            		}
            		if(notmovecntj==n) ++notmovecnti;
            	}
            } else if(d==2) {
            	for(int i = 0 ; i < n ; i++) {
            		int idx = 0, block = 0;
        			notmovecntj = 0;
            		for(int j = 0 ; j < n ; j++) {
            			if(tmap[i][j]!=0) {
            				if(block == tmap[i][j]) {
            					tmap[i][idx - 1] = block *2;
            					block = 0;
            					tmap[i][j] = 0;
            				} else {
            					block = tmap[i][j];
            					tmap[i][j] = 0;
            					tmap[i][idx] = block;
            					++idx;
            				}
            			}
            			if(tmap[i][j]==map[i][j]) ++notmovecntj;
            		}
            		if(notmovecntj==n) ++notmovecnti;
            	}
            } else {
            	for(int i = 0 ; i < n ; i++) {
            		int idx = n-1, block = 0;
        			notmovecntj = 0;
            		for(int j = n-1 ; j >= 0 ; j--) {
            			if(tmap[i][j]!=0) {
            				if(block == tmap[i][j]) {
            					tmap[i][idx + 1] = block *2;
            					block = 0;
            					tmap[i][j] = 0;
            				} else {
            					block = tmap[i][j];
            					tmap[i][j] = 0;
            					tmap[i][idx] = block;
            					--idx;
            				}
            			}
            			if(tmap[i][j]==map[i][j]) ++notmovecntj;
            			if(j==1 && tmap[i][j-1]==map[i][j-1]) ++notmovecntj;
            		}
            		if(notmovecntj==n) ++notmovecnti;
            	}
            }
            int total = 0;
            boolean[] tnotmoved = new boolean[4];
            if(notmovecnti==n) tnotmoved[d] = true;
            if(cnt==10) {
            	for(int i = 0 ; i < n ; i++) {
            		for(int j = 0 ; j < n ; j++) {
            			total = Math.max(total, tmap[i][j]);
            		}
            	}
            	max = Math.max(max, total);
            }
            else play2048(cnt+1, tmap, tnotmoved);
        }
    }
}
