import java.util.*;

class Solution {

    private int[][] grid;
    private int n, m;

    public int solution(String[] board) {
        n = board.length;
        m = board[0].length();

        int[] start = new int[2];
        int[] goal = new int[2];

        // 1. 보드를 숫자로 변환하고 시작/도착 위치 찾기
        parseBoard(board, start, goal);

        // 2. BFS 탐색
        return bfs(start, goal);
    }

    private void parseBoard(String[] board, int[] start, int[] goal) {
        grid = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                char c = board[i].charAt(j);
                if (c == 'D') {
                    grid[i][j] = 1; // 장애물
                } else {
                    if (c == 'R') {
                        start[0] = i;
                        start[1] = j;
                    } else if (c == 'G') {
                        goal[0] = i;
                        goal[1] = j;
                    }
                    grid[i][j] = 0; // 빈 공간 (로봇, 목표 포함)
                }
            }
        }
    }

    private int bfs(int[] start, int[] goal) {
        Deque<int[]> queue = new ArrayDeque<>();
        boolean[][] visited = new boolean[n][m];

        queue.add(new int[]{start[0], start[1], 0}); // {행, 열, 이동횟수}
        visited[start[0]][start[1]] = true;

        int[] dr = {-1, 1, 0, 0}; // 상, 하, 좌, 우
        int[] dc = {0, 0, -1, 1};

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int r = current[0];
            int c = current[1];
            int moves = current[2];

            if (r == goal[0] && c == goal[1]) {
                return moves;
            }

            for (int dir = 0; dir < 4; dir++) {
                int nr = r;
                int nc = c;

                while (true) {
                    int nextR = nr + dr[dir];
                    int nextC = nc + dc[dir];

                    if (nextR < 0 || nextR >= n || nextC < 0 || nextC >= m || grid[nextR][nextC] == 1) {
                        break;
                    }
                    nr = nextR;
                    nc = nextC;
                }

                if (!visited[nr][nc]) {
                    visited[nr][nc] = true;
                    queue.add(new int[]{nr, nc, moves + 1});
                }
            }
        }
        return -1;
    }
}