class Solution {
    
    int answer = 0;
    public int solution(int[] number) {
        
        
        dfs(number,0,0,0);
        
        return answer;
    }
    
    private void dfs(int[] num,int idx,int picked,int sum){
        if(picked == 3){
            if(sum == 0) answer++;
            return;
        }
        
        if(idx == num.length) return;
        
        
        dfs(num,idx+1,picked+1,sum+num[idx]);
        dfs(num,idx+1,picked,sum);
    }
}