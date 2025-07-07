import java.util.*;

class Solution {
    public int[] solution(String[] operations) {
        PriorityQueue<Integer> minq = new PriorityQueue<>(); // 오름차순 정렬
        PriorityQueue<Integer> maxq = new PriorityQueue<>(Collections.reverseOrder()); // 내림차순 정렬 
        
        for(String oper: operations){
            String[] parts = oper.split(" ");
            String command = parts[0];
            int number = Integer.parseInt(parts[1]);
            
            if(command.equals("I")){
                minq.offer(number);
                maxq.offer(number);
            }
            else if(command.equals("D")){
                if(minq.isEmpty()){
                    continue;
                }
                
                if(number == 1){
                    int max = maxq.poll(); // 최댓값 큐에서 최대값 꺼내기
                    minq.remove(max); // 최소값 큐에서도 최대값 삭제하기
                }else{
                    int min = minq.poll(); // 최소값 큐에서 최소값 꺼내기
                    maxq.remove(min); // 최대값 큐에서 최소값 삭제하기
                }
            }
        }
        
        int[] answer = {};
        if(minq.isEmpty()){
            answer = new int[]{0,0};
        }else{
            answer = new int[]{maxq.peek(),minq.peek()};
        }
        
        return answer;
    }
}