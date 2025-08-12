import java.util.*;

class Solution {
    public int[] solution(int n, int[][] roads, int[] sources, int destination) {
        
        // 1. 인접 그래프로 연결
        List<Integer>[] graph = new ArrayList[n+1];
        for(int i=0; i<=n; i++){
            graph[i] = new ArrayList<>();
        }
        
        for(int[] r: roads){
            graph[r[0]].add(r[1]);
            graph[r[1]].add(r[0]);
        }
        
        // 2. 거리 배열 및 큐 초기화
        int[] dist = new int[n+1];
        Arrays.fill(dist,-1);
        
        
        // 3. 도착지로부터 모든 연결된 곳 최소 거리 계산.
        Deque<Integer> q = new ArrayDeque<>();
        q.add(destination);
        dist[destination] = 0;
        
       
        while(!q.isEmpty()){
            int cur = q.poll();
            
            for(int next: graph[cur]){
                
                if(dist[next] == -1){
                    dist[next] = dist[cur] + 1;
                    q.add(next);
                }
            }
        }
        
        // 4. 정답 출력
        int[] answer = new int[sources.length];
        for(int i=0; i<sources.length; i++){
            answer[i] = dist[sources[i]];
        }
        
        return answer;
    }
}