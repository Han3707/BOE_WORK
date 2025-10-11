class Solution {
    public int solution(int a, int b, int n) {
        int answer = 0;
        
        int nul = 0;
        
        while(n >= a){
            nul = (n / a ) * b;
            n = (n % a) + nul;
            answer += nul;
        }
        
        
        return answer;
    }
}