import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        String str = br.readLine();

        long[] arr = new long[n];
        long ans = 0;
        long pow = 1;
        long mod = 1234567891;

        for (int i = 0; i < n; i++) {
            arr[i] = (str.charAt(i) - 'a' + 1); // 알파벳을 숫자로 변환

            ans = (ans + arr[i] * pow) % mod;
            pow = (pow * 31) % mod; // 제곱 값을 매번 계산 (값이 커지면 오버플로우 나기 때문에 미리)
        }

        System.out.println(ans);
    }
}
