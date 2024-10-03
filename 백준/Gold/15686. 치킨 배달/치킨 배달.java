import java.io.*;
import java.sql.SQLOutput;
import java.util.*;


public class Main {
  static int N,M;
  static List<int[]> chicken = new ArrayList<>();
  static List<int[]> home = new ArrayList<>();
  static int[][] map;
  static int res = Integer.MAX_VALUE;
  public static void main(String[] args) throws IOException{
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st= new StringTokenizer(br.readLine());

    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());

    map = new int[N+1][N+1];

    for(int i=1; i<N+1; i++) {
      st = new StringTokenizer(br.readLine());
      for(int j=1; j<N+1; j++){
        map[i][j] = Integer.parseInt(st.nextToken());

        if(map[i][j] == 2){ // 치킨집 좌표 저장
          chicken.add(new int[]{i,j});
        }else if(map[i][j] == 1){ // 홈 좌표 저장
          home.add(new int[]{i,j});
        }
      }
    }

    choice(new int[M],0,0);
    System.out.println(res);

  }

  static void choice(int[] selected,int start,int idx){

    if(idx == M){
      int citydist = 0;
      for(int[] h:home){
        int hX = h[0],hY= h[1];
        int mindist = Integer.MAX_VALUE;
        for(int i=0; i<M; i++){
          int[] chickenLot = chicken.get(selected[i]);
          int cX = chickenLot[0];
          int cY = chickenLot[1];
          int dist = Math.abs(hX-cX) + Math.abs(hY-cY);
          mindist = Math.min(mindist,dist);
        }
        citydist += mindist;
      }
      res = Math.min(res,citydist);
      return;
    }

    for(int i=start; i< chicken.size(); i++){
      selected[idx] = i; // 치킨집 선택
      choice(selected,i+1,idx+1); // 다음 치킨집 선택
    }
  }
}