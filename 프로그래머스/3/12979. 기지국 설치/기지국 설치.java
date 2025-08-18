import java.util.*;

class Solution {
    public int solution(int n, int[] stations, int w) {
        int answer = 0;
        int coverage = 2 * w + 1;
        int last_covered = 0; // 현재까지 커버된 마지막 아파트 위치

        for (int station : stations) {
            // 현재 기지국이 커버하기 시작하는 위치
            int current_start = station - w;

            // 이전 기지국 커버 범위와 현재 기지국 커버 범위 사이의 빈 공간
            int gap = current_start - last_covered - 1;
            
            if (gap > 0) {
                answer += (gap + coverage - 1) / coverage;
            }

            // last_covered를 현재 기지국이 커버하는 마지막 위치로 업데이트
            last_covered = station + w;
        }

        // 마지막 기지국 뒤에 남은 빈 공간 처리
        int last_gap = n - last_covered;
        if (last_gap > 0) {
            answer += (last_gap + coverage - 1) / coverage;
        }

        return answer;
    }
}