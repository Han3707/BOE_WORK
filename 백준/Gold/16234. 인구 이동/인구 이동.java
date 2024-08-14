import java.util.*;

public class Main {
    static int N, L, R;
    static int[][] grid;
    static boolean[][] visited;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        L = sc.nextInt();
        R = sc.nextInt();

        grid = new int[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                grid[i][j] = sc.nextInt();
            }
        }

        int days = 0;

        while (true) {
            visited = new boolean[N][N];
            boolean isUnionFormed = false;

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (!visited[i][j]) {
                        if (bfs(i, j)) {
                            isUnionFormed = true;
                        }
                    }
                }
            }

            if (!isUnionFormed) {
                break;
            }
            days++;
        }

        System.out.println(days);
    }

    static boolean bfs(int x, int y) {
        Queue<int[]> queue = new LinkedList<>();
        List<int[]> union = new ArrayList<>();

        queue.offer(new int[]{x, y});
        visited[x][y] = true;
        union.add(new int[]{x, y});

        int totalPopulation = grid[x][y];

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int cx = current[0];
            int cy = current[1];

            for (int i = 0; i < 4; i++) {
                int nx = cx + dx[i];
                int ny = cy + dy[i];

                if (nx >= 0 && ny >= 0 && nx < N && ny < N && !visited[nx][ny]) {
                    int diff = Math.abs(grid[nx][ny] - grid[cx][cy]);
                    if (L <= diff && diff <= R) {
                        queue.offer(new int[]{nx, ny});
                        visited[nx][ny] = true;
                        union.add(new int[]{nx, ny});
                        totalPopulation += grid[nx][ny];
                    }
                }
            }
        }

        int population = totalPopulation / union.size();
        for (int[] country : union) {
            grid[country[0]][country[1]] = population;
        }

        return union.size() > 1;
    }
}
