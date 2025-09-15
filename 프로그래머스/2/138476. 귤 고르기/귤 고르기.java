import java.util.*;

class Solution {
    public int solution(int k, int[] tangerine) {
        
        HashMap<Integer,Integer> map = new HashMap<>();
        
        for(int x:tangerine){
            map.put(x,map.getOrDefault(x,0)+1);
        }
        
        List<Integer> list = new ArrayList<>(map.values());
        list.sort(Comparator.reverseOrder());
        
        int cnt = 0;
        int ans = 0;
        for(int l:list){
            cnt += l;
            ans++;
            if(cnt >= k) break;
        }
        
        return ans;
    }
}