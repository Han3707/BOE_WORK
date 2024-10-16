import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int[] arr;
    static boolean[] visited;
    static int[] perm;
    static int maxSum = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        arr = new int[n];
        visited = new boolean[n];
        perm = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        // 모든 순열을 계산
        Permutations(0);

        System.out.println(maxSum);
    }

    // 순열 생성 함수
    static void Permutations(int depth) {
        if (depth == n) {
            // 순열이 완성되면 차의 절댓값 합 계산
            int sum = calculateSum();
            maxSum = Math.max(maxSum, sum);
            return;
        }

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                visited[i] = true;
                perm[depth] = arr[i];
                Permutations(depth + 1);
                visited[i] = false;
            }
        }
    }

    // 차의 절댓값 합 계산 함수
    static int calculateSum() {
        int sum = 0;
        for (int i = 0; i < n - 1; i++) {
            sum += Math.abs(perm[i] - perm[i + 1]);
        }
        return sum;
    }
}
