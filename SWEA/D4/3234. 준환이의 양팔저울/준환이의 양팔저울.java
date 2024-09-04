import java.io.*;
import java.util.*;

public class Solution {
    static int N, total;
    static int[] weights;
    static boolean[] used;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            N = Integer.parseInt(br.readLine());
            weights = new int[N];
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                weights[i] = Integer.parseInt(st.nextToken());
            }

            total = 0;
            used = new boolean[N];
            generatePermutations(new int[N], 0);
            System.out.println("#" + tc + " " + total);
        }
    }

    // 순열 생성
    static void generatePermutations(int[] perm, int depth) {
        if (depth == N) {
            // 생성된 순열에 대해 양팔저울 배치 탐색
            dfs(0, 0, 0, perm);
            return;
        }

        for (int i = 0; i < N; i++) {
            if (!used[i]) {
                used[i] = true;
                perm[depth] = weights[i];
                generatePermutations(perm, depth + 1);
                used[i] = false;
            }
        }
    }

    // 양팔저울 배치 탐색
    static void dfs(int idx, int left, int right, int[] perm) {
        // 오른쪽이 더 크면 x 이므로 리턴
        if (left < right) return; 
        // 모든 추를 다 사용한 경우
        if (idx == N) {
            total++;
            return;
        }

        // 현재 추를 왼쪽 저울에 올리는 경우
        dfs(idx + 1, left + perm[idx], right, perm);

        // 현재 추를 오른쪽 저울에 올리는 경우
        dfs(idx + 1, left, right + perm[idx], perm);
        
    }
}
