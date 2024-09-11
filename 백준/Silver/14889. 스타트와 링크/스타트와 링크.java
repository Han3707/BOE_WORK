import java.io.*;
import java.util.*;

public class Main {
    static int N,Minlink;
    static int[][] map;
    static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());

        map = new int[N][N];
        visited = new boolean[N];

        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        Minlink = Integer.MAX_VALUE;
        link(0,0);
        System.out.println(Minlink);
    }

    static void link(int depth,int cnt){

        if(cnt == N/2){
            cal();
            return;
        }
        if(depth >= N) return;


        visited[depth] = true; // depth 번째 사람을 팀으로 선택
        link(depth+1,cnt+1);

        visited[depth] = false; // depth 번째 사람을 팀으로 선택 x
        link(depth+1,cnt);
    }

    static void cal(){
        int team1 =0;
        int team2 =0;

        for(int i=0; i< N; i++){
            for(int j=0; j<N; j++){
                if(visited[i] && visited[j]){
                    team1 += map[i][j];
                }else if(!visited[i] && !visited[j]){
                    team2 += map[i][j];
                }
            }
        }
        int diff = Math.abs(team1-team2); // 차이
        Minlink = Math.min(Minlink,diff);
    }
}