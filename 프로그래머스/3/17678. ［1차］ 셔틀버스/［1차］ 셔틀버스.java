import java.util.*;

class Solution {
    
    private int timeToMin(String time){
        String[] p = time.split(":");
        return Integer.parseInt(p[0]) * 60 + Integer.parseInt(p[1]);
    }
    
    private String minTotime(int min){
        return String.format("%02d:%02d",min / 60, min % 60);
    }
    
    public String solution(int n, int t, int m, String[] timetable) {
        
        // 1. 크루 도착 시간을 분 단위로 변환 
        List<Integer> crews = new ArrayList<>();
        for(String time:timetable){
            crews.add(timeToMin(time));
        }
        
        Collections.sort(crews);
        
        int start = 9 * 60;
        int last = start + (n - 1) * t;
        
        int crewidx = 0;
        
        for(int i=0; i<n -1; i++){
            int shuttletime = start + i * t;
            int cnt = 0;
            
            while(crewidx < crews.size() && crews.get(crewidx) <= shuttletime && cnt < m){
                crewidx++;
                cnt++;
            }
        }
        
        
        List<Integer> lastshuttle = new ArrayList<>();
        while(crewidx < crews.size() && crews.get(crewidx) <= last){
            lastshuttle.add(crews.get(crewidx));
            crewidx++;
        }
        
        if(lastshuttle.size() < m){
            return minTotime(last);
        }else{
            return minTotime(lastshuttle.get(m-1) - 1);
        }
    }
}