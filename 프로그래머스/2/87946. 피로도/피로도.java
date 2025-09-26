class Solution {
    
    int answer = 0;
    
    public int solution(int k, int[][] dungeons) {
        
        
        
        boolean[] used = new boolean[dungeons.length];
        
        dfs(k,dungeons,used,0);
        
        return answer;
    }
    
    private void dfs(int k,int[][] d,boolean[] used,int cnt){
        answer = Math.max(answer,cnt);
        
        for(int i=0; i<d.length; i++){
            if(!used[i] && d[i][0] <= k){
                used[i] = true;
                dfs(k-d[i][1],d,used,cnt+1);
                used[i] = false;
            }
        }
    }
}