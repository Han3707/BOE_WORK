import java.util.*;

class Solution {
    public int solution(int n) {
        
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        
        for(int i=1; i<n; i++){
            if(n % i == 1) pq.offer(i);
        }
        
        
        return pq.peek();
    }
}