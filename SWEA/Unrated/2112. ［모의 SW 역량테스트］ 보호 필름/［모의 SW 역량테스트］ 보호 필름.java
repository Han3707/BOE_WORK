import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

    static int D, W, K;
    static int[][] film;
    static int res;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= t; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            D = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            film = new int[D][W];
            for (int i = 0; i < D; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < W; j++) {
                    film[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            res = Integer.MAX_VALUE;

            DFS(0, 0);

            System.out.println("#" + tc + " " + res);
        }
    }

    static boolean checkCondition() {
        for (int col = 0; col < W; col++) {
            int cnt = 1;
            boolean clear = false;
            for (int row = 1; row < D; row++) {
                if (film[row][col] == film[row - 1][col]) cnt++;
                else cnt = 1;

                if (cnt == K) {
                    clear = true;
                    break;
                }
            }
            if (!clear) return false;
        }
        return true;
    }

    static void DFS(int idx, int count) {
        if (checkCondition()) {
            res = Math.min(res, count);
            return;
        }

        if (count >= res) return;

        if (idx == D) return;

        int[] copy = film[idx].clone();

        DFS(idx + 1, count);

        for (int i = 0; i < W; i++) {
            film[idx][i] = 0;
        }
        DFS(idx + 1, count + 1);

        for (int i = 0; i < W; i++) {
            film[idx][i] = 1;
        }
        DFS(idx + 1, count + 1);

        film[idx] = copy;
    }
}
