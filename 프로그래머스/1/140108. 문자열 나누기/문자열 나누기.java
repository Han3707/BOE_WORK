class Solution {
    public int solution(String s) {
        int answer = 0;
        int count = 0;
        int other = 0;
        char c = 0;
        int n = s.length();
        
        
        for(int i=0; i<n; i++){
            
            if(count == 0 && other == 0){
                c = s.charAt(i);
            }
            
            if(c == s.charAt(i)) count++;
            else other++;
            
            if(count == other){
                answer++;
                count = 0;
                other = 0;
            }
        
        }
        
        if(count !=0 || other != 0){
            answer++;
        }
        
        
        return answer;
    }
}