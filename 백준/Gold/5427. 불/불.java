import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static char[][] map;
    static int[][] visited;
    static int w, h, cnt;
    static int dx[] = {1, 0, -1, 0};
    static int dy[] = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        for (int i = 0; i < n; i++) {
            List<int[]> fire = new ArrayList<>();

            StringTokenizer st = new StringTokenizer(br.readLine());

            w = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());

            map = new char[h][w];
            visited = new int[h][w];
            int x = 0;
            int y = 0;

            for (int k = 0; k < h; k++) {
                String line = br.readLine();
                for (int j = 0; j < w; j++) {
                    map[k][j] = line.charAt(j);
                    if (map[k][j] == '*') {
                        fire.add(new int[]{k, j});
                        visited[k][j] = 2;
                    }
                    if (map[k][j] == '#') visited[k][j] = 1;
                    if (map[k][j] == '@') {
                        x = k;
                        y = j;
                        visited[k][j] = 3;
                    }
                }
            }
//            for(int s=0; s<h; s++){
//                for(int v = 0; v <w; v++){
//                    System.out.print(visited[s][v]);
//                }
//                System.out.println();
//            }

            int res = bfs(x,y,fire);

            if (res != 0) {
                System.out.println(res);
            } else {
                System.out.println("IMPOSSIBLE");
            }
        }

    }

    static int bfs(int x, int y, List<int[]> fire) {
        Queue<int[]> q = new ArrayDeque<>();
        q.add(new int[]{x, y,0});

        int prev_time = -1;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int cx = cur[0];
            int cy = cur[1];
            int cnt = cur[2];
            if(cx == 0 || cy == 0 || cx == h-1 || cy == w-1) return cnt+1;

            if(cnt != prev_time){
                List<int[]> temp = new ArrayList<>();
                for (int[] f : fire) {
                    int fx = f[0];
                    int fy = f[1];
                    for (int m = 0; m < 4; m++) {
                        int fnx = fx + dx[m];
                        int fny = fy + dy[m];
                        if (fnx >= 0 && fnx < h && fny >= 0 && fny < w && visited[fnx][fny] != 1 && visited[fnx][fny] != 2) {
                            visited[fnx][fny] = 2;
                            temp.add(new int[]{fnx,fny});
                        }
                    }
                }
                fire = temp;
                prev_time = cnt;
            }

            for (int k = 0; k < 4; k++) {
                int nx = cx + dx[k];
                int ny = cy + dy[k];

                if (nx >= 0 && nx < h && ny >= 0 && ny < w && visited[nx][ny] == 0) {
                    visited[nx][ny] = 3;
                    q.add(new int[]{nx,ny,cnt+1});
                }
            }

        }

        return 0;
    }
}