import java.util.*;

class Solution {
    public String solution(String X, String Y) {
        
        StringBuilder sb = new StringBuilder();
        
        int[] cntx = new int[10];
        int[] cnty = new int[10];
        
        for(int i=0; i<X.length(); i++){
            cntx[X.charAt(i) - '0']++;
        }
        
        for(int i=0; i<Y.length(); i++){
            cnty[Y.charAt(i) - '0']++;
        }
        
        for(int i=9; i>=0; i--){
            int size = Math.min(cntx[i],cnty[i]);
            
            for(int k=0; k<size; k++){
                sb.append((char) ('0'+i));
            }
        }
        
        if(sb.length() == 0) return "-1";
        if(sb.charAt(0) == '0') return  "0";
        
        
        return sb.toString();
    }
}