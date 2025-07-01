class Solution {
    public int solution(int n, int m, int[] section) {
        int paintCount = 0; // 페인트칠 횟수
        int lastPainted = 0; // 마지막으로 페인트칠한 구역의 끝 번호

        // section 배열을 순회하며 페인트칠 필요 구역 확인
        for (int i = 0; i < section.length; i++) {
            // 현재 구역(section[i])이 이전에 칠했던 범위(lastPainted) 내에 있다면
            // 이미 칠해졌으므로 다음 구역으로 넘어갑니다.
            if (section[i] < lastPainted) {
                continue;
            }

            // 현재 구역이 이전에 칠했던 범위 밖에 있다면, 새로 칠해야 합니다.
            // 롤러를 현재 구역(section[i])부터 시작하여 m 길이만큼 칠합니다.
            paintCount++;
            // lastPainted를 현재 롤러가 칠한 범위의 끝 번호로 업데이트합니다.
            // section[i]에서 시작하여 m 길이이므로, 끝 번호는 section[i] + m - 1이 됩니다.
            lastPainted = section[i] + m; 
        }

        return paintCount;
    }
}