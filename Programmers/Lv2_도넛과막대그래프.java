import java.util.*;
class Solution {
    public int[] solution(int[][] edges) {
        int[] answer = {0, 0, 0, 0};
        Queue<Integer> que = new ArrayDeque<>();
        List<List<Integer>> list = new ArrayList<>();
        for(int i = 0 ; i <= 1000000 ; i++) {
            list.add(new ArrayList<>());
        }
        
        int[] depth = new int[1000001];
        int[] depth_out = new int[1000001];
        Arrays.fill(depth, -1);
        
        for(int i = 0 ; i < edges.length ; i++) {
            int a = edges[i][0];
            int b = edges[i][1];
            list.get(a).add(b);
            if(depth[a] < 0) depth[a] = 0;
            if(depth[b] < 0) depth[b] = 0;
            ++depth_out[a];
            ++depth[b];
        }
        
        int max = 0;
        for(int i = 1 ; i < depth.length ; i++) {
            if(depth[i] == 0) {
                if(max < depth_out[i]) {
                    max = depth_out[i];
                    answer[0] = i;
                }
            }
        }
        
        boolean[] visit = new boolean[1000001];
        for(int q : list.get(answer[0])) {
            que.add(q);
            visit[q] = true;
        }
        
        while(!que.isEmpty()) {
            int t = que.poll();
            int size = list.get(t).size();
            if(size == 0) ++answer[2];
            else if(size == 1) {
                int next = list.get(t).get(0);
                if(visit[next]) {
                    ++answer[1];
                    continue;
                }
                visit[next] = true;
                que.add(next);
            } else if(size == 2) ++answer[3];
        }
        
        return answer;
    }
}