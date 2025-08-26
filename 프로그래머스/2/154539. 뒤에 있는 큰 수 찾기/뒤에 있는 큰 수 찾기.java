import java.util.*;

class Solution {
    
    // 방법 1: ArrayDeque를 스택처럼 사용 (가장 기본적)
    public int[] solution(int[] numbers) {
        int[] answer = new int[numbers.length];
        Deque<Integer> deque = new ArrayDeque<>();
        
        Arrays.fill(answer, -1);
        
        for(int i = 0; i < numbers.length; i++) {
            // 현재 원소가 덱 최상단 인덱스의 원소보다 클 때까지 반복
            while(!deque.isEmpty() && numbers[deque.peekLast()] < numbers[i]) {
                int index = deque.pollLast();
                answer[index] = numbers[i];
            }
            deque.offerLast(i);
        }
        
        return answer;
    }
}