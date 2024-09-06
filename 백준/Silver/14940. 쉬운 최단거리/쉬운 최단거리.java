import java.util.*;
import java.io.*;

public class Main {
    static int N, M;
    static int[][] map, distance;
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};
    static int[] start;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        distance = new int[N][M]; // 각 좌표의 최단 거리를 저장할 배열
        for (int i = 0; i < N; i++) {
            Arrays.fill(distance[i], -1); // distance 배열을 -1로 초기화
        }

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 2) {
                    start = new int[]{i, j}; // 시작점 좌표 저장
                    distance[i][j] = 0; // 시작점의 거리는 0으로 설정
                }
                if(map[i][j] == 0){
                    distance[i][j] = 0; // 0인 곳은 벽이라 어차피 0 임    
                }
            }
        }

        bfs(start[0], start[1]); // BFS 실행

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                sb.append(distance[i][j]).append(' '); // 각 좌표의 최단 거리 출력
            }
            sb.append('\n');
        }

        System.out.print(sb.toString());
    }

    static void bfs(int x, int y) {
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{x, y});

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int cx = cur[0], cy = cur[1];

            for (int k = 0; k < 4; k++) {
                int nx = cx + dx[k];
                int ny = cy + dy[k];

                // 새로운 좌표가 범위 안에 있고, 아직 방문하지 않은 1인 좌표인 경우
                if (nx >= 0 && ny >= 0 && nx < N && ny < M && map[nx][ny] == 1 && distance[nx][ny] == -1) {
                    distance[nx][ny] = distance[cx][cy] + 1; // 새로운 좌표까지의 거리는 이전 좌표 + 1
                    q.offer(new int[]{nx, ny});
                }
            }
        }
    }
}
