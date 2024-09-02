import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N,M,K;
    static int[][] map;
    static boolean[][] visited;
    static List<Integer> res = new ArrayList<>();
    static int[] dx = {0,1,0,-1};
    static int[] dy = {1,0,-1,0};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new int[M][N];

        // map 0으로 초기화
        for(int i=0; i<M; i++){
            for(int j=0; j<N; j++){
                map[i][j] = 0;
            }
        }

        int[][] arr = new int[K][4]; // 좌표저장 배열
        for(int i=0; i<K; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<4; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i=0; i<K; i++){
            int x1 = arr[i][0];
            int y1 = arr[i][1];
            int x2 = arr[i][2];
            int y2 = arr[i][3];

            for(int x=x1; x<x2; x++){
                for(int y=y1; y<y2; y++){
                    map[x][y] = 1;
                }
            }
        }
        visited = new boolean[M][N];
        for(int i=0; i<M; i++){
            for(int j=0; j<N; j++){
                if(!visited[i][j] && map[i][j] == 0){
                    bfs(i,j);
                }
            }
        }

        System.out.println(res.size());
        Collections.sort(res);
        for(int i=0; i<res.size(); i++){
            System.out.print(res.get(i)+" ");
        }
    }

    static void bfs(int x,int y){
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{x,y});
        visited[x][y] =true;
        int cnt = 1;
        while(!q.isEmpty()){
            int[] cur = q.poll();
            int cx = cur[0];
            int cy = cur[1];

            for(int i=0; i<4; i++){
                int nx = cx + dx[i];
                int ny = cy + dy[i];
                if(nx >= 0 && ny >= 0 && nx < M && ny < N && !visited[nx][ny] && map[nx][ny] == 0){
                    cnt++;
                    visited[nx][ny] = true;
                    q.offer(new int[]{nx,ny});
                }
            }
        }

        res.add(cnt);


    }
}