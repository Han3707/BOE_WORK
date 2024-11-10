import java.io.*;

public class Solution {

    static int[] dx = {-1, 0, 1, 0};  // 상, 우, 하, 좌 (시계 방향)
    static int[] dy = {0, 1, 0, -1}; 

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for (int tc = 1; tc <= T; tc++) {
            String C = br.readLine();
            int x = 0, y = 0, dir = 1; // 초기 위치: (0,0), 방향: 오른쪽
            int maxDist = 0;
            boolean isLoop = false;

            // 최대 4사이클만 확인
            for (int cycle = 0; cycle < 4; cycle++) {
                for (int i = 0; i < C.length(); i++) {
                    char command = C.charAt(i);
                    switch (command) {
                        case 'S':
                            x += dx[dir];
                            y += dy[dir];
                            maxDist = Math.max(maxDist, x * x + y * y);
                            break;
                        case 'L':
                            dir = (dir - 1 + 4) % 4;
                            break;
                        case 'R':
                            dir = (dir + 1) % 4;
                            break;
                    }
                }
                
                // 사이클 끝날 때마다 (0, 0)로 돌아오고 방향이 처음과 같으면 무한 루프 발생
                if (x == 0 && y == 0 && dir == 1) {
                    isLoop = true;
                    break;
                }
            }

            sb.append("#").append(tc).append(" ");
            if (isLoop) {
                sb.append(maxDist).append("\n");
            } else {
                sb.append("oo").append("\n");
            }
        }
        System.out.print(sb);
    }
}
