import java.io.*;
import java.util.*;

public class Main {

    static int N, M, D;
    static int[][] map;
    static int maxKillCount = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());

        map = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 궁수의 위치 조합 선택
        selectArchers();
        System.out.println(maxKillCount);
    }

    // 궁수 위치 조합을 선택하는 함수
    static void selectArchers() {
        int[] archers = new int[3];
        for (int i = 0; i < M - 2; i++) {
            for (int j = i + 1; j < M - 1; j++) {
                for (int k = j + 1; k < M; k++) {
                    archers[0] = i;
                    archers[1] = j;
                    archers[2] = k;
                    simulate(archers);
                }
            }
        }
    }

    // 시뮬레이션 함수
    static void simulate(int[] archers) {
        int[][] tempMap = new int[N][M];
        for (int i = 0; i < N; i++) {
            System.arraycopy(map[i], 0, tempMap[i], 0, M);
        }

        int killCount = 0;

        for (int round = 0; round < N; round++) {
            Set<int[]> targets = new HashSet<>();

            // 각 궁수마다 공격할 적을 찾음
            for (int archer : archers) {
                int[] target = findTarget(archer, tempMap);
                if (target != null) {
                    targets.add(target);
                }
            }

            // 찾은 적들을 제거
            for (int[] target : targets) {
                if (tempMap[target[0]][target[1]] == 1) {
                    killCount++;
                    tempMap[target[0]][target[1]] = 0;
                }
            }

            // 적을 아래로 한 칸씩 이동
            for (int i = N - 1; i > 0; i--) {
                System.arraycopy(tempMap[i - 1], 0, tempMap[i], 0, M);
            }
            Arrays.fill(tempMap[0], 0);
        }

        maxKillCount = Math.max(maxKillCount, killCount);
    }

    // 궁수가 공격할 적을 찾는 함수
    static int[] findTarget(int archer, int[][] map) {
        int minDistance = D + 1;
        int[] target = null;

        for (int i = N - 1; i >= 0; i--) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 1) {
                    int distance = Math.abs(N - i) + Math.abs(archer - j);
                    if (distance <= D) {
                        if (distance < minDistance || (distance == minDistance && j < target[1])) {
                            minDistance = distance;
                            target = new int[]{i, j};
                        }
                    }
                }
            }
        }

        return target;
    }
}
