import javax.swing.*;
import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t= sc.nextInt();
        for(int tc = 1; tc<t+1; tc++) {
            // 테스트 케이스 수 입력
            int numIngredients = sc.nextInt(); // 재료 수를 numIngredients로 선언
            int maxCalories = sc.nextInt();    // 최대 칼로리를 maxCalories로 선언

            // DP 배열 초기화
            int[] dp = new int[maxCalories + 1];

            // 재료 정보 입력 및 DP 배열 업데이트
            for (int i = 0; i < numIngredients; i++) {
                int score = sc.nextInt();
                int calories = sc.nextInt();

                // DP 배열 역순으로 업데이트
                for (int j = maxCalories; j >= calories; j--) {
                    dp[j] = Math.max(dp[j], dp[j - calories] + score);
                }
            }

            // 최대 칼로리까지의 최대 점수 출력
            System.out.println("#"+tc+" "+dp[maxCalories]);
        }
        sc.close();
    }
}
