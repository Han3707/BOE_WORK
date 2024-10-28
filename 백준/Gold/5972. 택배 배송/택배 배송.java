import java.util.*;
import java.io.*;

public class Main {
    static int n, m;
    static List<Node>[] graph;
    static int[] dist;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        graph = new ArrayList[n + 1];
        dist = new int[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);

        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int dist = Integer.parseInt(st.nextToken());
            graph[a].add(new Node(b, dist));
            graph[b].add(new Node(a, dist));
        }

        // 다익스트라 알고리즘 실행
        dijkstra(1);

        // 결과 출력
        System.out.println(dist[n] == Integer.MAX_VALUE ? -1 : dist[n]);
    }

    static void dijkstra(int start) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(start, 0));
        dist[start] = 0;

        while (!pq.isEmpty()) {
            Node current = pq.poll();
            int currentNode = current.node;
            int currentDist = current.cost;

            if (currentDist > dist[currentNode]) continue;

            for (Node neighbor : graph[currentNode]) {
                int newDist = currentDist + neighbor.cost;
                if (newDist < dist[neighbor.node]) {
                    dist[neighbor.node] = newDist;
                    pq.offer(new Node(neighbor.node, newDist));
                }
            }
        }
    }
}

class Node implements Comparable<Node> {
    int node;
    int cost;

    public Node(int node, int cost) {
        this.node = node;
        this.cost = cost;
    }

    @Override
    public int compareTo(Node o) {
        return this.cost - o.cost;
    }
}
