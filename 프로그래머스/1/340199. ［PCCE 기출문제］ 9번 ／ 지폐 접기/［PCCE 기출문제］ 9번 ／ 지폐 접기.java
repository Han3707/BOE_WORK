import java.util.*;

class Solution {
    public int solution(int[] wallet, int[] bill) {
        int answer = 0;
        
        int a = bill[0];
        int b=  bill[1];
        
        Arrays.sort(wallet); // 지갑 정렬 오름차순
        
        
        // 종료조건 지갑안에 두 지페가 다 들어갈 경우
        while(true){
            
            // 접으면 크기가 달라지므로 매 반복문바다 큰 수 , 작은 수 지정
            int s = Math.min(a,b);
            int l = Math.max(a,b);
            
            if(s <= wallet[0] && l <= wallet[1]) break;
            
            if(a >= b){
                a /= 2;
            }else{
                b /= 2;
            }
            
            answer++;
            
        }
        
        
        return answer;
    }
}