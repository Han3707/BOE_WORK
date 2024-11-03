import java.io.*;
import java.util.*;

public class Main {
  static int n, m;
  static List<Node>[] graph;
  static int[] dist;
  static int[] prev;  // 이전 노드 정보를 저장할 배열

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;

    n = Integer.parseInt(br.readLine());
    m = Integer.parseInt(br.readLine());

    graph = new ArrayList[n + 1];
    dist = new int[n + 1];
    prev = new int[n + 1];  // 이전 노드를 저장할 배열 초기화
    Arrays.fill(dist, Integer.MAX_VALUE);
    Arrays.fill(prev, -1);  // 이전 노드 초기화(-1은 시작 노드 표시)

    for (int i = 1; i <= n; i++) {
      graph[i] = new ArrayList<>();
    }

    for (int i = 0; i < m; i++) {
      st = new StringTokenizer(br.readLine());
      int a = Integer.parseInt(st.nextToken());
      int b = Integer.parseInt(st.nextToken());
      int weight = Integer.parseInt(st.nextToken());
      graph[a].add(new Node(b, weight));  // 단방향 그래프이므로 a -> b만 추가
    }

    st = new StringTokenizer(br.readLine());
    int x = Integer.parseInt(st.nextToken());  // 시작 노드
    int y = Integer.parseInt(st.nextToken());  // 도착 노드

    extra(x);

    if (dist[y] == Integer.MAX_VALUE) {
      System.out.println(-1);
    } else {
      List<Integer> path = new ArrayList<>();
      for (int at = y; at != -1; at = prev[at]) {  // 역추적하여 경로 생성
        path.add(at);
      }
      Collections.reverse(path);  // 경로를 역순으로 변환하여 올바른 순서로 출력

      System.out.println( dist[y]);
      System.out.println(path.size());
      for (int node : path) {
        System.out.print(node + " ");
      }
    }
  }

  static void extra(int start) {
    PriorityQueue<Node> pq = new PriorityQueue<>();
    pq.offer(new Node(start, 0));
    dist[start] = 0;

    while (!pq.isEmpty()) {
      Node cur = pq.poll();
      int curNode = cur.node;
      int curDist = cur.cost;

      if (curDist > dist[curNode]) continue;

      for (Node neighbor : graph[curNode]) {
        int newDist = curDist + neighbor.cost;
        if (newDist < dist[neighbor.node]) {
          dist[neighbor.node] = newDist;
          prev[neighbor.node] = curNode;  // 이전 노드 업데이트
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
