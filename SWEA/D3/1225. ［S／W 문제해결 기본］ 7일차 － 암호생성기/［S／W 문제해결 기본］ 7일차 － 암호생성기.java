import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Solution {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        for (int t = 1; t < 11; t++) {
            int tc = Integer.parseInt(br.readLine());
            ArrayDeque<Integer> deq = new ArrayDeque<>();
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < 8; i++) {
                int num = Integer.parseInt(st.nextToken());
                deq.add(num);
            }

            while (true) {
                boolean stop = false; // 8개의 숫자 중 하나가 0이 되었을 때

                for (int k = 1; k <= 5; k++) {
                    int temp = deq.poll();
                    temp -= k;

                    if (temp <= 0) {
                        temp = 0;
                        stop = true; // 0이 되었을 때 멈추기 위한 true
                    }

                    deq.add(temp);

                    if (stop) break; // 0을 찾았으므로 암호완성이니깐 반목문 빠져나감
                }

                if (deq.peekLast() == 0) break; // 위와 동일
            }
            System.out.print("#"+tc+" ");
            for (int num : deq) {
                System.out.print(num + " ");
            }
        }
    }
}
