import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static List<Integer>[] small; // 자신보다 작은 학생 리스트
    static List<Integer>[] big; // 자신보다 큰 학생 리스트
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        small = new List[N + 1];
        big = new List[N + 1];

        for (int i = 1; i <= N; i++) {
            small[i] = new ArrayList<>();
            big[i] = new ArrayList<>();
        }

        // 입력 처리
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            big[a].add(b);    // a -> b (a가 b보다 작다)
            small[b].add(a);  // b -> a (b가 a보다 크다)
        }

        int answer = 0;

        // 모든 학생에 대해 자신보다 큰 학생 수와 작은 학생 수 계산
        for (int i = 1; i <= N; i++) {
            int smallCount = dfs(i, small); // 자신보다 작은 학생 수
            int bigCount = dfs(i, big); // 자신보다 큰 학생 수

            // 자신보다 작은 학생 수 + 큰 학생 수 == N-1 이면 순서가 확정
            if (smallCount + bigCount == N - 1) {
                answer++;
            }
        }

        System.out.println(answer);
    }

    // DFS를 이용해 자신보다 큰/작은 학생 수를 카운트
    static int dfs(int start, List<Integer>[] list) {
        visited = new boolean[N + 1];
        visited[start] = true;
        return dfsCount(start, list);
    }

    // DFS 탐색
    static int dfsCount(int current, List<Integer>[] list) {
        int count = 0;
        for (int next : list[current]) {
            if (!visited[next]) {
                visited[next] = true;
                count += 1 + dfsCount(next, list);
            }
        }
        return count;
    }
}
