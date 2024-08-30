import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {
    static int n, m;
    static int[] num, ans;
    static boolean[] visited;
    static HashSet<String> set = new HashSet<>(); // 중복 수열을 저장할 Set

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        num = new int[n];
        ans = new int[m];
        visited = new boolean[n];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            num[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(num);
        dfs(0);
    }

    static void dfs(int depth) {
        if (depth == m) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < m; i++) {
                sb.append(ans[i]).append(" ");
            }
            String sequence = sb.toString().trim();

            // Set에 중복 체크
            if (!set.contains(sequence)) {
                set.add(sequence);
                System.out.println(sequence);
            }
            return;
        }

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                visited[i] = true;
                ans[depth] = num[i];
                dfs(depth + 1);
                visited[i] = false;
            }
        }
    }
}
