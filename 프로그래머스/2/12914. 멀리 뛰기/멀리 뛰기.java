class Solution {
    public int solution(int n) {
        final int MOD = 1_234_567;

        int[] f = new int[n+2]; 
        f[0] = 1;
        f[1] = 1;
        f[2] = 2;
        
        for (int i = 2; i <= n; i++) {
            f[i] = (f[i-1] + f[i-2]) % MOD;
        }
        return f[n];
    }
}
