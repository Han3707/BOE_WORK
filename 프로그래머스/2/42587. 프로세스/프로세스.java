import java.util.*;

class Solution {
    public int solution(int[] priorities, int location) {
        
        int cnt = 0;
        
        Deque<Integer> q = new ArrayDeque<>();
        
        for(int i=0; i<priorities.length; i++){
            q.offer(i);
        }
        
        while(!q.isEmpty()){
            int cur = q.poll();
            
            boolean check = false; // 나보다 큰 수가 있으면 먼저 실행되면 안댐
            
            for(int p:q){
                if(priorities[p] > priorities[cur]){
                    check = true;
                    break;
                }
            }
            
            if(check){
                q.offer(cur);
            }else{
                cnt++;
                if(cur == location){
                    return cnt;
                }
            }
        }
        
        return -1;
    }
}