class Solution {
    public int solution(String t, String p) {
        int answer = 0;
        int n = t.length();
        int m = p.length();
        
        long P = Long.parseLong(p);
        
        for(int i=0; i <= n-m; i++){
            String s = t.substring(i,i+m);
            long x = Long.parseLong(s);
            if(x <= P){
                answer++;
            }
            
        }
        return answer;
    }
}