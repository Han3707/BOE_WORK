import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        
        int count = 0;
        int num = 665; // 666부터 시작하기 위해 초기값을 665로 둡니다.
        
        while (true) {
            num++;
            if (String.valueOf(num).contains("666")) {
                count++;
            }
            if (count == N) {
                System.out.println(num);
                break;
            }
        }
    }
}
