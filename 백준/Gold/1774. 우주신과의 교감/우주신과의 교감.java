import java.io.*;
import java.util.*;

public class Main {
    static int n, m;
    static double[][] dist;
    static List<Edge> edges;
    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        int[][] coordinates = new int[n][2];
        edges = new ArrayList<>();
        parent = new int[n + 1];

        // 좌표 입력
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            coordinates[i][0] = Integer.parseInt(st.nextToken());
            coordinates[i][1] = Integer.parseInt(st.nextToken());
        }

        // 유니온 파인드를 위한 초기화
        for (int i = 1; i <= n; i++) {
            parent[i] = i;
        }

        // 기존 연결된 우주신 처리
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            union(a, b); // 미리 연결된 간선 처리
        }

        // 모든 우주신 사이의 거리 계산하여 간선 리스트에 추가
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                double distance = calculateDistance(coordinates[i], coordinates[j]);
                edges.add(new Edge(i + 1, j + 1, distance));
            }
        }

        // 간선들을 거리 기준으로 정렬
        Collections.sort(edges, Comparator.comparingDouble(e -> e.cost));

        // 크루스칼 알고리즘 적용하여 MST 구성
        double minCost = 0;
        for (Edge edge : edges) {
            if (find(edge.from) != find(edge.to)) {
                union(edge.from, edge.to);
                minCost += edge.cost;
            }
        }

        // 결과 출력
        System.out.printf("%.2f", minCost);
    }

    // 두 좌표 간의 유클리드 거리 계산
    static double calculateDistance(int[] a, int[] b) {
        return Math.sqrt(Math.pow(a[0] - b[0], 2) + Math.pow(a[1] - b[1], 2));
    }

    // 유니온-파인드: Find 연산
    static int find(int x) {
        if (parent[x] == x) return x;
        return parent[x] = find(parent[x]);
    }

    // 유니온-파인드: Union 연산
    static void union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);
        if (rootX != rootY) {
            parent[rootY] = rootX;
        }
    }

    // 간선을 나타내는 클래스
    static class Edge {
        int from, to;
        double cost;

        Edge(int from, int to, double cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }
    }
}
