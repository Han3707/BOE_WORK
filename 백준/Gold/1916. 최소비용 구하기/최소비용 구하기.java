import java.io.*;
import java.util.*;

public class Main {
  static int n, m;
  static List<Node>[] graph;
  static int[] dist;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;


    n = Integer.parseInt(br.readLine());
    m = Integer.parseInt(br.readLine());

    graph = new ArrayList[n + 1];
    dist = new int[n+1];
    Arrays.fill(dist, Integer.MAX_VALUE);

    for(int i=1; i<= n; i++){
      graph[i] = new ArrayList<>();
    }

    for(int i=0; i<m; i++){
      st =  new StringTokenizer(br.readLine());
      int a = Integer.parseInt(st.nextToken());
      int b = Integer.parseInt(st.nextToken());
      int weight = Integer.parseInt(st.nextToken());
      graph[a].add(new Node(b, weight));
    }

    st = new StringTokenizer(br.readLine());
    int x = Integer.parseInt(st.nextToken());
    int y = Integer.parseInt(st.nextToken());

    extra(x);

    System.out.println(dist[y] == Integer.MAX_VALUE ? -1 : dist[y]);
  }


  static void extra(int start){
    PriorityQueue<Node> pq = new PriorityQueue<>();
    pq.offer(new Node(start,0));
    dist[start] = 0;

    while(!pq.isEmpty()){
      Node cur = pq.poll();
      int curNode = cur.node;
      int curDist = cur.cost;
      // 현재 꺼낸 최소 거리보다 크면 더 볼 필요 없으므로 다음 순서로
      if(curDist > dist[curNode]) continue;

      // 현재보다 더 작은 거리 값이 나온다면 업데이트 후 큐에 추가
      for(Node neighbor:graph[curNode]){
        int newDist = curDist + neighbor.cost;
        if(newDist < dist[neighbor.node]){
          dist[neighbor.node] = newDist;
          pq.offer(new Node(neighbor.node,newDist));
        }
      }
    }
  }

}

// pq 에 이용한 클래스
class Node implements Comparable<Node>{
  int node;
  int cost;

  public Node(int node, int cost){
    this.node = node;
    this.cost = cost;
  }

  @Override
  public int compareTo (Node o){
    return this.cost - o.cost;
  }
}