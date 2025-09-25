class Solution {
    public int solution(int[] numbers) {
        int answer = -1;
        
        int n = 0;
        
        for(int i=0; i<10; i++){
            n += i;
        }
        
        for(int num:numbers){
            n -= num;
        }
        
        answer = n;
        
        return answer;
    }
}