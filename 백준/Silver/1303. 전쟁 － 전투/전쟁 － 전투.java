import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n, m;
    static char[][] map;
    static boolean[][] visit;
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int cnt, cnt2;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        List<Integer> list = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new char[m][n];
        visit = new boolean[m][n];

        for (int i = 0; i < m; i++) {
            String s = br.readLine();
            for (int j = 0; j < s.length(); j++) {
                map[i][j] = s.charAt(j);
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if(!visit[i][j]) {
                    
                    bfs(i, j, map[i][j]);
                    if (cnt != 0 && map[i][j] == 'W') list.add(cnt);
                    if (cnt != 0 && map[i][j] == 'B') list2.add(cnt);
                }
            }
        }
        int ans = 0;
        int ans2 = 0;
        for (int r : list) {
            ans += r * r;
        }

        for(int r2 : list2){
            ans2 += r2*r2;
        }

        System.out.println(ans+" "+ans2);
    }

    static void bfs(int i, int j,char s) {
        Queue<int[]> q = new ArrayDeque<>();
        visit[i][j] = true;
        q.add(new int[]{i, j});
        cnt = 1;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int x = cur[0];
            int y = cur[1];

            for (int k = 0; k < 4; k++) {
                int nx = x + dx[k];
                int ny = y + dy[k];

                if (nx >= 0 && nx < m && ny >= 0 && ny < n && !visit[nx][ny] && map[nx][ny] == s) {
                    cnt++;
                    q.add(new int[]{nx, ny});
                    visit[nx][ny] = true;
                }

            }
        }
    }
}