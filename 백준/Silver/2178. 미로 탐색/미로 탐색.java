import java.util.*;
import java.io.*;

public class Main {
    static int N, M, min_load;
    static int[][] map;
    static boolean[][] visited;
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        visited = new boolean[N][M];
        min_load = Integer.MAX_VALUE;  // 최단 경로를 저장할 변수, 초기값은 아주 큰 값으로 설정

        // 지도 입력
        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = str.charAt(j) - '0';
            }
        }

        // BFS 탐색 시작
        bfs(0, 0);

        // 최단 경로 출력
        System.out.println(min_load);
    }

    static void bfs(int x, int y) {
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{x, y, 1});  // 시작점에서의 경로 길이는 1
        visited[x][y] = true;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int cx = cur[0], cy = cur[1], curCnt = cur[2];

            // 목적지에 도달하면 최단 경로를 갱신
            if (cx == N - 1 && cy == M - 1) {
                min_load = Math.min(min_load, curCnt);
            }

            // 4방향으로 이동
            for (int k = 0; k < 4; k++) {
                int nx = cx + dx[k];
                int ny = cy + dy[k];

                if (nx >= 0 && ny >= 0 && nx < N && ny < M && !visited[nx][ny] && map[nx][ny] != 0) {
                    visited[nx][ny] = true;
                    q.offer(new int[]{nx, ny, curCnt + 1});
                }
            }
        }
    }
}
