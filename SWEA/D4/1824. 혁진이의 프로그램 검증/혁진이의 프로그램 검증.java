import java.io.*;
import java.util.*;

public class Solution {
    static char[][] map;
    static int n, m;
    static int[] dx = {0, 1, 0, -1}; // 우, 하, 좌, 상
    static int[] dy = {1, 0, -1, 0};
    static char[] move = {'>', 'v', '<', '^'};
    static List<Character> list = Arrays.asList('0', '1', '2', '3', '4', '5', '6', '7', '8', '9');
    static boolean[][][][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int t = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= t; tc++) {
            st = new StringTokenizer(br.readLine());

            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());

            map = new char[n + 2][m + 2];
            visited = new boolean[n + 2][m + 2][4][16]; // x, y, 방향, 메모리

            for (int i = 0; i < n + 2; i++) {
                Arrays.fill(map[i], 't'); // 맵 테두리 설정
            }
            
            for (int i = 1; i <= n; i++) {
                String line = br.readLine();
                for (int j = 1; j <= m; j++) {
                    map[i][j] = line.charAt(j - 1);
                }
            }

            sb.append("#").append(tc).append(" ");
            if (bfs(1, 1)) sb.append("YES");
            else sb.append("NO");
            sb.append("\n");
        }
        System.out.print(sb);
    }

    static boolean bfs(int startX, int startY) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{startX, startY, 0, 0}); // {x, y, 방향, 메모리}
        visited[startX][startY][0][0] = true;

        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int x = curr[0], y = curr[1], dir = curr[2], memory = curr[3];

            if (map[x][y] == '@') return true;

            // 숫자일 경우 메모리에 저장
            if (list.contains(map[x][y])) {
                memory = map[x][y] - '0';
            }

            // 방향 변경
            if (map[x][y] == '>') dir = 0;
            else if (map[x][y] == 'v') dir = 1;
            else if (map[x][y] == '<') dir = 2;
            else if (map[x][y] == '^') dir = 3;
            else if (map[x][y] == '_') dir = (memory == 0) ? 0 : 2;
            else if (map[x][y] == '|') dir = (memory == 0) ? 1 : 3;
            
            // 메모리 증가/감소
            if (map[x][y] == '+') memory = (memory + 1) % 16;
            else if (map[x][y] == '-') memory = (memory - 1 + 16) % 16;

            // 이동
            if (map[x][y] == '?') {
                for (int i = 0; i < 4; i++) {
                    int nx = x + dx[i];
                    int ny = y + dy[i];
                    nx = (nx == 0) ? n : (nx == n + 1) ? 1 : nx;
                    ny = (ny == 0) ? m : (ny == m + 1) ? 1 : ny;
                    if (!visited[nx][ny][i][memory]) {
                        visited[nx][ny][i][memory] = true;
                        queue.offer(new int[]{nx, ny, i, memory});
                    }
                }
            } else {
                int nx = x + dx[dir];
                int ny = y + dy[dir];
                nx = (nx == 0) ? n : (nx == n + 1) ? 1 : nx;
                ny = (ny == 0) ? m : (ny == m + 1) ? 1 : ny;
                if (!visited[nx][ny][dir][memory]) {
                    visited[nx][ny][dir][memory] = true;
                    queue.offer(new int[]{nx, ny, dir, memory});
                }
            }
        }
        return false;
    }
}
