import java.io.*;
import java.util.*;

public class Main {
    static int N, M, K, minS;
    static int[][] arr, copyarr;
    static int[][] locate;
    static int[] all;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        arr = new int[N + 1][M + 1];
        locate = new int[K][3];
        all = new int[K]; // 모든 조합을 저장할 곳
        visited = new boolean[K];

        for (int i = 1; i < N + 1; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j < M + 1; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        copyarr = new int[N + 1][M + 1];

        for (int k = 0; k < K; k++) {
            st = new StringTokenizer(br.readLine());
            locate[k][0] = Integer.parseInt(st.nextToken());
            locate[k][1] = Integer.parseInt(st.nextToken());
            locate[k][2] = Integer.parseInt(st.nextToken());
        }

        minS = Integer.MAX_VALUE; // 최소값 초기화
        perm(0); // 순열로 주어진 좌표의 모든 조합을 돌려 최소값을 찾아야함

        System.out.println(minS);
    }

    static void perm(int depth) {
        if (depth == K) {
            // 배열 복사
            for (int i = 1; i < N + 1; i++) {
                copyarr[i] = arr[i].clone();
            }

            // 회전 연산 수행
            for (int i = 0; i < K; i++) {
                int r = locate[all[i]][0];
                int c = locate[all[i]][1];
                int s = locate[all[i]][2];
                turn(r, c, s, copyarr);
            }

            // 회전 후 최소 행의 합 계산
            int min_cur = Integer.MAX_VALUE;
            for (int i = 1; i < N + 1; i++) {
                int rowSum = 0;
                for (int j = 1; j < M + 1; j++) {
                    rowSum += copyarr[i][j];
                }
                min_cur = Math.min(min_cur, rowSum); // 최소값 갱신
            }

            minS = Math.min(minS, min_cur); // 최종 최소값 갱신
            return;
        }

        // 순열 탐색
        for (int i = 0; i < K; i++) {
            if (!visited[i]) {
                visited[i] = true;
                all[depth] = i;
                perm(depth + 1);
                visited[i] = false;
            }
        }
    }

    // 배열을 회전시키는 함수
    static void turn(int r, int c, int s, int[][] copy) {
        while (s > 0) {
            int startX = r - s;
            int startY = c - s;
            int endX = r + s;
            int endY = c + s;

            int temp = copy[startX][startY]; // 시작 위치 값 저장

            // 왼쪽 -> 위로 이동
            for (int i = startX; i < endX; i++) {
                copy[i][startY] = copy[i + 1][startY];
            }

            // 아래 -> 왼쪽으로 이동
            for (int i = startY; i < endY; i++) {
                copy[endX][i] = copy[endX][i + 1];
            }

            // 오른쪽 -> 아래로 이동
            for (int i = endX; i > startX; i--) {
                copy[i][endY] = copy[i - 1][endY];
            }

            // 위 -> 오른쪽으로 이동
            for (int i = endY; i > startY + 1; i--) {
                copy[startX][i] = copy[startX][i - 1];
            }

            copy[startX][startY + 1] = temp; // 저장한 temp 값 복원

            s--; // 안쪽 레이어로 이동
        }
    }
}
