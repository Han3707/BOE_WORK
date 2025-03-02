import java.util.Arrays;

class Solution {
    public int[] solution(int[] array, int[][] commands) {
        int size = commands.length;
        int[] answer = new int[size];
        
        for (int i = 0; i < size; i++) {
            int start = commands[i][0] - 1;  // 배열 인덱스는 0부터 시작하므로 1을 빼줌
            int end = commands[i][1];        // end는 포함해야 하므로 그대로 사용
            int k = commands[i][2] - 1;      // k번째 숫자를 찾기 위해 1을 빼줌
            
            int[] subArray = Arrays.copyOfRange(array, start, end); // 부분 배열 추출
            Arrays.sort(subArray); // 정렬
            
            answer[i] = subArray[k]; // k번째 숫자 선택
        }
        
        return answer;
    }
}
