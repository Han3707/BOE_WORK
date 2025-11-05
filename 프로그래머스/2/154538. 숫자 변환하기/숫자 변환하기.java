import java.util.*;

class Solution {
    public int solution(int x, int y, int n) {
        // 방문 여부를 기록 (중복 계산 방지)
        boolean[] visited = new boolean[y + 1];
        Queue<int[]> q = new LinkedList<>();
        
        // [현재 값, 연산 횟수]
        q.offer(new int[]{x, 0});
        visited[x] = true;
        
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int value = cur[0];
            int count = cur[1];
            
            // 목표 도달 시 반환
            if (value == y) return count;
            
            // 가능한 다음 연산들
            int[] next = {value + n, value * 2, value * 3};
            
            for (int nv : next) {
                if (nv <= y && !visited[nv]) {
                    visited[nv] = true;
                    q.offer(new int[]{nv, count + 1});
                }
            }
        }
        
        // 도달 불가
        return -1;
    }
}
