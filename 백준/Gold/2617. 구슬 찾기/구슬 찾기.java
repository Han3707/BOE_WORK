import java.io.*;
import java.util.*;


public class Main {
    static int n,m;
    static List<Integer>[] small;
    static List<Integer>[] big;
    static boolean[] visit;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        small = new List[n+1];
        big = new List[n+1];

        for(int i=1; i<n+1; i++){
            small[i] = new ArrayList<>();
            big[i] = new ArrayList<>();
        }


        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            small[b].add(a); // 자기보다 작은 애들 저장
            big[a].add(b); // 자기보다 큰 애들 저장
        }

        int ch = (n+1)/2;
        int ans = 0;
        for(int i=1; i<n+1; i++){
            int s = dfs(i,small);
            int b = dfs(i,big);

            if(s >= ch || b >= ch) ans++;

        }

        System.out.println(ans);

    }

    static int dfs(int start,List<Integer>[] list){
        visit = new boolean[n+1];
        visit[start] = true;
        return dfscnt(start,list);
    }

    static int dfscnt(int idx,List<Integer>[] list){
        int cnt = 0; // 자기보다 작거나 큰 수 개수 체크하는 변수
        for(int next:list[idx]){
            if(!visit[next]){
                visit[next] = true;
                cnt+= 1+dfscnt(next,list);
            }
        }
        return cnt;
    }
}