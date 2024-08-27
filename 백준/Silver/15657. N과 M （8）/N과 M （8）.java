import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int n, m;
    static int[] arr;
    static StringBuilder sb = new StringBuilder(); // StringBuilder 사용

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        arr = new int[n];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);
        nm5(0, new int[m],0);

        System.out.print(sb.toString()); // 한번에 출력
    }

    private static void nm5(int cnt, int[] selected,int idx) {
        if (cnt == m) {
            for (int i = 0; i < selected.length; i++) {
                sb.append(selected[i]).append(" "); // StringBuilder에 추가
            }
            sb.append("\n");
            return;
        }

        for (int i = idx; i < arr.length; i++) {
            selected[cnt] = arr[i];
            nm5(cnt + 1, selected,i);
        }
    }
}
