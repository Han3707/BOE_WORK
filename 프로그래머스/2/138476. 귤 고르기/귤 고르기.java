import java.util.*;

class Solution {
    public int solution(int k, int[] tangerine) {
        int answer = 0;
        
        HashMap<Integer,Integer> m = new HashMap<>();
        
        for(int t:tangerine){
            m.put(t,m.getOrDefault(t,0)+1);
        }
        
        List<Integer> list = new ArrayList<>(m.values());
        
        list.sort((a,b)-> b - a);
        
        int cnt = 0;
        int num = 0;
        
        for(int x:list){
            num += x;
            cnt++;
            
            if(num >= k) break;
        }
        
        return cnt;
    }
}