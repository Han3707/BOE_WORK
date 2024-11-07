import java.io.*;
import java.util.*;

public class Solution {
    static int[][] map = new int[100][100];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        for (int tc = 1; tc <= 10; tc++) {
            br.readLine(); // 테스트 케이스 번호 (사용하지 않음)
            int startX = 0, startY = 0;

            // 입력 처리 및 도착점 찾기
            for (int i = 0; i < 100; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < 100; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                    if (map[i][j] == 2) {
                        startX = i;
                        startY = j; // 도착점 위치
                    }
                }
            }

            // 해결 함수 실행
            int result = solve(startX, startY);
            System.out.println("#" + tc + " " + result);
        }
    }

    static int solve(int x, int y) {
        
        // 올라가다가 좌우로 갈 수 있는 길이 있으면 가야함 따라서 한칸씩 움직이면서 좌우로 이동 가능한지 확인
        while (x > 0) {

            x--;

            // 왼쪽으로 가능한 만큼 이동
            if (y > 0 && map[x][y - 1] == 1) {
                while (y > 0 && map[x][y - 1] == 1) {
                    y--;  // 왼쪽으로 이동
                }
            }
            // 오른쪽으로 가능한 만큼 이동(왼쪽이 불가능하다면)
            else if (y < 99 && map[x][y + 1] == 1) {
                while (y < 99 && map[x][y + 1] == 1) {
                    y++;  // 오른쪽으로 이동
                }
            }
        }

        return y; // 최종적으로 도달한 y 값 반환
    }
}
