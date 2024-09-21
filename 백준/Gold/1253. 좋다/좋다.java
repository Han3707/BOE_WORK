import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(); // 배열의 크기
        int[] arr = new int[n];

        // 배열 입력
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        // 배열 오름차순 정렬
        Arrays.sort(arr);
        int count = 0;

        // 각 원소를 타겟으로 설정하여 확인
        for (int i = 0; i < n; i++) {
            if (isGood(arr, i)) {
                count++;
            }
        }

        System.out.println(count);
    }

    // 현재 인덱스의 원소가 두 수의 합으로 표현될 수 있는지 확인
    private static boolean isGood(int[] arr, int index) {
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
            } else if (sum < target) {
                left++;
            } else {
                right--;
            }
        }

        return false; // 두 수의 합으로 표현할 수 없으면 false 반환
    }
}
