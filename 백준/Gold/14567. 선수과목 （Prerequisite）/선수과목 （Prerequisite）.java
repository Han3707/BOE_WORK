import java.io.*;
import java.util.*;

public class Main {
    static int[] in,num; // 위상정렬 선수 카운트
    static List<Integer>[] adj;
    static int n,m;
    public static void main(String[] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());



        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        adj = new List[n+1];
        in = new int[n+1];
        num = new int[n+1]; // 결과값 저장 ***
        for(int i=1; i<n+1; i++){
            adj[i] = new ArrayList<>();
        }

        for(int i=1; i<m+1; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            adj[a].add(b);
            in[b]++;
        }

        bfs();
        for(int i=1; i<n+1; i++) {
            System.out.print(num[i]+" ");
        }
        System.out.println();
    }
    // bfs 돌리는 부분 다시 한번 보기
    static void bfs(){
        Queue<Integer> q = new LinkedList<>();

        for(int i=1; i<n+1; i++){
            if(in[i] == 0){
                q.offer(i);
                num[i] = 1; // 처음이니깐 1
            }
        }
        
        while (!q.isEmpty()){
            int cur = q.poll();
            // cur 현재 위치 poll 했으니깐 현재 위치에서 다음 위치로 갈 거 adj를 통해 확인하기
            for(int next:adj[cur]){
                in[next]--; // 확인 후 in -- 함으로써 다시 in[?] 0 인 경우 찾아서 결과값 저장
                if(in[next] == 0){
                    q.offer(next); // q 추가 
                    num[next] = num[cur] +1; // 현재 num 에서 +1
                }
            }
        }


    }
}