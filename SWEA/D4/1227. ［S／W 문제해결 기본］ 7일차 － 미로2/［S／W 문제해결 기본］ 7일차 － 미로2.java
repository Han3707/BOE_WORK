import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Solution {
  static int[] dx = {1, 0, -1, 0}; // 하, 우, 상, 좌 방향
  static int[] dy = {0, 1, 0, -1};
  static boolean[][] visited;
  static final int N = 100;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();
    int tc = 10;

    for (int t = 0; t < tc; t++) {
      int caseNum = Integer.parseInt(br.readLine());
      int[][] maze = new int[N][N];
      visited = new boolean[N][N];
      int[] start = null;
      int[] end = null;

      for (int row = 0; row < N; row++) {
        String line = br.readLine();
        for (int col = 0; col < N; col++) {
          int value = line.charAt(col) - '0';
          maze[row][col] = value;
          if (value == 2) {
            start = new int[]{row,col};
          } else if (value == 3) {
            end = new int[]{row,col};
          }
        }
      }

      boolean result = bfs(maze,start,end);
      sb.append("#").append(caseNum).append(" ").append(result ? 1 : 0).append("\n");
    }

    System.out.print(sb.toString());
  }

  static boolean bfs(int[][] maze, int[] start,int[] end) {
    Queue<int[]> queue = new LinkedList<>();
    queue.add(new int[]{start[0], start[1]});
    visited[start[0]][start[1]] = true;

    while (!queue.isEmpty()) {
      int[] cur = queue.poll();
      int x = cur[0];
      int y = cur[1];

      if (x == end[0] && y == end[1]) {
        return true; // 도착점에 도달
      }

      for (int i = 0; i < 4; i++) {
        int nx = x + dx[i];
        int ny = y + dy[i];

        // 유효한 범위와 방문 여부, 벽(1) 여부 체크
        if (nx >= 0 && ny >= 0 && nx < N && ny < N && !visited[nx][ny] && maze[nx][ny] != 1) {
          visited[nx][ny] = true;
          queue.add(new int[]{nx, ny});
        }
      }
    }

    return false; // 도착점에 도달하지 못함
  }
}
