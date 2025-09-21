import java.util.*;

class Solution {
    public int solution(int[] mats, String[][] park) {
        int r = park.length;
        int c = park[0].length;

        Arrays.sort(mats); // 오름차순 → 큰 것부터 보려고 뒤에서부터
        for (int i = mats.length - 1; i >= 0; i--) {
            int size = mats[i];
            if (size > r || size > c) continue; // 아예 불가

            for (int x = 0; x <= r - size; x++) {
                for (int y = 0; y <= c - size; y++) {
                    // ✅ 가드: 좌상단이 -1이 아니면 후보 아님
                    if (!"-1".equals(park[x][y])) continue;

                    if (isAllEmpty(park, x, y, size)) {
                        return size;
                    }
                }
            }
        }
        return -1;
    }

    // (x,y) ~ (x+size-1, y+size-1)가 전부 "-1"인지 확인
    private boolean isAllEmpty(String[][] park, int x, int y, int size) {
        for (int i = x; i < x + size; i++) {
            for (int j = y; j < y + size; j++) {
                if (!"-1".equals(park[i][j])) return false;
            }
        }
        return true;
    }
}
