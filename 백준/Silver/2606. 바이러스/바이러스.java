import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static boolean[] visited;
    static ArrayList<Integer>[] child;
    static int count = 0; // 감염된 컴퓨터의 수를 세기 위한 변수

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine()); // 컴퓨터의 수
        int m = Integer.parseInt(br.readLine()); // 네트워크 상에서 연결된 컴퓨터 쌍의 수

        child = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            child[i] = new ArrayList<>();
        }
        visited = new boolean[n + 1];

        for (int i = 0; i < m; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            child[a].add(b);
            child[b].add(a); // 양방향 연결 추가
        }

        dfs(1); // 1번 컴퓨터에서 시작

        System.out.println(count - 1); // 1번 컴퓨터 제외
    }

    static void dfs(int v) {
        visited[v] = true;
        count++; // 감염된 컴퓨터 수 증가

        for (int x : child[v]) {
            if (!visited[x]) {
                dfs(x);
            }
        }
    }
}
