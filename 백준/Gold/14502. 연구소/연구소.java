import java.io.*;
import java.util.*;

public class Main {
    static int n, m;
    static int[][] map;
    static int[][] tempMap;
    static List<int[]> emptySpaces = new ArrayList<>();
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int maxSafeArea = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][m];
        tempMap = new int[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 0) {
                    emptySpaces.add(new int[]{i, j});
                }
            }
        }

        buildWalls(0, 0);
        System.out.println(maxSafeArea);
    }

    static void buildWalls(int count, int start) {
        if (count == 3) {
            spreadVirus();
            return;
        }

        for (int i = start; i < emptySpaces.size(); i++) {
            int[] current = emptySpaces.get(i);
            map[current[0]][current[1]] = 1;
            buildWalls(count + 1, i + 1);
            map[current[0]][current[1]] = 0;
        }
    }

    static void spreadVirus() {
        Queue<int[]> queue = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            System.arraycopy(map[i], 0, tempMap[i], 0, m);
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (tempMap[i][j] == 2) {
                    queue.add(new int[]{i, j});
                }
            }
        }

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int x = current[0];
            int y = current[1];

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx >= 0 && ny >= 0 && nx < n && ny < m) {
                    if (tempMap[nx][ny] == 0) {
                        tempMap[nx][ny] = 2;
                        queue.add(new int[]{nx, ny});
                    }
                }
            }
        }

        calculateSafeArea();
    }

    static void calculateSafeArea() {
        int safeArea = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (tempMap[i][j] == 0) {
                    safeArea++;
                }
            }
        }
        maxSafeArea = Math.max(maxSafeArea, safeArea);
    }
}
