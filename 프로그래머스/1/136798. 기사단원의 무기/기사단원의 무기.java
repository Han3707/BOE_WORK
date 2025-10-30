import java.util.*;

class Solution {
    public int solution(int number, int limit, int power) {
        int total = 0;

        for (int i = 1; i <= number; i++) {
            int cnt = divisorCount(i);
            total += (cnt > limit) ? power : cnt;
        }
        return total;
    }

    // n의 약수 개수: 1~ /n까지만 확인, 짝 약수까지 한 번에 더함
    private int divisorCount(int n) {
        int root = (int) Math.sqrt(n);
        int cnt = 0;
        for (int d = 1; d <= root; d++) {
            if (n % d == 0) cnt += 2;   // d, n/d
        }
        if (root * root == n) cnt--;    // 제곱수면 중복 하나 제거
        return cnt;
    }
}
