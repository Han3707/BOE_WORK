import java.util.*;

class Solution {
    private int n,m;
    private int[][] map;
    private boolean[][] visited;
    
    public int solution(int[][] maps) {
        this.map = maps;
        this.n = maps.length;
        this.m = maps[0].length;
        this.visited = new boolean[n][m];
        
        return bfs();
    }
    
    private int bfs(){
        Deque <int[]> deque = new ArrayDeque<>();
        
        int[] dx = {1,0,-1,0};
        int[] dy = {0,1,0,-1};
        
        deque.offer(new int[]{0,0,1});
        visited[0][0] = true;
        
        while(!deque.isEmpty()){
            int[] cur = deque.poll();
            int x = cur[0];
            int y = cur[1];
            int dist = cur[2];
            
            if(x == n -1 && y == m - 1){
                return dist;
            }
            
            for(int i=0; i<4; i++){
                int nx = x + dx[i];
                int ny = y + dy[i];
                
                if(nx >=0 && nx < n && ny >=0 && ny < m && !visited[nx][ny] && map[nx][ny] == 1){
                    visited[nx][ny] = true;
                    deque.offer(new int[]{nx,ny,dist + 1});
                }
            }
        }
        return -1;
    }
}