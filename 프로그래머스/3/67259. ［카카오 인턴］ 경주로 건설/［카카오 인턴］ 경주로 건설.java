import java.util.*;

class Solution {
    
    private static final int STCOST = 100;
    private static final int COCOST = 500;
    private static final int[] dx = {-1,0,1,0};
    private static final int[] dy = {0,1,0,-1};
    
    static class State{
        int x,y,dir,cost;
        
        State(int x, int y,int dir, int cost){
            this.x = x;
            this.y = y;
            this.dir = dir;
            this.cost = cost;
        }
    }
    
    public int solution(int[][] board) {
        int n = board.length;
        
        int[][][] visited = new int[n][n][4];
        
        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                Arrays.fill(visited[i][j],Integer.MAX_VALUE);
            }
        }
        
        Deque<State> dq = new ArrayDeque<>();
        
        if(board[0][1] == 0){
            dq.add(new State(0,1,1,STCOST));
            visited[0][1][1] = STCOST;
        }
        if(board[1][0] == 0){
            dq.add(new State(1,0,2,STCOST));
            visited[1][0][2] = STCOST;
        }
        
        while(!dq.isEmpty()){
            State cur = dq.poll();
            int x = cur.x;
            int y = cur.y;
            int dir = cur.dir;
            int cost = cur.cost;
            
            // 지금 현재 cost 보다 더 낮은 값이 이미 있다면 패스
            if(cost > visited[x][y][dir]){
                continue;
            }
            
            for(int i=0; i<4; i++){
                int nx = x + dx[i];
                int ny = y + dy[i];
                
                if(nx < 0 || nx >= n || ny < 0 || ny >= n || board[nx][ny] == 1 ){
                    continue;
                }
                
                int newcost;
                if(dir == i){
                    newcost = cost + STCOST;
                }else{
                    newcost = cost + STCOST + COCOST;
                }
                
                if(newcost < visited[nx][ny][i]){
                    visited[nx][ny][i] = newcost;
                    dq.add(new State(nx,ny,i,newcost));
                }
            }
        }
        int res = Integer.MAX_VALUE;
            for(int i=0; i<4; i++){
                res = Math.min(res,visited[n-1][n-1][i]);
            }
        return res;
    }
}