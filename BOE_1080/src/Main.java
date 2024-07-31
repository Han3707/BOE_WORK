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
        int[][] arr = new int[n][m];  // A ���
        int[][] arr2 = new int[n][m]; // B ���
        int res = 0; // Ƚ�� ī��Ʈ

        // A ��� �Է� �ޱ�
        for (int i = 0; i < n; i++) {
            String num = br.readLine();
            for (int j = 0; j < m; j++) {
                arr[i][j] = num.charAt(j) - '0';
            }
        }

        // B ��� �Է� �ޱ�
        for (int i = 0; i < n; i++) {
            String num2 = br.readLine();
            for (int j = 0; j < m; j++) {
                arr2[i][j] = num2.charAt(j) - '0';
            }
        }


				// n , m �� �� index 3�̻� �� ���
        if (n > 2 && m > 2) {
            for (int i = 0; i <= n - 3; i++) {
                for (int j = 0; j <= m - 3; j++) {
										// �迭 �ΰ��� ���� �ٸ� ��� 3x3 0-> 1 , 1-> 0 ����
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
            if (Arrays.deepEquals(arr, arr2)) {  // ���� �� �迭�� ���� ������ res ���
                System.out.println(res);
            }else {
                System.out.println(-1);
            }
        }else {        // N,M �� 2�����϶� ���� ���� 0 �ٸ� ���� -1
            if (Arrays.deepEquals(arr, arr2)) {
                System.out.println(0);
            }else{
                System.out.println(-1);
            }
        }
    }
}

