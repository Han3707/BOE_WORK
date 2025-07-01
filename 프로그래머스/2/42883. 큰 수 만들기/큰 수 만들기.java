class Solution {
    public String solution(String number, int k) {
        StringBuilder answer = new StringBuilder(); // 결과를 저장할 StringBuilder

        for (int i = 0; i < number.length(); i++) {
            char currentChar = number.charAt(i); // 현재 숫자 문자

            //k가 남아있고, answer가 비어있지 않고, answer의 마지막 문자가 현재 문자보다 작다면
            while (answer.length() > 0 && k > 0 && answer.charAt(answer.length() - 1) < currentChar) {
                answer.deleteCharAt(answer.length() - 1); // 마지막 문자 제거
                k--; // 제거 횟수 감소
            }

            //anwser 에 있던 숫자보다 cur의 현재 숫자가 더 크니깐 현재 숫자를 추가(위에서 과거 숫자 제거함)
            answer.append(currentChar);
        }

        // 아직 제거해야하는 숫자가 남아있다면 맨 마지막 수를 제거
        while (k > 0) {
            answer.deleteCharAt(answer.length() - 1);
            k--;
        }

        return answer.toString();
    }
}