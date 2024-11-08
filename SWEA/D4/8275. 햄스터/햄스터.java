import java.io.*;
import java.util.*;

public class Solution {
    static int N, X, M;
    static int[][] rec;
    static int[] cage;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken()); // 햄스터 우리 수
            X = Integer.parseInt(st.nextToken()); // 각 우리에 최대 X마리
            M = Integer.parseInt(st.nextToken()); // 기록 수
            rec = new int[M][3];

            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                rec[i][0] = Integer.parseInt(st.nextToken()); // l번 우리
                rec[i][1] = Integer.parseInt(st.nextToken()); // r번 우리
                rec[i][2] = Integer.parseInt(st.nextToken()); // s 마리
            }

            // 우선순위 큐 (총합이 큰 순, 사전순으로 정렬)
            PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> {
                int sumA = Arrays.stream(a).sum();
                int sumB = Arrays.stream(b).sum();

                // 총합이 큰 순서 우선
                if (sumA != sumB) return Integer.compare(sumB, sumA);

                // 총합이 같으면 사전순 (1번 인덱스부터 비교)
                for (int i = 1; i <= N; i++) {
                    if (a[i] != b[i]) return Integer.compare(a[i], b[i]);
                }
                return 0;
            });

            cage = new int[N + 1];
            perm(1, pq);

            if (pq.isEmpty()) {
                System.out.println("#" + tc + " " + -1);
            } else {
                System.out.print("#" + tc);
                int[] bestAns = pq.poll();
                for (int i = 1; i <= N; i++) {
                    System.out.print(" " + bestAns[i]);
                }
                System.out.println();
            }
        }
    }

    // 중복 순열을 통해 모든 경우 탐색
    public static void perm(int idx, PriorityQueue<int[]> pq) {
        if (idx == N + 1) {
            if (check()) {
                int[] result = new int[N + 1];
                System.arraycopy(cage, 0, result, 0, cage.length);
                pq.offer(result);
            }
            return;
        }
        
        for (int i = 0; i <= X; i++) {
            cage[idx] = i;
            perm(idx + 1, pq);
        }
    }

    // 기록된 조건 체크
    public static boolean check() {
        for (int i = 0; i < M; i++) {
            int sum = 0;
            for (int j = rec[i][0]; j <= rec[i][1]; j++) {
                sum += cage[j];
            }
            if (sum != rec[i][2]) return false;
        }
        return true;
    }
}
