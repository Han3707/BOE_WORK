import java.io.*;
import java.util.*;

public class Solution {
    static char[][] map;
    static boolean[][][][] visit;
    static int n, m;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};
    static List<Character> list = Arrays.asList('0', '1', '2', '3', '4', '5', '6', '7', '8', '9');

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
            visit = new boolean[n+2][m+2][4][16];
            // 테두리 't' 설정
            for (int i = 0; i < n + 2; i++) {
                map[i][0] = map[i][m + 1] = 't';
            }
            for (int i = 0; i < m + 2; i++) {
                map[0][i] = map[n + 1][i] = 't';
            }

            boolean hasAtSymbol = false;
            for (int i = 1; i <= n; i++) {
                String line = br.readLine();
                for (int j = 1; j <= m; j++) {
                    map[i][j] = line.charAt(j - 1);
                    if (map[i][j] == '@') hasAtSymbol = true;
                }
            }

            sb.append("#").append(tc).append(" ");
            if (hasAtSymbol && bfs(1, 1)) sb.append("YES");
            else sb.append("NO");
            sb.append("\n");
        }
        System.out.print(sb.toString());
    }

    static boolean bfs(int x, int y) {
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{x,y,0,0});
        visit[x][y][0][0] = true;

        while (!q.isEmpty()) {
            int[] cur = q.poll();

            x = cur[0];
            y = cur[1];
            int k = cur[2]; // 방향
            int memory = cur[3];


            if (map[x][y] == '@') return true;

            if (list.contains(map[x][y])) memory = map[x][y] - '0';

            if (map[x][y] == '>') k = 0;
            else if (map[x][y] == 'v') k = 1;
            else if (map[x][y] == '<') k = 2;
            else if (map[x][y] == '^') k = 3;

            if (map[x][y] == '_') k = (memory == 0) ? 0 : 2; // 메모리 0 이면 오른쪽 아니면 왼쪽
            if (map[x][y] == '|') k = (memory == 0) ? 1 : 3; // 메모리 0 이면 아래 아니면 위쪽


            if (map[x][y] == '+') memory = (memory == 15) ? 0 : memory + 1;
            else if (map[x][y] == '-') memory = (memory == 0) ? 15 : memory - 1;


            if(map[x][y] == '?'){
                for(int i=0; i<4; i++){
                    int nx = x + dx[i];
                    int ny = y + dy[i];

                    if(nx == 0) nx = n;
                    else if(nx == n+1) nx = 1;
                    if(ny == 0) ny = m;
                    else if(ny == m+1) ny = 1;

                    if(map[nx][ny] == '@') return true;

                    if(!visit[nx][ny][i][memory] && map[nx][ny] != 't'){
                        q.offer(new int[]{nx,ny,i,memory});
                        visit[nx][ny][i][memory] = true;
                    }
                }
            }else {

                int nx = x + dx[k];
                int ny = y + dy[k];

                if (nx == 0) nx = n;
                else if (nx == n+1) nx = 1;
                if (ny == 0) ny = m;
                else if (ny == m+1) ny = 1;

                if (!visit[nx][ny][k][memory] && map[nx][ny] != 't') {
                    q.offer(new int[]{nx, ny, k, memory});
                    visit[nx][ny][k][memory] = true;
                }
            }

        }
        return false;
    }
}
