import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int t = 1; t < 11; t++) {

            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken()); // 연락 수
            int start = Integer.parseInt(st.nextToken()); // 시작 노드

            ArrayList<Integer>[] graph = new ArrayList[101]; // 노드는 1부터 100까지
            for (int i = 0; i <= 100; i++) {
                graph[i] = new ArrayList<>();
            }

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i += 2) {
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                graph[from].add(to);
            }

            int result = bfs(graph, start);
            System.out.println("#"+t+" "+result);
        }
    }

    static int bfs(ArrayList<Integer>[] graph, int start) {
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

            for (int next : graph[current]) {
                if (!visited[next]) {
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
