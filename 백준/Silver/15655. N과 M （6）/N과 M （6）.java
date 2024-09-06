import java.util.*;
import java.io.*;

public class Main {
    static int n, m;
    static int[] arr, res;
    static boolean[] visited;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        arr = new int[n + 1];
        res = new int[m];
        visited = new boolean[n + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i < n + 1; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);


        perm(0,1);

        System.out.println(sb.toString());

    }

    static void perm(int x,int start) {
        if (x == m) {
            for (int i = 0; i < m; i++) {
                sb.append(res[i]).append(' ');
            }
            sb.append('\n');
            return;
        }

        for (int i = start; i < n + 1; i++) {
//            if(!visited[i]){
//                visited[i] = true;
            res[x] = arr[i];
            perm(x + 1,i+1);
//                visited[i] = false;
        }

    }

}


