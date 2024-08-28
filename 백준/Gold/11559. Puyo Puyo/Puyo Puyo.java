import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static char[][] puyo;
    static boolean[][] visited;
    static int[] dx = {1, -1, 0, 0}; // 상하좌우 이동을 위한 배열
    static int[] dy = {0, 0, 1, -1}; // 상하좌우 이동을 위한 배열
    static final int ROWS = 12;
    static final int COLS = 6;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        puyo = new char[ROWS][COLS];

        // 입력 받기
        for (int i = 0; i < ROWS; i++) {
            String a = br.readLine();
            for (int j = 0; j < COLS; j++) {
                puyo[i][j] = a.charAt(j);
            }
        }

        int chainCount = 0;

        while (true) {
            visited = new boolean[ROWS][COLS];
            boolean isPopped = false;

            for (int i = 0; i < ROWS; i++) {
                for (int j = 0; j < COLS; j++) {
                    if (puyo[i][j] != '.' && !visited[i][j]) {
                        List<int[]> connected = new ArrayList<>();
                        dfs(i, j, puyo[i][j], connected);

                        if (connected.size() >= 4) {
                            isPopped = true;
                            for (int[] pos : connected) {
                                puyo[pos[0]][pos[1]] = '.';
                            }
                        }
                    }
                }
            }

            if (!isPopped) break;

            dropPuyo();
            chainCount++;
        }

        System.out.println(chainCount);
    }

    static void dfs(int x, int y, char color, List<int[]> connected) {
        visited[x][y] = true;
        connected.add(new int[]{x, y});

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx >= 0 && nx < ROWS && ny >= 0 && ny < COLS && !visited[nx][ny] && puyo[nx][ny] == color) {
                dfs(nx, ny, color, connected);
            }
        }
    }

    static void dropPuyo() {
        for (int j = 0; j < COLS; j++) {
            int emptyRow = ROWS - 1;

            for (int i = ROWS - 1; i >= 0; i--) {
                if (puyo[i][j] != '.') {
                    char temp = puyo[i][j];
                    puyo[i][j] = '.';
                    puyo[emptyRow][j] = temp;
                    emptyRow--;
                }
            }
        }
    }
}
