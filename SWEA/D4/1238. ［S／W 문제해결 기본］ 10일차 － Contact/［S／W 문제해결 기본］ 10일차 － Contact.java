import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int tc = 1; tc <= 10; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken()); // 연락의 수
            int start = Integer.parseInt(st.nextToken()); // 시작 노드

            int[][] matrix = new int[101][101]; // 인접 행렬 초기화

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i += 2) {
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                matrix[from][to] = 1; // from에서 to로의 연결을 나타냄
            }

            int result = bfs(matrix, start);
            System.out.println("#" + tc + " " + result);
        }
    }

    static int bfs(int[][] matrix, int start) {
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[101];
        int[] depth = new int[101];

        queue.add(start);
        visited[start] = true;
        depth[start] = 0;

        int maxDepth = 0;
        int result = start;

        while (!queue.isEmpty()) {
            int current = queue.poll();

            for (int next = 1; next <= 100; next++) {
                if (matrix[current][next] == 1 && !visited[next]) {
                    visited[next] = true;
                    queue.add(next);
                    depth[next] = depth[current] + 1;

                    if (depth[next] > maxDepth) {
                        maxDepth = depth[next];
                        result = next;
                    } else if (depth[next] == maxDepth) {
                        result = Math.max(result, next); // 같은 깊이면 큰 번호 선택
                    }
                }
            }
        }

        return result;
    }
}
