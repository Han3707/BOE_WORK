import java.util.*;

class Solution {
    public int solution(String s) {
        int n = s.length();
        int ans = 0;
        
        for (int i = 0; i < n; i++) {
            String r = s.substring(i) + s.substring(0, i);
            if (isValid(r)) ans++;
        }
        return ans;
    }

    private boolean isValid(String str) {
        Deque<Character> dq = new ArrayDeque<>();
        for (char c : str.toCharArray()) {
            if (c == '(' || c == '[' || c == '{') {
                dq.addLast(c); // push 역할
            } else {
                if (dq.isEmpty()) return false;
                char top = dq.removeLast(); // pop 역할
                if ((c == ')' && top != '(') ||
                    (c == ']' && top != '[') ||
                    (c == '}' && top != '{')) {
                    return false;
                }
            }
        }
        return dq.isEmpty();
    }
}