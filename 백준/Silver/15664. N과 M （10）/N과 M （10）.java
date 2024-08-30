import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int n, m;
    static int[] num, ans;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken()); // 전역 변수 n 할당
        m = Integer.parseInt(st.nextToken()); // 전역 변수 m 할당

        num = new int[n];
        ans = new int[m];
        visited = new boolean[n];

        st = new StringTokenizer(br.readLine()); // 새로운 라인에서 숫자들 읽기

        for (int i = 0; i < n; i++) {
            num[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(num); // 숫자를 오름차순으로 정렬
        dfs(0,0);
    }

    static void dfs(int depth,int start) {
        if (depth == m) {
            for (int i = 0; i < m; i++) {
                System.out.print(ans[i] + " ");
            }
            System.out.println();
            return;
        }

        int last = 0; // 마지막으로 사용된 숫자 저장용 변수
        for (int i = start; i < n; i++) {
            if (last != num[i]) {

                ans[depth] = num[i];
                last = num[i];
                dfs(depth + 1,i+1);

            }
        }
    }
}
