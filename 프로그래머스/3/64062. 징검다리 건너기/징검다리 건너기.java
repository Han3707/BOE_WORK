
import java.util.*;

class Solution {
    public int solution(int[] stones, int k) {
        // 이분 탐색을 위한 최소/최대 친구 수 설정
        int left = 1;
        int right = Arrays.stream(stones).max().getAsInt(); // stones의 실제 최대값
        int answer = 0;
        
        while (left <= right) {
            int mid = (left + right) / 2;
            if (canCross(stones, k, mid)) {
                answer = mid; // 가능한 최대값 저장
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return answer;
    }
    
    private boolean canCross(int[] stones, int k, int people) {
        int consecutive = 0;
        
        for (int stone : stones) {
            if (stone < people) { // 핵심: people명보다 작은 값의 돌은 people번째 사람이 밟을 수 없음
                consecutive++;
                if (consecutive >= k) {
                    return false; // 연속 k개의 밟을 수 없는 돌 발생
                }
            } else {
                consecutive = 0; // 밟을 수 있는 돌이 나오면 리셋
            }
        }
        return true;
    }
}