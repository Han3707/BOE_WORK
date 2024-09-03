import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Solution {
    static int N, K;
    static int[][] map;
    static boolean[][] visited;
    static int maxTrailLength;
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {-1, 1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            map = new int[N + 2][N + 2];
            visited = new boolean[N + 2][N + 2];
            int max = 0;

            // 지도 입력 및 최고 높이 찾기
            for (int i = 1; i <= N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 1; j <= N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                    max = Math.max(max, map[i][j]);
                }
            }

            maxTrailLength = 0;

            // 모든 지점에서 DFS 탐색
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    if (map[i][j] == max) {
                        visited[i][j] = true;
                        dfs(i, j, 1, true); // boolean cut 사용
                        visited[i][j] = false;
                    }
                }
            }

            sb.append("#").append(tc).append(" ").append(maxTrailLength).append("\n");
        }

        System.out.println(sb.toString());
    }

    static void dfs(int x, int y, int length, boolean canCut) {
        maxTrailLength = Math.max(maxTrailLength, length);

        for (int k = 0; k < 4; k++) {
            int nx = x + dx[k];
            int ny = y + dy[k];

            if (map[nx][ny] != 0 && !visited[nx][ny]) {
                if (map[nx][ny] < map[x][y]) {
                    visited[nx][ny] = true;
                    dfs(nx, ny, length + 1, canCut);
                    visited[nx][ny] = false;
                } else if (canCut) {
                    for (int j = 1; j <= K; j++) {
                        if (map[nx][ny] - j < map[x][y]) {
                            int originalHeight = map[nx][ny];
                            map[nx][ny] -= j;
                            visited[nx][ny] = true;
                            dfs(nx, ny, length + 1, false); // 깎기 작업을 사용했으므로 false
                            visited[nx][ny] = false;
                            map[nx][ny] = originalHeight; // 원래 높이로 복원
                        }
                    }
                }
            }
        }
    }
}
