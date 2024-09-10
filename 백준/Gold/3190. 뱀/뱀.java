import java.io.*;
import java.util.*;

public class Main {
    static int N, K, L;
    static int[][] map;
    static int[][] move;
    static int[] dx = {0, 1, 0, -1}; // 우, 하, 좌, 상
    static int[] dy = {1, 0, -1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine()); // 보드 크기
        K = Integer.parseInt(br.readLine()); // 사과 개수

        map = new int[N + 1][N + 1]; // 보드 크기 설정
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            map[x][y] = 1; // 사과 위치 설정
        }

        L = Integer.parseInt(br.readLine()); // 방향 변환 정보 개수
        move = new int[L][2];
        for (int i = 0; i < L; i++) {
            st = new StringTokenizer(br.readLine());
            int time = Integer.parseInt(st.nextToken());
            char direction = st.nextToken().charAt(0);
            move[i][0] = time;
            if(direction == 'L'){
                move[i][1] = 3; // 왼쪽 
            }else move[i][1] =1; // 오른쪽
        }

        System.out.println(simulate());
    }

    // 게임 시뮬레이션 함수
    static int simulate() {
        Deque<int[]> snake = new ArrayDeque<>();
        snake.offer(new int[]{1, 1}); // 뱀 시작 위치
        int dir = 0; // 처음에는 오른쪽(우)
        int time = 0;
        int moveIdx = 0; // 다음에 바꿀 방향 정보의 인덱스

        while (true) {
            
            time++;
             
            int[] head = snake.peekFirst();
            int nx = head[0] + dx[dir];
            int ny = head[1] + dy[dir];

            // 벽에 부딪히거나 자신의 몸에 부딪히면 게임 종료
            if (nx < 1 || ny < 1 || nx > N || ny > N || Exit(nx, ny, snake)) {
                break;
            }

            // 뱀의 머리를 다음 위치로 이동
            snake.offerFirst(new int[]{nx, ny});

            // 사과가 있으면 사과를 먹고 꼬리는 그대로, 없으면 꼬리 자르기
            if (map[nx][ny] == 1) {
                map[nx][ny] = 0; // 사과 제거
            } else {
                snake.pollLast(); // 꼬리 이동
            }
            if(moveIdx < L ){
            int aliveT = move[moveIdx][0];
            int dir_LR = move[moveIdx][1];
                if(time == aliveT){
                    dir = (dir + dir_LR) % 4; // 방향 전환: L은 +3, R은 +1
                    moveIdx++;
                }
            }
            // 방향 변환 정보에 따라 방향 바꾸기
            
        }

        return time; // 몇 초 동안 생존했는지 반환
    }

    // 뱀이 자기 몸과 부딪혔는지 확인하는 함수
    static boolean Exit(int x, int y, Deque<int[]> snake) {
        for (int[] body : snake) {
            if (body[0] == x && body[1] == y) {
                return true;
            }
        }
        return false;
    }
}
