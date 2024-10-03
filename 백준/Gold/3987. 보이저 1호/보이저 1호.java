import java.io.*;
import java.util.*;

public class Main {
  static int N, M, a, b;
  static char[][] map;
  static int[] dx = {-1, 0, 1, 0}; // 위, 오, 밑, 왼
  static int[] dy = {0, 1, 0, -1};

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());

    map = new char[N][M];

    for (int i = 0; i < N; i++) {
      String line = br.readLine();
      for (int j = 0; j < M; j++) {
        map[i][j] = line.charAt(j);
      }
    }

    st = new StringTokenizer(br.readLine());
    a = Integer.parseInt(st.nextToken()) - 1; // 1-based -> 0-based 변환
    b = Integer.parseInt(st.nextToken()) - 1; // 1-based -> 0-based 변환

    int resTime = 0;
    char resDir = 'U';

    for (int k = 0; k < 4; k++) {
      int time = check(a, b, k);
      if (resTime < time) {
        switch (k) {
          case 0: resDir = 'U'; break;
          case 1: resDir = 'R'; break;
          case 2: resDir = 'D'; break;
          case 3: resDir = 'L'; break;
        }
        resTime = time;
      }
    }

    System.out.println(resDir);

    if (resTime == Integer.MAX_VALUE) {
      System.out.println("Voyager");
    } else {
      System.out.println(resTime);
    }
  }

  static int check(int r, int c, int dir) {
    int t = 1;
    boolean[][][] visited = new boolean[N][M][4]; // 방향 포함 방문 체크

    while (true) {
      int nx = r + dx[dir];
      int ny = c + dy[dir];

      // 항성계를 벗어나거나 블랙홀(C)에 도착
      if (nx < 0 || ny < 0 || nx >= N || ny >= M || map[nx][ny] == 'C') {
        return t;
      }

      // 같은 방향과 위치에서 반복된다면 무한 루프
      if (visited[nx][ny][dir]) {
        return Integer.MAX_VALUE;
      }

      visited[nx][ny][dir] = true;

      if (map[nx][ny] == '/') {
        switch (dir) {
          case 0: dir = 1; break;
          case 1: dir = 0; break;
          case 2: dir = 3; break;
          case 3: dir = 2; break;
        }
      } else if (map[nx][ny] == '\\') {
        switch (dir) {
          case 0: dir = 3; break;
          case 1: dir = 2; break;
          case 2: dir = 1; break;
          case 3: dir = 0; break;
        }
      }

      r = nx;
      c = ny;
      t++;
    }
  }
}
