import java.util.*;

class Solution {
    public int solution(int[][] routes) {
        
        Arrays.sort(routes,(a,b) -> a[1] - b[1]);
        
        int mincamera = Integer.MIN_VALUE;
        int cnt = 0;
        
        
        
        for(int[] r : routes){
            int start = r[0];
            int end = r[1];
            
            if(mincamera < start){
                mincamera = end;
                cnt++;
            }
        }
        
        return cnt;
    }
}