import java.io.*;
import java.util.*;


public class Main {
    static final int INF = 9999;
    static int n;
    static int[][] dist;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        
        dist = new int[n][n];
        
        for(int i = 0; i<n; i++){
            String str = br.readLine();
            for(int j = 0; j<n; j++){
                if(i==j){
                    dist[i][j] = INF;
                    continue;
                }
                dist[i][j] = (str.charAt(j) ==  'Y') ? 1 : INF;
            }
        }
        
        for (int k = 0; k < n; k++){
            for(int i= 0; i < n; i++){
                for(int j= 0; j < n; j++){
                    if(i==j || j == k || i == k) continue;
                    if(dist[i][j] > dist[i][k] + dist[k][j]){
                        dist[i][j] = dist[i][k] + dist[k][j];
                    }
                }
            }
        }
        
        int max =0;
        for(int i= 0; i < n ; i++){
            int cnt = 0;
            for(int j = 0; j < n; j++){
                if(i == j) continue;
                if(dist[i][j] <= 2){
                    cnt++;
                }
            }
            max = Math.max(max,cnt);
        }
        System.out.println(max);
    }
}
