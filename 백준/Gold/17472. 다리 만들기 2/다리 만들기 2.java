import java.io.*;
import java.util.*;

public class Main {
    static int[][] map;
    static int mapCnt; // 섬의 개수
    static int[] parent; // 유니온 파운드 부모 배열
    static int n, m;
    static List<Edge> edges = new ArrayList<>(); // 간선 저장
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, -1, 0, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][m]; // 맵 입력받기
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        mapCnt = 2; // 맵 1부터 시작하면 겹치므로 2부터 시작해줌
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] == 1) {
                    labeling(i, j, mapCnt++);
                }
            }
        }

        createEdges(); //간선 만들기
        Collections.sort(edges);
        make(); // 유니온 파인드 초기화

        int res = kruskal(); // 크루스칼 알고리즘

        System.out.println(res); //결과출력
    }

    // 섬 라벨링하기
    static void labeling(int x, int y, int num) {
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{x, y});

        map[x][y] = num;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int cx = cur[0], cy = cur[1];

            for (int i = 0; i < 4; i++) {
                int nx = cx + dx[i];
                int ny = cy + dy[i];

                if (nx >= 0 && nx < n && ny >= 0 && ny < m && map[nx][ny] == 1) {
                    map[nx][ny] = num;
                    q.offer(new int[]{nx, ny});
                }
            }
        }
    }

    static void createEdges() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] > 0) {
                    int curNum = map[i][j]; // 현재 섬의 번호 저장

                    for (int k = 0; k < 4; k++) {
                        int nx = i, ny = j, dist = 0; // 거리 초기화

                        while (true) {

                            nx += dx[k];
                            ny += dy[k];

                            if (nx < 0 || nx >= n || ny < 0 || ny >= m || map[nx][ny] == curNum) break;
                            // 같은 섬이거나 범위 벗어나면 탈출

                            if (map[nx][ny] == 0) {
                                // 바다인경우 거리 증가
                                dist++;
                            } else if (map[nx][ny] > 0) {
                                if (dist >= 2) {
                                    edges.add(new Edge(curNum, map[nx][ny], dist));
                                    // 거리가 2이상인 경우 다리가 생긴것이므로 현재 섬 번호, 도착한 섬 번호 , 다리길이 저장
                                }
                                break;
                            }
                        }
                    }
                }
            }
        }
    }

    static void make() { // 부모 초기화
        parent = new int[mapCnt];
        Arrays.fill(parent, -1);
    }

    static int find(int a) {
        if (parent[a] < 0) return a;
        return parent[a] = find(parent[a]);
    }

    static boolean union(int a, int b) {
        int aRoot = find(a);
        int bRoot = find(b);
        if (aRoot == bRoot) return false;
        parent[aRoot] += parent[bRoot];
        parent[bRoot] = aRoot;
        return true;
    }

    static int kruskal() {

        int totaldist = 0; // 총거리
        int cntEdge = 0; // 연결된 간선 갯수
        int conectMap = mapCnt - 2; // 2부터 시작햇으므로 -2 ( 연결해야하는 간선의 수는 섬의 개수 -1)

        for (Edge edge : edges) {
            if (union(edge.start, edge.end)) { // 연결됐다면 간선갯수 증가
                totaldist += edge.w;
                cntEdge++;
                if(cntEdge == conectMap-1){
                    return totaldist;
                }
            }
        }
        return -1; // 모든 섬 연결 x 인경우 -1 반환
    }


    //간선클래스
    static class Edge implements Comparable<Edge> {
        int start, end, w;

        public Edge(int start, int end, int w) {
            this.start = start;
            this.end = end;
            this.w = w;
        }

        @Override
        public int compareTo(Edge o) {
            return this.w - o.w;
        }
    }
}




