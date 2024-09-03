import java.io.*;
import java.util.*;

public class Main {
    static int N, M, d, cnt;
    static int[] dx = {-1, 0, 1, 0}; // 북, 동, 남, 서
    static int[] dy = {0, 1, 0, -1};
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        cnt = 0;
        bfs(x, y);
        System.out.println(cnt);
    }

    static void bfs(int x, int y) {
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{x, y});

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int cx = cur[0], cy = cur[1];

            // 현재 칸이 청소되지 않은 경우 청소한다.
            if (map[cx][cy] == 0) {
                cnt++;
                map[cx][cy] = 2; // 청소 완료를 표시
            }

            boolean cleaned = false;

            // 현재 방향에서 반시계 방향으로 90도씩 회전하며 청소할 곳 찾기
            for (int i = 0; i < 4; i++) {
                if (d == 0) { // 북쪽을 보고 있을 때
                    d = 3; // 왼쪽으로 회전(서쪽)
                    int nx = cx + dx[d];
                    int ny = cy + dy[d];
                    if (nx >= 0 && ny >= 0 && nx < N && ny < M && map[nx][ny] == 0) {
                        q.offer(new int[]{nx, ny});
                        cleaned = true;
                        break;
                    }
                } else if (d == 3) { // 서쪽을 보고 있을 때
                    d = 2; // 왼쪽으로 회전(남쪽)
                    int nx = cx + dx[d];
                    int ny = cy + dy[d];
                    if (nx >= 0 && ny >= 0 && nx < N && ny < M && map[nx][ny] == 0) {
                        q.offer(new int[]{nx, ny});
                        cleaned = true;
                        break;
                    }
                } else if (d == 2) { // 남쪽을 보고 있을 때
                    d = 1; // 왼쪽으로 회전(동쪽)
                    int nx = cx + dx[d];
                    int ny = cy + dy[d];
                    if (nx >= 0 && ny >= 0 && nx < N && ny < M && map[nx][ny] == 0) {
                        q.offer(new int[]{nx, ny});
                        cleaned = true;
                        break;
                    }
                } else if (d == 1) { // 동쪽을 보고 있을 때
                    d = 0; // 왼쪽으로 회전(북쪽)
                    int nx = cx + dx[d];
                    int ny = cy + dy[d];
                    if (nx >= 0 && ny >= 0 && nx < N && ny < M && map[nx][ny] == 0) {
                        q.offer(new int[]{nx, ny});
                        cleaned = true;
                        break;
                    }
                }
            }

            // 네 방향 모두 청소할 곳이 없는 경우 후진
            if (!cleaned) {
                if (d == 0) { // 북쪽
                    int nx = cx + 1; // 남쪽으로 후진
                    if (nx < N && map[nx][cy] != 1) {
                        q.offer(new int[]{nx, cy});
                    } else {
                        break; // 후진할 수 없으면 작동 종료
                    }
                } else if (d == 1) { // 동쪽
                    int ny = cy - 1; // 서쪽으로 후진
                    if (ny >= 0 && map[cx][ny] != 1) {
                        q.offer(new int[]{cx, ny});
                    } else {
                        break; // 후진할 수 없으면 작동 종료
                    }
                } else if (d == 2) { // 남쪽
                    int nx = cx - 1; // 북쪽으로 후진
                    if (nx >= 0 && map[nx][cy] != 1) {
                        q.offer(new int[]{nx, cy});
                    } else {
                        break; // 후진할 수 없으면 작동 종료
                    }
                } else if (d == 3) { // 서쪽
                    int ny = cy + 1; // 동쪽으로 후진
                    if (ny < M && map[cx][ny] != 1) {
                        q.offer(new int[]{cx, ny});
                    } else {
                        break; // 후진할 수 없으면 작동 종료
                    }
                }
            }
        }
    }
}
