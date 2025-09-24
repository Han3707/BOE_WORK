import java.util.*;

class Solution {
    public int[] solution(int[] lottos, int[] win_nums) {
        int[] answer = new int[2];
        
        Arrays.sort(lottos);
        Arrays.sort(win_nums);
        
        int cnt = 0; // 0의 개수
        int win = 0; // 당첨개수
        
        
        
        for(int i=0; i<lottos.length; i++){
            if(lottos[i] == 0){
                cnt++;
            }
            
            for(int w:win_nums){
                if(w == lottos[i]){
                    win++;
                }
            }
        }
        
        answer[1] = count(win);
        answer[0] = count(win+cnt);
        
        return answer;
    }
    
    private int count(int num){
        
        if(num == 6){
            return 1;
        }else if(num == 5){
            return 2;
        }else if(num == 4){
            return 3;
        }else if(num == 3){
            return 4;
        }else if(num == 2){
            return 5;
        }else{
            return 6;
        }
    }
}