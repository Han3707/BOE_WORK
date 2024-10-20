import java.util.*;
import java.io.*;

public class Main {
    static int N, M;
    static boolean[][] compare;  // 비교 관계를 저장하는 배열

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        compare = new boolean[N + 1][N + 1];  // 물체 간의 비교 결과를 저장할 배열

        // 초기화: 자기 자신과의 관계는 항상 true
        for (int i = 1; i <= N; i++) {
            compare[i][i] = true;
        }

        // 입력을 받아 비교 관계 저장
        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            compare[a][b] = true;  // a > b
        }

        // 플로이드-와샬 알고리즘 적용
        for (int k = 1; k <= N; k++) {
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    if (compare[i][k] && compare[k][j]) {
                        compare[i][j] = true;  // i > k, k > j이면 i > j
                    }
                }
            }
        }

        // 각 물체에 대해 비교할 수 없는 물체의 수 계산
        for (int i = 1; i <= N; i++) {
            int count = 0;
            for (int j = 1; j <= N; j++) {
                if (!compare[i][j] && !compare[j][i]) {
                    count++;
                }
            }
            System.out.println(count);
        }
    }
}
