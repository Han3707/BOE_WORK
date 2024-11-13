import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static List<Integer>[] adj;
    static int[] numP;
    static int minDiff = Integer.MAX_VALUE;
    static boolean[] select;
    static boolean isPossible = false; // 연결이 되어 있는지
    public static void main(String[] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        numP = new int[n+1];
        adj = new List[n+1];
        select = new boolean[n+1];
        st = new StringTokenizer(br.readLine());
        for(int i=1; i<n+1; i++){
            numP[i] = Integer.parseInt(st.nextToken());
            adj[i] = new ArrayList<>();
        }

        for(int i=1; i<n+1; i++){
            st = new StringTokenizer(br.readLine());
            int cnt = Integer.parseInt(st.nextToken());
            for(int j=0; j<cnt; j++){
                adj[i].add(Integer.parseInt(st.nextToken()));
            }
        }

        simul(1);

        if(isPossible) System.out.println(minDiff);
        else System.out.println(-1);
    }

    static void simul(int idx){
        if(idx == n+1){
            calc();
            return;
        }

        select[idx] = true;
        simul(idx+1);
        select[idx] = false;
        simul(idx+1);
    }

    static void calc(){
        List<Integer> group1 = new ArrayList<>();
        List<Integer> group2 = new ArrayList<>();
        int sum1 = 0;
        int sum2 = 0;

        for(int i=1; i<n+1; i++){
            if(select[i]){
                group1.add(i);
                sum1 += numP[i];
            }else{
                group2.add(i);
                sum2 += numP[i];
            }
        }

        if(!group1.isEmpty() && !group2.isEmpty() && isConnected(group1) && isConnected(group2)){
            isPossible = true;
            minDiff = Math.min(minDiff,Math.abs(sum1-sum2));
        }
    }

    static boolean isConnected(List<Integer> list){
        Queue<Integer> q = new LinkedList<>();
        boolean[] visit = new boolean[n+1];
        int cur = list.get(0);
        visit[cur] = true;
        q.offer(cur);
        int cnt = 1;

        while(!q.isEmpty()){
            int start = q.poll();

            for(int next:adj[start]){

                if(list.contains(next) && !visit[next]){
                    visit[next] = true;
                    cnt++;
                    q.offer(next);
                }
            }
        }
        return list.size() == cnt;
    }
}