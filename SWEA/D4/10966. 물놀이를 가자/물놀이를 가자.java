import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Solution {
    static int N, M;
    static char[][] map;
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, -1, 0, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();

        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            map = new char[N][M];
            List<int[]> waterPositions = new ArrayList<>();

            for (int i = 0; i < N; i++) {
                String line = br.readLine();
                for (int j = 0; j < M; j++) {
                    map[i][j] = line.charAt(j);
                    if (map[i][j] == 'W') {
                        waterPositions.add(new int[]{i, j});
                    }
                }
            }

            int result = bfs(waterPositions);
            sb.append("#").append(tc).append(" ").append(result).append("\n");
        }

        System.out.print(sb.toString());
    }

    static int bfs(List<int[]> waterPositions) {
        int totalDistance = 0;
        boolean[][] visited = new boolean[N][M];
        Queue<int[]> q = new LinkedList<>();

        // 물의 모든 위치를 큐에 추가하고 방문 처리
        for (int[] pos : waterPositions) {
            q.offer(new int[]{pos[0], pos[1], 0});
            visited[pos[0]][pos[1]] = true;
        }

        // BFS 탐색
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int cx = cur[0], cy = cur[1];
            int dist = cur[2];

            for (int i = 0; i < 4; i++) {
                int nx = cx + dx[i];
                int ny = cy + dy[i];

                if (nx >= 0 && ny >= 0 && nx < N && ny < M && !visited[nx][ny]) {
                    visited[nx][ny] = true;
                   
                    if (map[nx][ny] == 'L') {
                        totalDistance += dist+1; // 'L'에 도달하면 거리 누적
                    }
                    q.offer(new int[]{nx, ny, dist+1});
                }
            }
        }

        return totalDistance;
    }
}
