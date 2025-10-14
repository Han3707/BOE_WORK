import java.util.*;

class Solution {
    public String solution(String s) {
        // 1. 문자열을 문자 배열로 변환
        char[] arr = s.toCharArray();
        
        // 2. 오름차순 정렬 후
        Arrays.sort(arr);
        
        // 3. 뒤집어서 내림차순으로 만들기
        StringBuilder sb = new StringBuilder(new String(arr));
        return sb.reverse().toString();
    }
}
