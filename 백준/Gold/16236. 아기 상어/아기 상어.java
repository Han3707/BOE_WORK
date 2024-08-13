import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int[] dy = {-1, 0, 0, 1}; // 상, 좌, 우, 하
    static int[] dx = {0, -1, 1, 0};
    static int[][] map;
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        map = new int[N][N]; // 바다의 크기 주어진 배열의 크기
        int[] shark = null; // 상어의 처음 위치

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 9) {
                    shark = new int[]{i, j}; // 상어의 초기 위치를 저장
                    map[i][j] = 0; // 상어의 위치를 빈 칸으로 설정
                }
            }
        }

        int size = 2; // 아기상어의 초기 크기
        int eat = 0; // 상어가 먹은 물고기의 수
        int move = 0; // 상어가 이동한 거리

        while (true) {
            int[] target = bfs(shark, size);

            if (target==null) {
                break; // 더 이상 먹을 물고기가 없으면 종료
            }

            // 상어가 물고기 있는 위치로 이동
            shark[0] = target[0];
            shark[1] = target[1];
            move += target[2]; // 물고기 있는 위치로 이동한 거리를 총 거리에 더함
            eat++;

            if (eat == size) { // 아기상어는 자기 size 만큼 물고기를 먹으면 진화함
                size++;
                eat = 0; // 먹은 물고기 수로 진화를 했으니 먹은 물고기는 다시 초기화
            }
        }

        System.out.println(move); // 총 이동 거리 출력
    }

    // BFS 함수: 상어의 현재 위치와 크기를 받아서 먹을 물고기를 찾음
    static int[] bfs(int[] start, int size) {
        PriorityQueue<int[]> que = new PriorityQueue<>((o1, o2) -> {
            if (o1[2] != o2[2]) return Integer.compare(o1[2], o2[2]); // 거리 우선 정렬
            if (o1[0] != o2[0]) return Integer.compare(o1[0], o2[0]); // 행 우선 정렬
            return Integer.compare(o1[1], o2[1]); // 열 우선 정렬
        });

        boolean[][] visited = new boolean[N][N];
        que.add(new int[]{start[0], start[1], 0}); // 현재 상어의 위치와 거리 0
        visited[start[0]][start[1]] = true;

        while (!que.isEmpty()) {
            int[] cur = que.poll();

            // 현재 위치에서 먹을 수 있는 물고기를 발견한 경우
            if (map[cur[0]][cur[1]] != 0 && map[cur[0]][cur[1]] < size) {
                map[cur[0]][cur[1]] = 0; // 물고기를 먹었으니깐 위치의 물고기 값은 0으로 돌림
                return new int[]{cur[0],cur[1],cur[2]}; // 물고기 먹은 후의 거리 값과 좌표 반환

            }

            // 상하좌우로 이동 가능 여부 체크
            for (int k = 0; k < 4; k++) {
                int ny = cur[0] + dy[k];
                int nx = cur[1] + dx[k];

                if (ny < 0 || nx < 0 || ny >= N || nx >= N || visited[ny][nx] || map[ny][nx] > size) {
                    continue;
                }

                que.add(new int[]{ny, nx, cur[2] + 1}); // 다음 탐색 위치 큐에 추가
                visited[ny][nx] = true; // 방문 표시
            }
        }
        return null; // 먹을 물고기가 없을 경우
    }
}
