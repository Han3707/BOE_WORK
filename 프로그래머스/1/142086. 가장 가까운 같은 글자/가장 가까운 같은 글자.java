import java.util.*;
class Solution {
    public int[] solution(String s) {
        int n = s.length();
        int[] ans = new int[n];
        int[] last = new int[26];
        
        Arrays.fill(last,-1);
        
        for(int i=0; i<n; i++){
            int idx = s.charAt(i) - 'a';
            ans[i] = (last[idx] == -1) ? -1 : (i-last[idx]);
            last[idx] = i;
        }
        
        return ans;
    }
}