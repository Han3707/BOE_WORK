import java.io.*;
import java.util.*;

public class Main {
    static int N, Q, gridSize;
    static int[][] map;
    static boolean[][] visited;
    static int[] dx = {-1, 1, 0, 0}; // 상, 하, 좌, 우 이동
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());
        gridSize = (int) Math.pow(2, N);
        map = new int[gridSize][gridSize];

        // 지도 초기화
        for (int i = 0; i < gridSize; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < gridSize; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        int[] L = new int[Q];
        for (int i = 0; i < Q; i++) {
            L[i] = Integer.parseInt(st.nextToken());
        }

        // 마법 단계마다 처리
        for (int i = 0; i < Q; i++) {
            fireStorm(L[i]);
        }

        // 전체 남은 얼음의 양과 가장 큰 덩어리 계산
        System.out.println(getTotalIce());
        System.out.println(getLargestIceBlock());
    }

    // 파이어스톰 마법
    static void fireStorm(int l) {
        int size = (int) Math.pow(2, l);

        // 격자 회전
        for (int i = 0; i < gridSize; i += size) {
            for (int j = 0; j < gridSize; j += size) {
                rotate(i, j, size);
            }
        }

        // 얼음이 녹는 과정
        meltIce();
    }

    // 부분 격자 회전 (90도 회전)
    static void rotate(int x, int y, int size) {
        int[][] temp = new int[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                temp[j][size - 1 - i] = map[x + i][y + j];
            }
        }

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                map[x + i][y + j] = temp[i][j];
            }
        }
    }

    // 얼음 녹는 과정
    static void meltIce() {
        List<int[]> melting = new ArrayList<>();

        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                if (map[i][j] == 0) continue;

                int count = 0;
                for (int k = 0; k < 4; k++) {
                    int nx = i + dx[k];
                    int ny = j + dy[k];

                    if (nx >= 0 && ny >= 0 && nx < gridSize && ny < gridSize && map[nx][ny] > 0) {
                        count++;
                    }
                }

                if (count < 3) {
                    melting.add(new int[]{i, j});
                }
            }
        }

        for (int[] m : melting) {
            map[m[0]][m[1]]--;
        }
    }

    // 남은 얼음의 총 양 구하기
    static int getTotalIce() {
        int total = 0;
        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                total += map[i][j];
            }
        }
        return total;
    }

    // 가장 큰 얼음 덩어리 크기 구하기
    static int getLargestIceBlock() {
        visited = new boolean[gridSize][gridSize];
        int largest = 0;

        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                if (map[i][j] > 0 && !visited[i][j]) {
                    largest = Math.max(largest, bfs(i, j));
                }
            }
        }

        return largest;
    }

    // BFS를 이용한 덩어리 크기 구하기
    static int bfs(int x, int y) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{x, y});
        visited[x][y] = true;

        int size = 1;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int cx = cur[0];
            int cy = cur[1];

            for (int i = 0; i < 4; i++) {
                int nx = cx + dx[i];
                int ny = cy + dy[i];

                if (nx >= 0 && ny >= 0 && nx < gridSize && ny < gridSize && map[nx][ny] > 0 && !visited[nx][ny]) {
                    visited[nx][ny] = true;
                    queue.add(new int[]{nx, ny});
                    size++;
                }
            }
        }

        return size;
    }
}
