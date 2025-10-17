class Solution {
    public String solution(String s) {
        String answer = "";
        int mid = s.length() / 2;
        
        if (s.length() % 2 == 1) { // 홀수
            answer += s.charAt(mid);
        } else { // 짝수
            answer += s.charAt(mid - 1);
            answer += s.charAt(mid);
        }
        
        return answer;
    }
}
