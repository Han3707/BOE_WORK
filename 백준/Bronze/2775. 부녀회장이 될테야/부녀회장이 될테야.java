import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine());

        for(int i=0; i<t; i++){
            int k = Integer.parseInt(br.readLine());
            int n = Integer.parseInt(br.readLine());

            int[][] arr= new int[15][15]; // k,n 범위 15



            for(int x=0; x<15; x++){
                for(int y=0; y<15; y++){
                    arr[0][y] = y; // 0층은 1호당 1명씩 늘어나므로
                    arr[x][1] = 1; // 1호는 1명 고정
                }
            }

            for(int x=1; x<15; x++){
                for(int y=2; y<15; y++){
                    arr[x][y] = arr[x-1][y] + arr[x][y-1];
                }
            }

            System.out.println(arr[k][n]);
        }
    }
}