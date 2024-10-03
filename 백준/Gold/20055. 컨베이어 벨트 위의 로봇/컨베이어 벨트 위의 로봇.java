import java.io.*;
import java.util.*;

public class Main {
    static int N, K;
    static int[] robot;
    static int[] map;
    static int res = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 벨트의 길이
        K = Integer.parseInt(st.nextToken()); // 내구도 0인 칸의 개수 제한

        map = new int[2 * N]; // 벨트의 내구도
        robot = new int[N]; // 로봇이 있는 위치 (0부터 N-1까지)

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 2 * N; i++) {
            map[i] = Integer.parseInt(st.nextToken());
        }

        res = move();
        System.out.println(res);
    }

    static int move() {
        int turn = 0;

        while (true) {
            turn++;

            // 1. 벨트가 회전
            rotateBelt();

            // 2. 로봇을 이동
            moveRobots();

            // 3. 1번 위치에 로봇을 추가 (내구도가 0보다 크면)
            addRobot();

            // 4. 내구도가 0인 칸의 개수가 K개 이상이면 종료
            if (checkEnd()) {
                return turn;
            }
        }
    }

    // 벨트를 회전시키는 함수
    static void rotateBelt() {
        // 내구도 배열 회전
        int lastDurability = map[2 * N - 1];
        for (int i = 2 * N - 1; i > 0; i--) {
            map[i] = map[i - 1];
        }
        map[0] = lastDurability;

        // 로봇 위치도 회전
        for (int i = N - 1; i > 0; i--) {
            robot[i] = robot[i - 1];
        }
        robot[0] = 0; // 1번 위치에 로봇이 없으므로 초기화

        // N번째 위치에서 로봇이 떨어지므로 N-1 위치에 로봇이 있으면 제거
        robot[N - 1] = 0;
    }

    // 로봇을 이동시키는 함수
    static void moveRobots() {
        // N-2에서 0으로 로봇을 이동 (뒤에서 앞으로)
        for (int i = N - 2; i >= 0; i--) {
            if (robot[i] == 1 && robot[i + 1] == 0 && map[i + 1] > 0) {
                // 로봇을 한 칸 이동
                robot[i] = 0;
                robot[i + 1] = 1;
                // 내구도 감소
                map[i + 1]--;

                // 로봇이 내려가는 위치에 도착하면 제거
                if (i + 1 == N - 1) {
                    robot[i + 1] = 0;
                }
            }
        }
    }

    // 1번 위치에 로봇을 추가하는 함수
    static void addRobot() {
        if (map[0] > 0) {
            robot[0] = 1; // 로봇 추가
            map[0]--; // 내구도 감소
        }
    }

    // 내구도가 0인 칸의 개수를 확인하는 함수
    static boolean checkEnd() {
        int cnt = 0;
        for (int i = 0; i < 2 * N; i++) {
            if (map[i] == 0) {
                cnt++;
            }
        }
        return cnt >= K;
    }
}
