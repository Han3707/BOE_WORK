import java.util.*;

public class Main {

    static int N, M;
    static int[][] map;
    static List<Edge> edges = new ArrayList<>();
    static int[] parents;
    static int islandCount;

    static final int[] dirX = {0, 1, 0, -1};
    static final int[] dirY = {1, 0, -1, 0};

    static void make() {
        parents = new int[islandCount + 1];
        Arrays.fill(parents, -1);
    }

    static int findSet(int a) {
        if (parents[a] < 0) return a;
        return parents[a] = findSet(parents[a]);
    }

    static boolean union(int a, int b) {
        int aRoot = findSet(a);
        int bRoot = findSet(b);
        if (aRoot == bRoot) return false;

        if (parents[aRoot] < parents[bRoot]) {
            parents[aRoot] += parents[bRoot];
            parents[bRoot] = aRoot;
        } else {
            parents[bRoot] += parents[aRoot];
            parents[aRoot] = bRoot;
        }
        return true;
    }

    static void bfs(int startX, int startY, int num) {
        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[N][M];
        queue.add(new int[]{startX, startY});
        visited[startX][startY] = true;
        map[startX][startY] = num;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int x = cur[0], y = cur[1];

            for (int d = 0; d < 4; d++) {
                int nx = x + dirX[d];
                int ny = y + dirY[d];
                if (nx >= 0 && ny >= 0 && nx < N && ny < M && !visited[nx][ny] && map[nx][ny] == 1) {
                    visited[nx][ny] = true;
                    map[nx][ny] = num;
                    queue.add(new int[]{nx, ny});
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        map = new int[N][M];
        islandCount = 2;  // 섬 번호 2부터 시작

        // 지도 입력 받기
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                map[i][j] = sc.nextInt();
            }
        }

        // 섬 찾기 및 번호 매기기
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 1) {
                    bfs(i, j, islandCount);
                    islandCount++;
                }
            }
        }

        // 섬 간 다리(간선) 정보 처리
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                int num = map[i][j];
                if (num > 0) {
                    for (int d = 0; d < 4; d++) {
                        int nx = i;
                        int ny = j;
                        int dist = 0; // 다리의 거리를 초기화

                        // 특정 방향으로 계속 진행하며 빈 공간 탐색
                        while (true) {
                            nx += dirX[d];
                            ny += dirY[d];

                            if (nx < 0 || ny < 0 || nx >= N || ny >= M) break; // 지도 밖으로 나간 경우
                            if (map[nx][ny] == num) break; // 같은 섬인 경우 종료
                            if (map[nx][ny] == 0) {
                                dist++; // 빈 공간인 경우, 거리 증가
                            } else if (map[nx][ny] > 0) {
                                if (dist >= 2) { // 유효한 다리 길이
                                    edges.add(new Edge(num, map[nx][ny], dist));
                                }
                                break;
                            }
                        }
                    }
                }
            }
        }

        // 간선 정렬
        edges.sort(Comparator.comparingInt(o -> o.weight));
        make();

        // 크루스칼 알고리즘
        int cost = 0;
        int cnt = 0;
        int numIslands = islandCount - 2; // 총 섬 개수 (2부터 시작하므로 -2)
        for (Edge edge : edges) {
            if (union(edge.start, edge.end)) {
                cost += edge.weight;
                cnt++;
            }
        }

        // 모든 섬이 연결되었는지 확인
        if (cnt == numIslands - 1) {
            System.out.println(cost);
        } else {
            System.out.println(-1);
        }
    }

    static class Edge {
        int start, end, weight;

        public Edge(int start, int end, int weight) {
            this.start = start;
            this.end = end;
            this.weight = weight;
        }
    }
}
