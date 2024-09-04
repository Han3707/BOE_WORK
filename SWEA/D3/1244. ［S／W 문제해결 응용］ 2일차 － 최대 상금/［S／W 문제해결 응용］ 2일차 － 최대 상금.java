import java.util.*;
import java.io.*;

class Solution {
    static char[] nums;
    static int limitCount;
    static int result;

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String str = st.nextToken();
            nums = new char[str.length()];  // nums 배열을 문자열 길이로 초기화
            for (int i = 0; i < str.length(); i++) {
                nums[i] = str.charAt(i);
            }
            limitCount = Integer.parseInt(st.nextToken());
            result = 0;

            if (limitCount > nums.length) {
                limitCount = nums.length;
            }

            dfs(0, 0);
            System.out.println("#" + tc + " " + result);
        }
    }

    private static void dfs(int pos, int count) {
        if (count == limitCount) {
            int value = Integer.parseInt(new String(nums));
            result = Math.max(result, value);
            return;
        }

        for (int i = pos; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                swap(i, j);
                dfs(i, count + 1);
                swap(i, j);
            }
        }
    }

    private static void swap(int i, int j) {
        char tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
