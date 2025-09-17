class Solution {
    public int solution(String[][] board, int h, int w) {
        int answer = 0;
        
        int n = board.length;
        
        int[] dx = {0,1,0,-1};
        int[] dy = {1,0,-1,0};
        
        String color = board[h][w];
        
        for(int i=0; i<4; i++){
            int nx = h + dx[i];
            int ny = w + dy[i];
            
            if(nx >= 0 && nx < n && ny >= 0 && ny < n){
                if(board[nx][ny].equals(color)) answer++;
            }
        }
        
        return answer;
    }
}