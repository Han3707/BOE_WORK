import java.util.Arrays;

class Solution {
    public String solution(int[] numbers) {
        // 정수 배열을 문자열 배열로 변환
        String[] numStr = new String[numbers.length];
        for (int i = 0; i < numbers.length; i++) {
            numStr[i] = String.valueOf(numbers[i]);
        }
        
        // 사용자 정의 비교자를 사용하여 정렬
        // 두 문자열 a와 b를 비교할 때, (b+a)와 (a+b)를 비교하여 큰 순서로 정렬합니다.
        Arrays.sort(numStr, (a, b) -> (b + a).compareTo(a + b));
        
        // 가장 큰 수가 "0"인 경우 (예: [0, 0, 0]) "0"을 반환
        if (numStr[0].equals("0")) {
            return "0";
        }
        
        // 정렬된 문자열들을 이어붙여 결과 생성
        StringBuilder answer = new StringBuilder();
        for (String s : numStr) {
            answer.append(s);
        }
        
        return answer.toString();
    }
}