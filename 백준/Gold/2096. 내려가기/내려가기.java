import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[][] map = new int[n][3];

        // map 배열 초기화
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 현재 줄에서 최대, 최소 값 저장
        int[] maxDp = new int[3];
        int[] minDp = new int[3];

        // 첫 줄 초기화
        for (int i = 0; i < 3; i++) {
            maxDp[i] = minDp[i] = map[0][i];
        }

        // DP 진행 (두 번째 줄부터 마지막 줄까지)
        for (int i = 1; i < n; i++) {
            int[] newMaxDp = new int[3];
            int[] newMinDp = new int[3];

            // 최대값 DP
            newMaxDp[0] = map[i][0] + Math.max(maxDp[0], maxDp[1]);
            newMaxDp[1] = map[i][1] + Math.max(maxDp[0], Math.max(maxDp[1], maxDp[2]));
            newMaxDp[2] = map[i][2] + Math.max(maxDp[1], maxDp[2]);

            // 최소값 DP
            newMinDp[0] = map[i][0] + Math.min(minDp[0], minDp[1]);
            newMinDp[1] = map[i][1] + Math.min(minDp[0], Math.min(minDp[1], minDp[2]));
            newMinDp[2] = map[i][2] + Math.min(minDp[1], minDp[2]);

            // 업데이트
            maxDp = newMaxDp;
            minDp = newMinDp;
        }

        // 최종 최대값과 최소값 계산
        int maxResult = Math.max(maxDp[0], Math.max(maxDp[1], maxDp[2]));
        int minResult = Math.min(minDp[0], Math.min(minDp[1], minDp[2]));

        System.out.println(maxResult + " " + minResult);
    }
}
