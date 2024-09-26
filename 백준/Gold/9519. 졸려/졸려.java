import java.io.*;
import java.util.*;

public class Main {
    static int len;
    static char[] cs;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int X = Integer.parseInt(br.readLine());
        String s = br.readLine();
        len = s.length();
        cs = s.toCharArray();

        // 주기 찾기
        HashSet<String> set = new HashSet<>();
        String original = new String(cs);
        set.add(original);  // 원래 문자열을 미리 추가

        int cycleLength = 0;

        while (true) {
            func();
            cycleLength++;
            String current = new String(cs);
            if (set.contains(current)) {
                break;  // 주기를 발견하면 종료
            }
            set.add(current);  // 새로 섞인 문자열 추가
        }

        // X를 주기로 나눈 나머지만큼만 실행
        X = X % cycleLength;
        cs = original.toCharArray();  // 초기 상태로 재설정

        for (int i = 0; i < X; i++) {
            func();  // X 횟수만큼 섞기
        }

        System.out.println(new String(cs));  // 결과 출력
    }
    
    // 문자열 섞기
    static void func() {
        char[] arr = new char[len];
        for (int i = 0; i < len; i++) {
            if (i % 2 == 0) {
                arr[i / 2] = cs[i];  // 짝수 인덱스
            } else {
                arr[len - 1 - (i / 2)] = cs[i];  // 홀수 인덱스
            }
        }
        cs = arr;  // 섞인 배열로 업데이트
    }
}
