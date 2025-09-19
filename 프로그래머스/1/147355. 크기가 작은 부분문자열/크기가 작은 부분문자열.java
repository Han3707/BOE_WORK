class Solution {
    public int solution(String t, String p) {
        int answer = 0;
        
        int n = t.length();
        int m = p.length();
        long num_p = Long.parseLong(p);
        
        for(int i=0; i<=n-m; i++){
            
            String sub = t.substring(i,i+m);
            long val = Long.parseLong(sub);
            
            if(val <= num_p) answer++;
        }
        
        return answer;
    }
}