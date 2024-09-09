import java.io.*;
import java.util.*;

public class Main {
    static int[][] map;
    static int[] start; // 상어위치 저장
    static int cnt, size, time, n;
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());

        map = new int[n][n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 9) {
                    start = new int[]{i, j};
                    map[i][j] = 0;
                }
            }
        }
        size = 2; // 상어 크기
        cnt = 0; // 먹은 물고기 수
        time = 0; // 총 시간

        while (true) {
            int[] tar = bfs(start, size);
            if (tar == null) break; // tar가 null인 경우 종료

            int x = tar[0], y = tar[1], t = tar[2];

            cnt++;
            if (cnt == size) {
                size++;
                cnt = 0;
            }

            start[0] = x;
            start[1] = y;
            time += t;
        }
        System.out.println(time);
    }

    static int[] bfs(int[] start, int shark) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> {
            if (o1[2] != o2[2]) return Integer.compare(o1[2], o2[2]); // 거리 우선
            if (o1[0] != o2[0]) return Integer.compare(o1[0], o2[0]); // 행 우선
            return Integer.compare(o1[1], o2[1]); // 열 우선
        });

        boolean[][] visited = new boolean[n][n];
        pq.offer(new int[]{start[0], start[1], 0});
        visited[start[0]][start[1]] = true;

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int x = cur[0], y = cur[1], dist = cur[2];

            if (map[x][y] != 0 && map[x][y] < shark) {
                map[x][y] = 0;
                return new int[]{x, y, dist};
            }

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (ny >= 0 && nx >= 0 && ny < n && nx < n && !visited[nx][ny] && map[nx][ny] <= shark) {
                    pq.offer(new int[]{nx, ny, dist + 1});
                    visited[nx][ny] = true;
                }
            }
        }
        return null; // 물고기가 없을 경우
    }
}
