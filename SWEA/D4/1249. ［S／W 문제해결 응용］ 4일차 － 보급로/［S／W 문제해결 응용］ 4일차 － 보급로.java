import java.io.*;
import java.util.*;

public class Solution {
    static int[][] map;
    static boolean[][] visit;
    static int n;
    static int[] dx = {0,1,0,-1};
    static int[] dy = {1,0,-1,0};

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int t = Integer.parseInt(br.readLine());

        for(int tc=1; tc<t+1; tc++){

            n = Integer.parseInt(br.readLine());

            map = new int[n+2][n+2];
            visit = new boolean[n+2][n+2];

            // 테두리 방문처리
            for(int i=0; i<n+2; i++){
                visit[0][i] = true;
                visit[n+1][i] = true;
                visit[i][0] = true;
                visit[i][n+1] = true;
            }

            for(int i=1; i<n+1; i++){
                String line = br.readLine();
                for(int j=1; j<n+1; j++){
                    map[i][j] = line.charAt(j-1) -'0';
                }
            }

            int res = bfs(1,1);

            sb.append("#").append(tc).append(" ").append(res).append('\n');

        }

        System.out.println(sb.toString());
    }

    static int bfs(int x,int y){
        // pq로 더 작게 복구한 경우를 택함
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b)->{
           return Integer.compare(a[2],b[2]);
        });
        visit[x][y] = true;
        pq.offer(new int[]{x,y,0}); // x,y 좌표 , 0 dist 복구값

        while (!pq.isEmpty()){

            int[] cur = pq.poll();
            int cx = cur[0],cy = cur[1], dist = cur[2];
            
            if(cx == n && cy == n) return dist;
            
            for(int k=0; k<4; k++){
                int nx = cx + dx[k];
                int ny = cy + dy[k];
                
                if(!visit[nx][ny]){
                    visit[nx][ny] = true;
                    if(map[nx][ny] != 0){
                        pq.offer(new int[]{nx,ny,dist+map[nx][ny]});
                    }
                    else pq.offer(new int[]{nx,ny,dist});
                    
                }
            }
            
           
        }
        return -1;
    }

}