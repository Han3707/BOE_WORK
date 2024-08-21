import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int[][] grid;

    static class Pipe {
        int x, y, status;

        Pipe(int x, int y, int status) {
            this.x = x;
            this.y = y;
            this.status = status;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        grid = new int[N][N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                grid[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.println(bfs());
    }

    static int bfs() {
        Queue<Pipe> queue = new LinkedList<>();
        queue.add(new Pipe(0, 1, 0));
        int count = 0;

        while (!queue.isEmpty()) {
            Pipe current = queue.poll();
            int x = current.x;
            int y = current.y;
            int status = current.status;

            if (x == N - 1 && y == N - 1) {
                count++;
                continue;
            }

            // 가로 상태
            if (status == 0) {
                if (y + 1 < N && grid[x][y + 1] == 0) {
                    queue.add(new Pipe(x, y + 1, 0));
                }
                if (x + 1 < N && y + 1 < N && grid[x][y + 1] == 0 && grid[x + 1][y] == 0 && grid[x + 1][y + 1] == 0) {
                    queue.add(new Pipe(x + 1, y + 1, 2));
                }
            }

            // 세로 상태
            else if (status == 1) {
                if (x + 1 < N && grid[x + 1][y] == 0) {
                    queue.add(new Pipe(x + 1, y, 1));
                }
                if (x + 1 < N && y + 1 < N && grid[x + 1][y] == 0 && grid[x][y + 1] == 0 && grid[x + 1][y + 1] == 0) {
                    queue.add(new Pipe(x + 1, y + 1, 2));
                }
            }

            // 대각선 상태
            else if (status == 2) {
                if (y + 1 < N && grid[x][y + 1] == 0) {
                    queue.add(new Pipe(x, y + 1, 0));
                }
                if (x + 1 < N && grid[x + 1][y] == 0) {
                    queue.add(new Pipe(x + 1, y, 1));
                }
                if (x + 1 < N && y + 1 < N && grid[x + 1][y] == 0 && grid[x][y + 1] == 0 && grid[x + 1][y + 1] == 0) {
                    queue.add(new Pipe(x + 1, y + 1, 2));
                }
            }
        }

        return count;
    }
}

