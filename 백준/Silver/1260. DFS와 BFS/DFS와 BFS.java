import java.util.*;
import java.io.*;

public class Main {
    static int N,M,Start;
    static int[][] link;
    static boolean[] visited;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException{

        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st= new StringTokenizer(br.readLine());


        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        Start = Integer.parseInt(st.nextToken());

        link = new int[N+1][N+1];
        visited = new boolean[N+1];

        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            link[a][b] = link[b][a] = 1;
        }

        DFS(Start);
        sb.append("\n");
        visited = new boolean[N+1]; // dfs 로 방문처리를 했으므로 다시 초기화 시켜줌
        BFS(Start);

        System.out.println(sb);
    }

    static void DFS(int idx){

        visited[idx] = true;
        sb.append(idx).append(" ");

        for(int i=1; i<N+1; i++){
            if(link[idx][i] == 1 && !visited[i]){
                DFS(i);
            }
        }
    }

    static void BFS(int idx){
        Queue<Integer> q = new LinkedList<>();
        q.add(idx);
        visited[idx] = true;
        sb.append(idx).append(" ");

        while(!q.isEmpty()){
            int st = q.poll();

            for(int i=1; i<N+1; i++){
                if(link[st][i] == 1 && !visited[i]){
                    q.add(i);
                    visited[i] = true;
                    sb.append(i).append(" ");
                }
            }

        }

    }
}