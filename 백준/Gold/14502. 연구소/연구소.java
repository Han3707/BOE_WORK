import java.io.*;
import java.util.*;

public class Main {
    static int n, m;
    static int[][] map;
    static int[][] tempMap;
    static boolean[][] visited;
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int maxSafeArea = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][m];
        tempMap = new int[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(0);
        System.out.println(maxSafeArea);
    }

    // 벽을 세우는 모든 조합을 시도하는 DFS 메서드
    static void dfs(int count) {
        if (count == 3) {
            spreadVirus();
            return;
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] == 0) {
                    map[i][j] = 1;
                    dfs(count + 1);
                    map[i][j] = 0;
                }
            }
        }
    }

    // 바이러스를 퍼뜨리는 BFS 메서드
    static void spreadVirus() {
        Queue<int[]> queue = new LinkedList<>();
        visited = new boolean[n][m];

        // tempMap 초기화 및 바이러스 위치 큐에 추가
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                tempMap[i][j] = map[i][j];
                if (tempMap[i][j] == 2) {
                    queue.add(new int[]{i, j});
                    visited[i][j] = true;
                }
            }
        }

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int x = current[0];
            int y = current[1];

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx >= 0 && ny >= 0 && nx < n && ny < m) {
                    if (tempMap[nx][ny] == 0 && !visited[nx][ny]) {
                        tempMap[nx][ny] = 2;
                        visited[nx][ny] = true;
                        queue.add(new int[]{nx, ny});
                    }
                }
            }
        }

        calculateSafeArea();
    }

    // 안전 영역을 계산하는 메서드
    static void calculateSafeArea() {
        int safeArea = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (tempMap[i][j] == 0) {
                    safeArea++;
                }
            }
        }
        maxSafeArea = Math.max(maxSafeArea, safeArea);
    }
}
