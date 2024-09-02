import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Solution {
    static boolean[] visited;
    static ArrayList<Integer>[] child;
    static int count = 0; // 감염된 컴퓨터의 수를 세기 위한 변수

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine());
        for(int tc=1; tc<t+1; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            child = new ArrayList[n + 1];
            for (int i = 1; i <= n; i++) {
                child[i] = new ArrayList<>();
            }
            visited = new boolean[n + 1];

            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                child[a].add(b);
                child[b].add(a); // 양방향 연결 추가
            }
            int cnt= 0;
            for(int k=1; k<n+1; k++){
                if(!visited[k]){
                    dfs(k);
                    cnt++;
                }
            }
            System.out.println("#"+tc+" "+cnt); // 1번 컴퓨터 제외
        }
    }

    static void dfs(int v) {
        visited[v] = true;

        for (int x : child[v]) {
            if (!visited[x]) {
                dfs(x);
            }
        }
    }
}
