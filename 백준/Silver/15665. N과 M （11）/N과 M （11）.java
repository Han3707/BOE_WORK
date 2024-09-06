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

        arr = new int[n];
        res = new int[m];
        visited = new boolean[n];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);  // 배열을 정렬하여 중복 수열 방지
        perm(0);
        System.out.println(sb.toString());
    }

    static void perm(int depth) {
        if (depth == m) {
            for (int i = 0; i < m; i++) {
                sb.append(res[i]).append(' ');
            }
            sb.append('\n');
            return;
        }

        int prev = -1;  // 이전에 사용한 값을 저장하는 변수

        for (int i = 0; i < n; i++) {
                if(prev != arr[i]){
                    res[depth] = arr[i];
                    prev = arr[i];
                    perm(depth+1);
            }
        }
    }
}
