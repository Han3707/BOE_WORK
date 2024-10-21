import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Main {
    static int cnt;
    static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());


        int res = bfs(n);
        System.out.println(res);
    }

    static int bfs(int num){
        Queue<int[]> q = new ArrayDeque<>();
        visited = new boolean[num+1];
        cnt = 0;
        q.add(new int[]{num,cnt});

        while(!q.isEmpty()){
            int[] cur = q.poll();

            int cur_num = cur[0];
            int cur_time = cur[1];

            if(cur_num == 1) return cur_time;

            q.add(new int[]{cur_num - 1, cur_time + 1});
            if(cur_num % 2 ==0) {
                q.add(new int[]{cur_num / 2, cur_time + 1});
            }
            if(cur_num %3 ==0) {
                q.add(new int[]{cur_num / 3, cur_time + 1});
            }

        }
        return 0;
    }
}