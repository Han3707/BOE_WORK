class Solution {
    public int solution(int[] schedules, int[][] timelogs, int startday) {
        int answer = 0;
        int size = schedules.length;
        int mytime = 0;
        int[] daytime;  // timelogs[i]를 저장할 변수
        
        for (int i = 0; i < size; i++) {
            mytime = schedules[i] + 10;
            
            // 올바른 비교 연산자 사용
            if ((mytime % 100) >= 60) {
                mytime += 40;
            }
            
            daytime = timelogs[i];
            
            // 시작하는 날 초기화
            int st_day = startday;
            int cnt = 0;
            for (int j = 0; j < 7; j++) {
                int dayweek = st_day % 7;
                if (dayweek != 0 && dayweek != 6){
                // timelogs[j] 대신 daytime[j] 사용
                    if (mytime >= daytime[j]) {
                        cnt++;
                    }
                }
                st_day++; // 다음 요일로 넘어가야 함 (추가 필요)
            }
            
            if (cnt == 5) answer++;
        }
        
        return answer;
    }
}
