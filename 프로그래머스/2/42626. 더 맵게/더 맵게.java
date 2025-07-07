import java.util.*;

class Solution {
    public int solution(int[] scoville, int K) {
        
        //우선순위 큐
        PriorityQueue<Integer> q = new PriorityQueue<>();
        
        //스코빌 지수 큐에 추가
        for(int s:scoville){
            q.offer(s);
        }
        //카운트 최소값 저장 변수
        int minCnt = 0;
        
        // 가장 작은 값이 k 가 될 때까지 반복
        while(q.size() > 1 && q.peek() < K){
            // 가장 작은 값 두개 추출
            int min1 = q.poll();
            int min2 = q.poll();
            
            //스코빌 지수 계산
            int newScoville = min1 + (min2 * 2);
            
            //새로운 스코빌 큐에 추가
            q.offer(newScoville);
            
            // 카운트 증가
            minCnt++;
        }
        
        if(q.peek() < K){
            minCnt = -1;
        }
        
        return minCnt;
    }
}