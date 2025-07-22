import java.util.*;

class Solution {
    public int solution(int[] queue1, int[] queue2) {
        int answer = -1;
        
        Deque<Integer> q1 = new ArrayDeque<>();
        Deque<Integer> q2 = new ArrayDeque<>();
        
        long sum1 = 0, sum2 = 0;
        
        for(int i=0; i<queue1.length; i++){
            sum1 += queue1[i];
            sum2 += queue2[i];
            
            q1.add(queue1[i]);
            q2.add(queue2[i]);
        }
        
        long total = sum1 + sum2;
        if(total % 2 != 0) return -1;
        
        long target = total / 2;
        int cnt = 0;
        int maxcnt = (queue1.length + queue2.length) * 2;
        
        while(sum1 != target && cnt < maxcnt){
           if(sum1 > target){
               if(q1.isEmpty()) return -1;
               int n = q1.removeFirst();
               sum1 -= n;
               sum2 += n;
               q2.addLast(n);
           }else{
               if(q2.isEmpty()) return -1;
               int n = q2.removeFirst();
               sum2 -= n;
               sum1 += n;
               q1.addLast(n);
           }
           cnt++;
        } 
        
        if(sum1 == target){
            answer = cnt;
        }
        
        return answer;
    }
}