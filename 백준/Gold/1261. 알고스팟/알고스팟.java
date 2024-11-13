import java.io.*;
import java.util.*;

public class Main {
    static int[][] map;
    static boolean[][] visit;
    static int n,m;
    static int[] dx = {1,0,-1,0};
    static int[] dy = {0,-1,0,1};
    public static void main(String[] args) throws IOException{
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        m =Integer.parseInt(st.nextToken());
        n =Integer.parseInt(st.nextToken());

        map = new int[n+2][m+2];
        visit = new boolean[n+2][m+2];


        for(int i=0; i<n+2; i++){
            visit[i][0] = true;
            visit[i][m+1] = true;
        }

        for(int i=0; i<m+2; i++){
            visit[0][i] = true;
            visit[n+1][i] = true;
        }

        for(int i=1; i<n+1; i++){
            String line = br.readLine();
            for(int j=1; j<m+1; j++){
                map[i][j] = line.charAt(j-1) -'0';

            }
        }

        int res = bfs(1,1);

        System.out.println(res);
    }

    static int bfs(int x, int y){
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b)->{
            return Integer.compare(a[2],b[2]);
        });

        visit[x][y] = true;
        pq.offer(new int[]{x,y,0}); // x,y ,dist

        while(!pq.isEmpty()){
            int[] cur = pq.poll();
            int cx = cur[0],cy = cur[1],dist =cur[2];

            if(cx ==n && cy ==m) return dist;

            for(int i=0; i<4; i++){
                int nx = cx + dx[i];
                int ny = cy + dy[i];

                if(!visit[nx][ny]){
                    visit[nx][ny] = true;
                    if(map[nx][ny] == 1) pq.offer(new int[]{nx,ny,dist+1});
                    else pq.offer(new int[]{nx,ny,dist});

                }
            }
        }
        return -1;
    }
}