import java.util.*;

class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        
        int idx = speeds.length;
        List<Integer> res = new ArrayList<>(); // 날짜별 카운트 넣을 리스트 (정답 리스트)
        
        int[] days = new int[idx]; // 날짜 계산 
        for(int i=0; i<idx; i++){
            int done = 100 - progresses[i];
            days[i] = (int) Math.ceil((double) done / speeds[i]);
        }
        
        int maxDay = days[0];
        int cnt = 1;
        
        // 첫번째 days 완료일은 maxDay 에 저장해놨으므로 1부터 시작
        for(int i=1; i<days.length; i++){
          if(days[i] <= maxDay){
              cnt++;
          }  else{
              res.add(cnt);
              maxDay = days[i];
              cnt = 1; // 초기화
          }
        }
        res.add(cnt);
        
        int[] answer = new int[res.size()]; 
        for (int i = 0; i < res.size(); i++) {
            answer[i] = res.get(i); 
        }
        
    
        return answer;
    }
}