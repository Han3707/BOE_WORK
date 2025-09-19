import java.util.*;

class Solution {
    public String solution(String s, String skip, int index) {
        // 1) 알파벳에서 skip 문자 제거
        boolean[] ban = new boolean[26];
        for (int i = 0; i < skip.length(); i++) {
            ban[skip.charAt(i) - 'a'] = true;
        }

        StringBuilder dict = new StringBuilder(); // 사용 가능한 알파벳 목록
        for (char c = 'a'; c <= 'z'; c++) {
            if (!ban[c - 'a']) dict.append(c);
        }

        // 2) 변환
        StringBuilder ans = new StringBuilder();
        int L = dict.length(); // 26 - skip.length()
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            int pos = dict.indexOf(String.valueOf(ch)); // 현재 문자의 위치
            int next = (pos + index) % L;               // index만큼 이동 + 순환
            ans.append(dict.charAt(next));
        }

        return ans.toString();
    }
}
