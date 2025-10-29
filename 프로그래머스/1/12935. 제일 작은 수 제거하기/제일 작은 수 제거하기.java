import java.util.*;

class Solution {
    public int[] solution(int[] arr) {
        // 1. 원소가 하나면 [-1] 반환
        if (arr.length == 1) return new int[]{-1};
        
        // 2. 가장 작은 값 찾기
        int min = arr[0];
        for (int num : arr) {
            if (num < min) min = num;
        }
        
        // 3. 최소값 제외한 새 배열 생성
        List<Integer> list = new ArrayList<>();
        for (int num : arr) {
            if (num != min) list.add(num);
        }
        
        // 4. List → int 배열로 변환
        int[] answer = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            answer[i] = list.get(i);
        }
        
        return answer;
    }
}
