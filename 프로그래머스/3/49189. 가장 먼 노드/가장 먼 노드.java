import java.util.*;

class Solution {
    public int solution(int n, int[][] edge) {
        
        // 1. 인접 리스트로 그래프 표현 
        List<Integer>[] graph = new ArrayList[n+1];
        for(int i=0; i<=n; i++){
            graph[i] = new ArrayList<>();
        }
        
        for(int[] e: edge){
            graph[e[0]].add(e[1]);
            graph[e[1]].add(e[0]);  // 양방향이므로 양쪽
        }
        
        // 2. 거리 배열 및 큐 초기화
        int[] dist = new int[n+1];
        Arrays.fill(dist,-1); 
        
        Deque<Integer> q = new ArrayDeque<>();
        
        // 시작 노드 0으로 설정 후 큐에 추가
        dist[1] = 0;
        q.add(1);
        
        int maxdist = 0;
        while(!q.isEmpty()){
            int cur = q.poll();
            maxdist = Math.max(maxdist,dist[cur]);
            
            for(int next: graph[cur]){
                // 방문하지 않았다면 
                if(dist[next] == -1){
                    dist[next] = dist[cur] + 1; // 원래 거리보다 +1
                    q.add(next);
                }
            }
        }
        
        // 4. 최장 거리 노드 개수 카운트
        int cnt = 0;
        for(int d:dist){
            if(d == maxdist){
                cnt++;
            }
        }
        
        return cnt;
    }
}