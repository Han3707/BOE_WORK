import java.util.*;
import java.io.*;

public class Solution {


    static int N, map[][], minWireSum, maxCoreCnt;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};
    static List<int[]> list;

  

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            N = Integer.parseInt(br.readLine());

            map = new int[N][N];
            list = new ArrayList<>();

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                    if(map[i][j] == 1) {
                        // 가장자리는 신경안써도 됨
                        if(i == 0 || i == N-1 || j == 0 || j == N-1) continue;
                        list.add(new int[]{i, j});
                    }
                }
            }

            minWireSum = Integer.MAX_VALUE;
            maxCoreCnt = Integer.MIN_VALUE;

            bt(0,0,0);
            sb.append("#").append(tc).append(" ").append(minWireSum).append("\n");
        }
        System.out.println(sb);
    }

    public static void bt(int idx, int coreCnt, int wireSum) {
        if(idx == list.size()) {         // 모든 코어 다 돌아봤을 때
            if(maxCoreCnt < coreCnt) {   // 코어 최대
                maxCoreCnt = coreCnt;
                minWireSum = wireSum;
            }
            if(maxCoreCnt == coreCnt) {  // 전선 길이 최소
                minWireSum = Math.min(wireSum, minWireSum);
            }
            return;
        }
        int[] cur = list.get(idx);
        int x = cur[0];
        int y = cur[1];

        // 직진 탐색
        for (int i = 0; i < 4; i++) {
            int cnt = 0, nx = x, ny = y;
            // 어떠한 조건을 만족할 때까지 직진
            while(true) {
                nx += dx[i];
                ny += dy[i];

                // 범위 벗어남
                if (nx < 0 || nx >= N || ny < 0 || ny >= N) {
                    break;
                }
                // 가다가 다른 코어 만남 또는 전전 만남
                if(map[nx][ny] == 1) {
                    cnt = 0;
                    break;
                }

                cnt++;
            }

            // 초기화
            nx = x;
            ny = y;
            // 전선깔기 : 1로 채우기
            for (int j = 0; j < cnt; j++) {
                nx += dx[i];
                ny += dy[i];
                map[nx][ny] = 1;
            }

            if(cnt == 0) bt(idx+1, coreCnt, wireSum);
            else {
                bt(idx+1, coreCnt+1, wireSum+cnt);

                // 초기화
                nx = x;
                ny = y;
                // 되돌려놓기
                for (int j = 0; j < cnt; j++) {
                    nx += dx[i];
                    ny += dy[i];
                    map[nx][ny] = 0;
                }
            }
        }

    }
}
