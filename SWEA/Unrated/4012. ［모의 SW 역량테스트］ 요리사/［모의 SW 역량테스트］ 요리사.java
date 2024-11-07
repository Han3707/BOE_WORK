import java.io.*;
import java.util.*;

public class Solution {
    static int t, n;
    static int[][] map;
    static int minDiff;
    static boolean[] visit;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        t = Integer.parseInt(br.readLine());
        StringTokenizer st;

        for (int tc = 1; tc <= t; tc++) {
            n = Integer.parseInt(br.readLine());
            map = new int[n][n];
            minDiff = Integer.MAX_VALUE;
            visit = new boolean[n];

            // 시너지 맵 입력
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < n; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }



            // 조합 생성 및 최소 차이 계산
            combination(0, 0,visit);
            System.out.println("#" + tc + " " + minDiff);
        }
    }

    static void combination(int start,int depth,boolean[] visit){

        if(depth == n/2){
            int sum1 =0;
            int sum2 =0;

            for(int i=0; i<n; i++){
                for(int j=i+1; j<n; j++){
                    if(visit[i] && visit[j]){
                        sum1 += map[i][j] + map[j][i];
                    }else if(!visit[i] && !visit[j]){
                        sum2 += map[i][j] + map[j][i];
                    }
                }
            }
            int res = Math.abs(sum1-sum2);
            minDiff = Math.min(minDiff,res);
            return;
        }

        for(int i=start; i<n; i++){
            visit[i] = true;
            combination(i+1,depth+1,visit);
            visit[i] = false;
        }

    }



}
