import com.sun.source.tree.NewArrayTree;

import java.io.*;
import java.util.*;

// 출력 1번 과목부터 n 번 과목까지 차례대로 몇 번째 학기에 이수가능한지 번호 출력
// 입력 List<Integer>[] list 필요
// 위상정렬에서 부모(?)가 몇개 조건이 이수되어야하는지 체크하는 parent 배열 필요
// 결과값 저장하는 res[] 배열

public class Main {
    static int n,m;
    static List<Integer>[] list;
    static int[] ans,parent;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        list = new List[n+1];
        parent = new int[n+1];
        ans = new int[n+1];

        for(int i=1; i<n+1; i++){
            list[i] = new ArrayList<>();
        }


        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            list[a].add(b);
            parent[b]++;
        }

        bfs();

        for(int i=1; i<n+1; i++){
            System.out.print(ans[i]+" ");
        }
        System.out.println();
    }

    static void bfs(){
        Queue<Integer> q= new LinkedList<>();

        for(int i=1; i<n+1; i++){
            if(parent[i] == 0){
                q.offer(i);
                ans[i] = 1;
            }
        }

        while (!q.isEmpty()){
            int cur = q.poll();

            for(int next:list[cur]){
                parent[next]--;
                if(parent[next] == 0){
                    q.offer(next);
                    ans[next] = ans[cur]+1;
                }
            }

        }
    }
}