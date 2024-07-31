import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        // TODO Auto-generated method stub
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[][] arr = new int[n][m];  // A 행렬
        int[][] arr2 = new int[n][m]; // B 행렬
        int res = 0; // 횟수 카운트

        // A 행렬 입력 받기
        for (int i = 0; i < n; i++) {
            String num = br.readLine();
            for (int j = 0; j < m; j++) {
                arr[i][j] = num.charAt(j) - '0';
            }
        }

        // B 행렬 입력 받기
        for (int i = 0; i < n; i++) {
            String num2 = br.readLine();
            for (int j = 0; j < m; j++) {
                arr2[i][j] = num2.charAt(j) - '0';
            }
        }


				// n , m 둘 다 index 3이상 인 경우
        if (n > 2 && m > 2) {
            for (int i = 0; i <= n - 3; i++) {
                for (int j = 0; j <= m - 3; j++) {
										// 배열 두개가 서로 다른 경우 3x3 0-> 1 , 1-> 0 돌림
                    if (arr[i][j] != arr2[i][j]) {
                        for (int x = i; x < 3 + i; x++) {
                            for (int y = j; y < 3 + j; y++) {

                                if (arr[x][y] == 1) {
                                    arr[x][y] = 0;
                                } else {
                                    arr[x][y] = 1;
                                }
                            }
                        }
                        res++;
                    }

                }
            }
            if (Arrays.deepEquals(arr, arr2)) {  // 돌린 후 배열이 서로 같으면 res 출력
                System.out.println(res);
            }else {
                System.out.println(-1);
            }
        }else {        // N,M 이 2이하일때 같은 경우는 0 다를 경우는 -1
            if (Arrays.deepEquals(arr, arr2)) {
                System.out.println(0);
            }else{
                System.out.println(-1);
            }
        }
    }
}

