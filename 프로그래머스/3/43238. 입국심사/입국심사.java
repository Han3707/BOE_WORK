class Solution {
    public long solution(int n, int[] times) {
        long left = 1;
        long right = (long)n * timeMax(times);
        long answer = right;
        
        while(left <= right){
            long mid = (left + right) / 2;
            long total = 0; // 사람수 
             
            for (int time : times){
                total += mid / time; 
                if(total >= n) break;  // 사람수 충족했으면 브레이크
            }
            
            if(total >= n){
                answer = mid;
                right = mid - 1;
            }else{
                left = mid + 1;
            }
        }
        
        return answer;
    }
    
    private int timeMax(int[] times){
        int maxT = times[0];
        for(int time: times){
            if(time > maxT) maxT = time;
        }
        
        return maxT;
    }
}