import java.util.*;

class Solution {

    private int[][] map;
    private int n,m;
    
    public int solution(String[] board) {
        n = board.length;
        m = board[0].length();
        
        map = new int[n][m];
        
        int[] start = new int[2];
        int[] end = new int[2];
        
        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                if(board[i].charAt(j) == 'D'){
                    map[i][j] = 1;
                }else if(board[i].charAt(j) == 'R'){
                    map[i][j] = 0;
                    start[0] = i;
                    start[1] = j;
                }else if(board[i].charAt(j) == 'G'){
                    map[i][j] = 0;
                    end[0] = i;
                    end[1] = j;
                }else{
                    map[i][j] = 0;
                }
            }
        }
        
        
        return bfs(start,end);
    }
    
    private int bfs(int[] start,int[] end){
        Deque<int[]> dq = new ArrayDeque<>();
        boolean[][] visited = new boolean[n][m];
        
        dq.add(new int[]{start[0],start[1],0});
        
        int[] dx = {1,0,-1,0};
        int[] dy = {0,1,0,-1};
        
        while(!dq.isEmpty()){
            int[] cur = dq.poll();
            int x = cur[0];
            int y = cur[1];
            int move = cur[2];
            
            if(x == end[0] && y == end[1]){
                return move;
            }
            
            for(int i=0; i<4; i++){
                int nx = x;
                int ny = y;
                
                while(true){
                    int cx = nx + dx[i];
                    int cy = ny + dy[i];
                    
                    if(cx < 0 || cy < 0 || cx >= n || cy >= m || map[cx][cy] ==1){
                        break;
                    }
                    
                    nx = cx;
                    ny = cy;
                }
                
                if(!visited[nx][ny]){
                    visited[nx][ny] = true;
                    dq.add(new int[]{nx,ny,move+1});
                }
            }
        }
        
        return -1;
    }

}