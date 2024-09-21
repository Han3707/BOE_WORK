import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine()); // 배열의 크기
        int[] arr = new int[n];
        StringTokenizer st;
        // 배열 입력
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        // 배열 오름차순 정렬
        Arrays.sort(arr);
        int count = 0;

        // 각 원소를 타겟으로 설정하여 확인
        for (int i = 0; i < n; i++) {
            if (Check(arr, i)) {
                count++;
            }
        }

        System.out.println(count);
    }

    // 현재 인덱스의 원소가 두 수의 합으로 표현될 수 있는지 확인
    private static boolean Check(int[] arr, int index) {
        int target = arr[index];
        int left = 0;
        int right = arr.length - 1;

        while (left < right) {
            if (left == index) {
                left++; // 타겟 자신은 제외
                continue;
            }
            if (right == index) {
                right--; // 타겟 자신은 제외
                continue;
            }

            int sum = arr[left] + arr[right];

            if (sum == target) {
                return true; // 두 수의 합이 타겟과 같으면 true 반환
                // left( index = 0  작은 수부터 시작 ) right( index = arr.length-1 큰 수부터 시작 )
            } else if (sum < target) { // 합이 타겟보다 작으면 값을 올려야하므로 left++ 
                left++;
            } else { // 합이 타겟보다 크면 값이 큰 것이므로 right -- 
                right--;
            }
        }

        return false; // 두 수의 합으로 표현할 수 없으면 false 반환
    }
}
