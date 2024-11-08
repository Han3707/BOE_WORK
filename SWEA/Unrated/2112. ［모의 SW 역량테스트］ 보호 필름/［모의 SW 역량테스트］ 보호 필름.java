import java.io.*;
import java.util.*;

public class Solution {
    static int D, W, K;
    static int[][] map;
    static int res;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= t; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            D = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            map = new int[D][W];
            for (int i = 0; i < D; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < W; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            res = Integer.MAX_VALUE; // 최소 횟수를 추적하기 위한 변수

            DFS(0, 0); // 첫 번째 세로줄부터 시작

            System.out.println("#" + tc + " " + res);
        }
    }

    // 조건 체크 함수: 각 열에 대해 연속된 값이 K개 이상 있는지 확인
    static boolean checkCondition() {
        for (int col = 0; col < W; col++) {
            int cnt = 1;
            boolean clear = false;

            for (int row = 1; row < D; row++) {
                if (map[row][col] == map[row - 1][col]) {
                    cnt++;
                } else {
                    cnt = 1;
                }

                if (cnt == K) {
                    clear = true;
                    break;
                }
            }
            if (!clear) return false; // 하나라도 조건을 만족하지 않으면 false 반환
        }
        return true; // 모든 열이 조건을 만족하면 true 반환
    }

    // DFS 함수: idx는 현재 처리 중인 세로줄, count는 변경한 세로줄의 수
    static void DFS(int idx, int count) {
        // 조건을 만족하면 최소 횟수를 갱신하고 종료
        if (checkCondition()) {
            res = Math.min(res, count);
            return;
        }

        // 변경한 횟수가 이미 res보다 크면 더 이상 진행하지 않음
        if (count >= res) return;

        // 마지막 세로줄에 도달하면 종료
        if (idx == D) return;

        // 현재 세로줄을 임시로 복사
        int[] copy = map[idx].clone();

        // 현재 세로줄을 변경하지 않고 넘어가기
        DFS(idx + 1, count);

        // 현재 세로줄을 0으로 변경하고 다음 세로줄 처리
        for (int i = 0; i < W; i++) {
            map[idx][i] = 0;
        }
        DFS(idx + 1, count + 1);

        // 현재 세로줄을 1로 변경하고 다음 세로줄 처리
        for (int i = 0; i < W; i++) {
            map[idx][i] = 1;
        }
        DFS(idx + 1, count + 1);

        // 원래 상태로 되돌려 놓기
        map[idx] = copy;
    }
}
