import java.util.*;

class Solution {
    public String[] solution(String[] players, String[] callings) {
        
        HashMap<String,Integer> hash = new HashMap<>();
        
        for(int i=0; i<players.length; i++){
            hash.put(players[i],i);
        }
        
        for(String c:callings){
            int idx = hash.get(c);
            if(idx == 0) continue;
            
            String next = players[idx-1];
            String now = players[idx];
            
            players[idx-1] = now;
            players[idx] = next;
            
            hash.put(next,idx);
            hash.put(now,idx-1);
        }
        
        
        return players;
    }
}