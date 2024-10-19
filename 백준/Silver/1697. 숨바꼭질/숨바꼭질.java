import java.io.*;
import java.util.*;

public class Main {
  static int n, k;
  static boolean[] visited ;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    n = Integer.parseInt(st.nextToken());
    k = Integer.parseInt(st.nextToken());
    int time = 0;
    int res = BFS(n);
    System.out.println(res);
  }

  static int BFS(int start) {
    Queue<int[]> q = new ArrayDeque<>();
    visited = new boolean[1000001];
    q.add(new int[]{start,0});

    while(!q.isEmpty()){
      int[] cur = q.poll();

      int cur_pos = cur[0];
      int cur_time = cur[1];

      if(cur_pos == k) return cur_time;

      if(cur_pos >= 0 && cur_pos < 100001 && !visited[cur_pos]){
        visited[cur_pos] = true;
        q.add(new int[]{cur_pos*2,cur_time+1});
        q.add(new int[]{cur_pos+1,cur_time+1});
        q.add(new int[]{cur_pos-1,cur_time+1});
      }
    }
    return -1;
  }
}
