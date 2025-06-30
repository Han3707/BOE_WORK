import java.util.*;

class Solution {
    private int maxCnt = 0; // 지금까지 찾은 최대 던전 탐험 수 (전역관리)
    
    public int solution(int k, int[][] dungeons) {
        boolean[] visited = new boolean[dungeons.length];
        List<int[]> current = new ArrayList<>();
        permute(dungeons,visited,current,k);  // 순열 생성 , 시뮬레이션
        
        return maxCnt; // 결과 반환
    }
    
    private void permute(int[][] dungeons,boolean[] visited, List<int[]> current,int k){
        if(current.size() == dungeons.length){ // 순열이 완성되면 시뮬레이션 돌림
            simul(current,k); // current 던전 방문 순서 , k 는 피로도 초기값
            return; 
        }
            
        for(int i=0; i<dungeons.length; i++){
        if(!visited[i]){
            visited[i] = true;
            current.add(dungeons[i]);
            permute(dungeons,visited,current,k);
            current.remove(current.size() -1); // 백트래킹
            visited[i] = false;
            
        }
      }
    }

            
    private void simul(List<int[]> path,int k){
        int cnt = 0;
        for (int[] d:path){
            int level = d[0], cost = d[1];  // 던전을 돌기 위한 최소 피로도 d[0],돌고 난 다음 차감값d[1]
            if(k>= level){
                k -= cost;
                cnt++;
            }else break;
        }
        
        maxCnt = Math.max(maxCnt,cnt); // 최대값 갱신
    }
    
    
}
        