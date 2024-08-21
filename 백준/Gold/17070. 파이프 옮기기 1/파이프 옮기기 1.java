import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[][] home;
    static int N;
    static int count=0;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        home = new int[N+1][N+1];
        for(int r=1;r<=N;r++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int c=1;c<=N;c++) {
                home[r][c] = Integer.parseInt(st.nextToken());
            }
        }

        // 가로: 1, 세로: 2, 대각선: 3
        dfs(1,2,1);  // 가장 처음 (1,2), 방향 가로
        System.out.println(count);

    }

    public static void dfs(int r, int c, int dir) {
        if(r>N || c >N || home[r][c]==1) {
            return;
        }
         // 대각선방향으로 옮길 때 도착전에 / 대각선이 1인지 확인
        if(dir==3) {
            if(home[r][c-1]==1 || home[r-1][c]==1) {
                return;
            }
        }
        if(r==N && c==N) {
            count++;
            return;
        }

        // 파이프가 가로방향인 경우 -> 가로방향, 대각선방향
        if(dir==1) {
            dfs(r,c+1,1);
            dfs(r+1,c+1,3);
        }
        // 파이프가 세로방향인 경우 -> 세로방향, 대각선방향
        if(dir==2) {
            dfs(r+1,c,2);
            dfs(r+1,c+1,3);
        }
        // 파이프가 대각선방향인 경우 -> 가로방향, 세로방향, 대각선방향
        if(dir==3) {
            // 파이프의 / 대각선도 벽이 아니어야 함
//            if(home[r][c-1]==1 || home[r-1][c]==1) {
//                return;
//            }
            dfs(r,c+1,1);
            dfs(r+1,c,2);
            dfs(r+1,c+1,3);
        }

    }

}
