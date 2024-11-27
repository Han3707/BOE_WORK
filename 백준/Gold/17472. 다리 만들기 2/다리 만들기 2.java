import java.util.*;

public class Main {

    static int N, M;
    static int[][] map;
    static List<Edge> edges = new ArrayList<>();
    static int[] parents;
    static int islandCount;

    // 방향 배열 (우, 하, 좌, 상)
    static final int[] dirX = {0, 1, 0, -1};
    static final int[] dirY = {1, 0, -1, 0};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 입력
        N = sc.nextInt();
        M = sc.nextInt();
        map = new int[N][M];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                map[i][j] = sc.nextInt();
            }
        }

        // 섬 번호 매기기
        islandCount = 2; // 섬 번호는 2부터 시작
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 1) {
                    labelIslands(i, j, islandCount++);
                }
            }
        }

        // 다리(간선) 생성
        createEdges();

        // 유니온 파인드 초기화
        makeSet();

        // 크루스칼 알고리즘 실행
        int result = kruskal();

        // 결과 출력
        System.out.println(result);
    }

    // 섬을 번호로 라벨링 (BFS)
    static void labelIslands(int x, int y, int label) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{x, y});
        map[x][y] = label;

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int curX = current[0];
            int curY = current[1];

            for (int d = 0; d < 4; d++) {
                int nx = curX + dirX[d];
                int ny = curY + dirY[d];

                if (nx >= 0 && ny >= 0 && nx < N && ny < M && map[nx][ny] == 1) {
                    map[nx][ny] = label;
                    queue.add(new int[]{nx, ny});
                }
            }
        }
    }

    // 다리(간선) 생성
    static void createEdges() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] > 0) { // 섬인 경우
                    int currentIsland = map[i][j];
                    for (int d = 0; d < 4; d++) {
                        int nx = i, ny = j, distance = 0;

                        while (true) {
                            nx += dirX[d];
                            ny += dirY[d];

                            if (nx < 0 || ny < 0 || nx >= N || ny >= M || map[nx][ny] == currentIsland) {
                                break; // 지도 밖이거나 같은 섬일 경우 종료
                            }

                            if (map[nx][ny] == 0) {
                                distance++; // 바다라면 거리 증가
                            } else if (map[nx][ny] > 0) {
                                if (distance >= 2) { // 유효한 다리
                                    edges.add(new Edge(currentIsland, map[nx][ny], distance));
                                }
                                break;
                            }
                        }
                    }
                }
            }
        }

        // 간선 가중치 오름차순 정렬
        Collections.sort(edges);
    }

    // 유니온 파인드 - makeSet
    static void makeSet() {
        parents = new int[islandCount];
        Arrays.fill(parents, -1);
    }

    // 유니온 파인드 - findSet
    static int findSet(int a) {
        if (parents[a] < 0) return a;
        return parents[a] = findSet(parents[a]);
    }

    // 유니온 파인드 - union
    static boolean union(int a, int b) {
        int aRoot = findSet(a);
        int bRoot = findSet(b);
        if (aRoot == bRoot) return false;
        parents[aRoot] += parents[bRoot];
        parents[bRoot] = aRoot;
      
        return true;
    }

    // 크루스칼 알고리즘
    static int kruskal() {
        int totalCost = 0, connectedEdges = 0;
        int requiredEdges = islandCount - 2; // 연결해야 할 섬 -1 (섬 번호는 2부터 시작)

        for (Edge edge : edges) {
            if (union(edge.start, edge.end)) {
                totalCost += edge.weight;
                connectedEdges++;
                if (connectedEdges == requiredEdges-1) {
                    return totalCost;
                }
            }
        }
        return -1; // 모든 섬을 연결할 수 없는 경우
    }

    // 간선 클래스
    static class Edge implements Comparable<Edge> {
        int start, end, weight;

        public Edge(int start, int end, int weight) {
            this.start = start;
            this.end = end;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            return this.weight - o.weight;
        }
    }
}
