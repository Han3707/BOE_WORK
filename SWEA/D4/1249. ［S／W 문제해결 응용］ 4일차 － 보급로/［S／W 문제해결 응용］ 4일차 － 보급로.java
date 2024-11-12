import java.io.*;
import java.util.*;

public class Solution {
    static int[][] map;
    static int n;
    static boolean[][] visit;
    static int[] dx ={1,0,-1,0};
    static int[] dy ={0,-1,0,1};
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine());

        for(int tc=1; tc<t+1; tc++){
            n = Integer.parseInt(br.readLine());

            visit = new boolean[n+2][n+2];
            map = new int[n+2][n+2];

            for(int i=0; i<n+2; i++){
                visit[0][i] = true;
                visit[n+1][i] = true;
                visit[i][0] = true;
                visit[i][n+1] = true;
            }

            for(int i=1; i<n+1; i++){
                String line = br.readLine();
                for(int j=1; j<n+1; j++){
                    map[i][j] = line.charAt(j-1) - '0';
                }
            }

            int res = bfs(1,1);

            System.out.println("#"+tc+" "+res);
        }

    }

    static int bfs(int x,int y){
        PriorityQueue<int[]> q = new PriorityQueue<>((a,b)->{
            return Integer.compare(a[2],b[2]);
        });
        visit[x][y] = true;
        q.offer(new int[]{x,y,0});

        while (!q.isEmpty()){
            int[] cur = q.poll();
            int cx = cur[0],cy = cur[1],dist = cur[2];

            if(cx==n && cy==n) return dist;

            for(int i=0; i<4; i++){
                int nx = cx+dx[i];
                int ny = cy+dy[i];

                if(!visit[nx][ny]){
                    visit[nx][ny] = true;
                    if(map[nx][ny] != 0) q.offer(new int[]{nx,ny,dist+map[nx][ny]});
                    else q.offer(new int[]{nx,ny,dist});
                }

            }
        }
        return -1;
    }
}