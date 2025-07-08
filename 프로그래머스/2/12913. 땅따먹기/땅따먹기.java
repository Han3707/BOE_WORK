class Solution {
    public int solution(int[][] land) {
        int n = land.length;
        
        // 핵심 아이디어: 각 행에서 "지금까지의 최대 점수"를 저장
        // dp[i][j] = i행에서 j열을 선택했을 때의 최대 점수
        int[][] dp = new int[n][4];
        
        // 1단계: 첫 번째 행은 그대로 복사 (선택의 여지가 없음)
        for (int j = 0; j < 4; j++) {
            dp[0][j] = land[0][j];
        }
        
        // 2단계: 두 번째 행부터 계산
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < 4; j++) {
                // 현재 칸 (i, j)를 선택한다면?
                // 이전 행에서 j가 아닌 다른 열들 중 최대값을 가져와야 함
                
                int maxFromPrevRow = 0;
                for (int k = 0; k < 4; k++) {
                    if (k != j) { // 같은 열은 연속으로 선택 불가
                        maxFromPrevRow = Math.max(maxFromPrevRow, dp[i-1][k]);
                    }
                }
                
                // 현재 칸의 점수 + 이전 행에서 올 수 있는 최대 점수
                dp[i][j] = land[i][j] + maxFromPrevRow;
            }
        }
        
        // 3단계: 마지막 행에서 최대값 찾기
        int answer = 0;
        for (int j = 0; j < 4; j++) {
            answer = Math.max(answer, dp[n-1][j]);
        }
        
        return answer;
    }
    
}