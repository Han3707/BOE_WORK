class Solution {
    public int solution(int num) {
        long n = num;       // 연산은 long으로
        int cnt = 0;

        // 500번 시도
        while (n != 1 && cnt < 500) {
            if (n % 2 == 0) n /= 2;
            else n = n * 3 + 1;
            cnt++;
        }

        // 500번 안에 1이면 횟수, 아니면 -1
        return (n == 1) ? cnt : -1;
    }
}
