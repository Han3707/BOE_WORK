import java.util.*;

class Solution {
    public int[] solution(String[] name, int[] yearning, String[][] photo) {
        
        //이름과 점수 해시맵에 저장
        Map<String, Integer> score = new HashMap<>();
        for(int i=0; i<name.length; i++){
            score.put(name[i],yearning[i]);
        }
            
        int[] answer = new int[photo.length];
        
        // 사진 하나씩 처리
        for(int i=0; i<photo.length; i++){
            int sum = 0;
            for (String person: photo[i]){
                // get 은 해당 값 들고오는데 getOrDefault 는 값이 없으면 내가 원하는 값 지정가능
                sum += score.getOrDefault(person,0);
                answer[i] = sum;
            }
        }
        
        
        return answer;
    }
}