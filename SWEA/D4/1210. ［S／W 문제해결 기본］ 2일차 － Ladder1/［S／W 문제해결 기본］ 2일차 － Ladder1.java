import java.io.*;
import java.util.*;

public class Solution {
    static int[][] map = new int[100][100];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        

        for (int tc = 1; tc <= 10; tc++) {
            br.readLine();
            int startX = 0, startY = 0;

            // 입력 처리 및 도착점 찾기
            for (int i = 0; i < 100; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < 100; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                    if (map[i][j] == 2) {
                        startX = i;
                        startY = j; // 도착점 위치
                    }
                }
            }

            // BFS 시작
            int result = BFS(startX, startY);
            System.out.println("#" + tc + " " + result);
        }
    }

    static int BFS(int startX, int startY) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{startX, startY});

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int x = current[0];
            int y = current[1];

            // 맨 위에 도달하면 현재 y 값 반환
            if (x == 0) return y;

            // 왼쪽으로 가능한 만큼 이동
            if (y > 0 && map[x][y - 1] == 1) {
                while (y > 0 && map[x][y - 1] == 1) {
                    y--;
                }
            }
            // 오른쪽으로 가능한 만큼 이동
            else if (y < 99 && map[x][y + 1] == 1) {
                while (y < 99 && map[x][y + 1] == 1) {
                    y++;
                }
            }

            // 위쪽으로 이동하여 다음 탐색 위치로 추가
            if (x > 0 && map[x - 1][y] == 1) {
                queue.offer(new int[]{x - 1, y});
            }
        }
        return -1; // 실패 시 -1 반환
    }
}
