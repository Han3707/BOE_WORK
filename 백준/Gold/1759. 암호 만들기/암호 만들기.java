import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static StringBuilder sb = new StringBuilder();
    static int n, m;
    static char[] arr;
    static List<String> result;
    static Set<Character> vowels = Set.of('a', 'e', 'i', 'o', 'u');

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        arr = new char[m];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < m; i++) {
            arr[i] = st.nextToken().charAt(0);
        }
        Arrays.sort(arr); // 사전 순서로 정렬
        result = new ArrayList<>();
        combination(0, 0, 0, 0);

        for (String s : result) {
            System.out.println(s);
        }
    }

    static void combination(int idx, int depth, int Acnt, int Bcnt) {
        if (depth == n) {
            if (Acnt >= 1 && Bcnt >= 2) {
                result.add(sb.toString()); // 조건을 만족할 때만 리스트에 추가
            }
            return;
        }

        for (int i = idx; i < m; i++) {
            char temp = arr[i];
            sb.append(temp);

            // 모음 또는 자음인지 확인하여 카운트 증가
            // 모음 = Acnt , 자음 = Bcnt
            if (vowels.contains(temp)) {
                combination(i + 1, depth + 1, Acnt + 1, Bcnt);
            } else {
                combination(i + 1, depth + 1, Acnt, Bcnt + 1);
            }

            sb.deleteCharAt(sb.length() - 1); // 백트래킹
        }
    }
}
