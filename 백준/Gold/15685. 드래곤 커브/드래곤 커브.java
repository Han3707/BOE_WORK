import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static int[] dx = {1, 0, -1, 0}; // 방향: 오른쪽, 위쪽, 왼쪽, 아래쪽
    static int[] dy = {0, -1, 0, 1};
    static int[][] map = new int[101][101];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int g = Integer.parseInt(st.nextToken());

            dragon(x, y, d, g);
        }

        int cnt = 0;
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                if (checkSquare(i, j)) cnt++;
            }
        }

        System.out.println(cnt);
    }

    static void dragon(int x, int y, int d, int g) {
        ArrayList<Integer> directions = new ArrayList<>();
        directions.add(d);
        map[x][y] = 1;

        // 0세대 생성
        x += dx[d];
        y += dy[d];
        map[x][y] = 1;

        // 각 세대마다 방향 리스트 갱신
        for (int i = 0; i < g; i++) {
            int size = directions.size();
            for (int j = size - 1; j >= 0; j--) {
                d = (directions.get(j) + 1) % 4;
                x += dx[d];
                y += dy[d];
                map[x][y] = 1;
                directions.add(d);
            }
        }
    }

    static boolean checkSquare(int x, int y) {
        // 정사각형의 네 꼭짓점이 모두 드래곤 커브의 일부인지 확인
        return map[x][y] == 1 && map[x + 1][y] == 1 && map[x][y + 1] == 1 && map[x + 1][y + 1] == 1;
    }
}
