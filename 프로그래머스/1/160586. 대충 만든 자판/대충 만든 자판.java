import java.util.*;

class Solution {
    public int[] solution(String[] keymap, String[] targets) {
        
        final int INF = 10000;
        
        int n = keymap.length;
        int m = targets.length;
        
        int[] minkey = new int[26];
        int[] answer = new int[m];
        
        
        Arrays.fill(minkey,INF);
        
        
        for(int i=0; i<n; i++){
            for(int j=0; j<keymap[i].length(); j++){
                int cnt = keymap[i].charAt(j) - 'A';
                minkey[cnt] = Math.min(minkey[cnt],j+1);
            }
        }
        
        for(int i=0; i<m; i++){
            String t = targets[i];
            
            int sum = 0;
            
            for(int k=0; k<t.length(); k++){
                int idx = t.charAt(k) - 'A';
                if(minkey[idx] == INF){
                    sum = -1;
                    break;
                }
                sum += minkey[idx];
            }
            
            answer[i] = sum;
        }
        
        
        return answer;
    }
}