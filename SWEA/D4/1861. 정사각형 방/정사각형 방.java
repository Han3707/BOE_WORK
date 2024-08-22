import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    static int[] dx = {1, 0, -1, 0}; // 상하좌우 이동을 위한 x좌표 변화
    static int[] dy = {0, -1, 0, 1}; // 상하좌우 이동을 위한 y좌표 변화
    static int n;
    static int[][] arr;
    static boolean[][] visited;
    static int[][] dp; // 메모이제이션을 위한 배열

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine()); // 테스트 케이스의 수

        for (int tc = 1; tc <= t; tc++) {
            n = Integer.parseInt(br.readLine()); // 방의 크기
            arr = new int[n][n];
            dp = new int[n][n]; // 메모이제이션 배열 초기화

            // 배열 입력 받기
            for (int i = 0; i < n; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < n; j++) {
                    arr[i][j] = Integer.parseInt(st.nextToken());
                    dp[i][j] = -1; // 메모이제이션 배열 초기화
                }
            }

            int maxCount = 0;
            int startNumber = Integer.MAX_VALUE;

            // 모든 방에 대해 탐색 시작
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    int count = dfs(i, j);
                    if (count > maxCount || (count == maxCount && arr[i][j] < startNumber)) {
                        maxCount = count;
                        startNumber = arr[i][j];
                    }
                }
            }

            // 결과 출력
            System.out.println("#" + tc + " " + startNumber + " " + maxCount);
        }
    }

    static int dfs(int x, int y) {
        if (dp[x][y] != -1) {
            return dp[x][y];
        }

        int max = 1; // 현재 방에서 시작하여 이동 가능한 최대 방의 수
        for (int k = 0; k < 4; k++) {
            int nx = x + dx[k];
            int ny = y + dy[k];

            if (nx >= 0 && ny >= 0 && nx < n && ny < n && arr[nx][ny] == arr[x][y] + 1) {
                max = Math.max(max, 1 + dfs(nx, ny));
            }
        }

        dp[x][y] = max;
        return max;
    }
}
