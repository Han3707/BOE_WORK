import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int t = Integer.parseInt(br.readLine()); // 테스트 케이스 수

        for (int tc = 1; tc <= t; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken()); // 초기 세포 배열의 행 크기
            int m = Integer.parseInt(st.nextToken()); // 초기 세포 배열의 열 크기
            int k = Integer.parseInt(st.nextToken()); // 시뮬레이션 시간

            int[][] board = new int[n + 2 * k][m + 2 * k]; // 확장된 보드

            List<int[]> cells = new ArrayList<>(); // 세포 리스트

            // 초기 세포 입력
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < m; j++) {
                    int lifeForce = Integer.parseInt(st.nextToken());
                    if (lifeForce != 0) {
                        board[i + k][j + k] = lifeForce;
                        cells.add(new int[]{i + k, j + k, lifeForce, 0, 0});
                    }
                }
            }

            // 4방향 탐색을 위한 델타 배열
            int[] dx = {1, -1, 0, 0};
            int[] dy = {0, 0, 1, -1};

            // 시간 진행
            for (int time = 1; time <= k; time++) {
                PriorityQueue<int[]> activeCells = new PriorityQueue<>((a, b) -> b[2] - a[2]);

                // 활성화된 세포 큐에 추가
                for (int[] cell : cells) {
                    if (cell[4] == 1) {
                        activeCells.offer(cell);
                    }
                }

                // 세포 상태 업데이트
                for (int i = 0; i < cells.size(); i++) {
                    cells.get(i)[3]++; // 경과 시간 증가
                    if (cells.get(i)[3] == cells.get(i)[2]) { // 활성화 시점 도달
                        cells.get(i)[4] = 1; // 활성화 상태로 변경
                    } else if (cells.get(i)[3] == cells.get(i)[2] * 2) { // 소멸 시점 도달
                        cells.remove(i);
                        i--;
                    }
                }

                // 활성화된 세포 번식 처리
                while (!activeCells.isEmpty()) {
                    int[] cell = activeCells.poll();
                    for (int d = 0; d < 4; d++) {
                        int newRow = cell[0] + dx[d];
                        int newCol = cell[1] + dy[d];
                        if (board[newRow][newCol] != 0) continue;
                        board[newRow][newCol] = cell[2];
                        cells.add(new int[]{newRow, newCol, cell[2], 0, 0}); // 새로운 세포 추가
                    }
                }
            }

            // 살아남은 세포 수 카운트
            int activeCellCount = 0;
            for (int[] cell : cells) {
                if (cell[3] < cell[2] * 2) {
                    activeCellCount++;
                }
            }

            sb.append("#").append(tc).append(" ").append(activeCellCount).append("\n");
        }

        System.out.println(sb.toString());
    }
}
