import java.util.*;

class Solution {
    private int n,m;
    private int[][] dir;
    private boolean[][] visited;
    
    public int[] solution(String[] maps) {
        n = maps.length;
        m = maps[0].length();
        
        int[][] grid = new int[n][m];
        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                char c = maps[i].charAt(j);
                grid[i][j] = (c == 'X') ? 0 : (c -'0');
            }
        }
        
        visited = new boolean[n][m];
        List<Integer> res = new ArrayList<>();
        
        dir = new int[][]{{-1,0},{1,0},{0,-1},{0,1}};
        
        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                if(!visited[i][j] && grid[i][j] > 0){
                    int days = bfs(grid,i,j);
                    res.add(days);
                }
            }
        }
        
        if(res.isEmpty()){
            return new int[]{-1};
        }
        
        Collections.sort(res);
        int[] ans = new int[res.size()];
        for(int i=0; i<res.size(); i++){
            ans[i] = res.get(i);
        }
        
        return ans;
    }
    
    private int bfs(int[][] map,int si,int sj){
        Deque<int[]> dq = new ArrayDeque<>();
        dq.offer(new int[]{si,sj});
        visited[si][sj] = true;
        int days = map[si][sj];
        
        while(!dq.isEmpty()){
            int[] cur = dq.poll();
            int x = cur[0];
            int y = cur[1];
            
            for(int[] d:dir){
                int nx = x + d[0];
                int ny = y + d[1];
                
                if(nx >= 0 && nx < n && ny >= 0 && ny < m && !visited[nx][ny] && map[nx][ny] > 0){
                    
                    visited[nx][ny] = true;
                    days += map[nx][ny];
                    dq.add(new int[]{nx,ny});
                }
            }
        }
        
        return days;
    }
}