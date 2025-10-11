import java.util.*;

class Solution {
    public String solution(int[] food) {
        String answer = "";
        
        StringBuilder sb = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        for(int i=1; i<food.length; i++){
            if(food[i] / 2  >= 1){
                for(int z=0; z<food[i]/2; z++){
                    sb.append(i);
                }
            }
        }
        
        sb2.append(sb);
        sb2.reverse();
        sb.append("0");
        sb.append(sb2);
        
        
        return sb.toString();
    }
}