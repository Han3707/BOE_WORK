import java.util.*;

class Solution {
    public int solution(int[][] jobs) {
        // 작업을 요청 시간 순으로 정렬
        Arrays.sort(jobs, (a, b) -> Integer.compare(a[0], b[0]));
        
        // 소요시간 순으로 정렬
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(a[1], b[1]));
        
        int currentTime = 0;  // 현재 시간
        int jobIndex = 0;     // 다음에 처리할 작업의 인덱스
        int totalTime = 0;    // 총 대기시간 + 처리시간
        int completedJobs = 0; // 완료된 작업 수
        
        while (completedJobs < jobs.length) {
            // 현재 시간에 요청된 모든 작업을 우선순위 큐에 추가
            while (jobIndex < jobs.length && jobs[jobIndex][0] <= currentTime) {
                pq.offer(jobs[jobIndex]);
                jobIndex++;
            }
            
            if (!pq.isEmpty()) {
                // 소요시간이 가장 짧은 작업을 선택
                int[] currentJob = pq.poll();
                int requestTime = currentJob[0];
                int duration = currentJob[1];
                
                // 작업 완료 시간 업데이트
                currentTime += duration;
                
                // 응답시간 = 작업 완료 시간 - 작업 요청 시간
                totalTime += (currentTime - requestTime);
                completedJobs++;
            } else {
                // 처리할 작업이 없으면 다음 작업의 요청 시간으로 이동
                if (jobIndex < jobs.length) {
                    currentTime = jobs[jobIndex][0];
                }
            }
        }
        
        // 평균 시간 반환 (소수점 이하 버림)
        return totalTime / jobs.length;
    }
}