class Solution {
    // 키 -> 좌표 (row, col)
    private int[] coord(int key) {
        if (key == 0) return new int[]{3, 1};      // 0
        if (key == -1) return new int[]{3, 0};     // * (왼손 시작)
        if (key == -2) return new int[]{3, 2};     // # (오른손 시작)
        // 1~9
        return new int[]{(key - 1) / 3, (key - 1) % 3};
    }

    private int dist(int[] a, int[] b) {
        return Math.abs(a[0] - b[0]) + Math.abs(a[1] - b[1]);  // 맨해튼 거리
    }

    public String solution(int[] numbers, String hand) {
        StringBuilder sb = new StringBuilder();
        int leftKey = -1;   // * 에서 시작
        int rightKey = -2;  // # 에서 시작
        boolean rightFav = hand.equals("right");

        for (int num : numbers) {
            if (num == 1 || num == 4 || num == 7) {
                sb.append('L');
                leftKey = num;
            } else if (num == 3 || num == 6 || num == 9) {
                sb.append('R');
                rightKey = num;
            } else {
                int dl = dist(coord(leftKey), coord(num));
                int dr = dist(coord(rightKey), coord(num));
                if (dl < dr || (dl == dr && !rightFav)) {
                    sb.append('L');
                    leftKey = num;
                } else {
                    sb.append('R');
                    rightKey = num;
                }
            }
        }
        return sb.toString();
    }
}
