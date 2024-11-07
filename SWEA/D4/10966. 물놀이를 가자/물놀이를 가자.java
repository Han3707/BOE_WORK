import java.io.*;
import java.util.*;


class Pos{
    int x;
    int y;
    int cost;
    public Pos(int x,int y,int cost){
        this.x = x;
        this.y = y;
        this.cost = cost;
    }
}

public class Solution {
    static int n,m;
    static List<Pos> water;
    static boolean[][] visit;
    static char[][] map;
    static int MinDist;
    static int[] dx = {1,0,-1,0};
    static int[] dy = {0,1,0,-1};
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int t = Integer.parseInt(br.readLine());

        for(int tc=1; tc<t+1; tc++){
            StringTokenizer st= new StringTokenizer(br.readLine());

            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());

            map = new char[n][m];

            water = new ArrayList<>();

            for(int i=0; i<n; i++){
                String line = br.readLine();
                for(int j=0; j<m; j++){
                    map[i][j] = line.charAt(j);
                    if(map[i][j] == 'W') water.add(new Pos(i,j,0));
                }
            }
            BFS();
            sb.append("#").append(tc).append(" ").append(MinDist).append('\n');

        }
        System.out.println(sb);

    }

    static void BFS(){
        Queue<Pos> q = new ArrayDeque<>();
        visit = new boolean[n][m];
        for(Pos w:water){
            visit[w.x][w.y] = true;
            q.offer(w);
        }

        MinDist = 0;

        while(!q.isEmpty()){
            Pos cur = q.poll();
            int x= cur.x,y = cur.y,dist = cur.cost;


            for(int i=0; i<4; i++){
                int nx = x+dx[i];
                int ny = y+dy[i];

                if(nx >= 0 && nx < n && ny >= 0 && ny < m && !visit[nx][ny]){
                    visit[nx][ny] = true;
                    MinDist += dist +1;
                    q.offer(new Pos(nx,ny,dist+1));
                }
            }
        }

    }
}