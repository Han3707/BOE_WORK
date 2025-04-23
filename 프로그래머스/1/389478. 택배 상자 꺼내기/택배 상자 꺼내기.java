class Solution {
    public int solution(int n, int w, int num) {
        int answer = 0;

        int h = (n + w - 1) / w; // 총 층 수 (ceil)

        int[][] arr = new int[h][w];

        int cnt = 1;
        for (int i = 0; i < h; i++) {
            if (i % 2 == 0) {
                for (int j = 0; j < w; j++) {
                    if (cnt > n) break;
                    arr[i][j] = cnt++;
                }
            } else {
                for (int j = w - 1; j >= 0; j--) {
                    if (cnt > n) break;
                    arr[i][j] = cnt++;
                }
            }
        }

        int targetX = 0;
        int targetY = 0;
        boolean found = false;
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                if (arr[i][j] == num) {
                    targetX = i;
                    targetY = j;
                    found = true;
                    break;
                }
            }
            if (found) break;
        }

        for (int i = h - 1; i >= targetX; i--) {
            if (arr[i][targetY] != 0) {
                answer++;
            }
        }

        return answer;
    }
}
