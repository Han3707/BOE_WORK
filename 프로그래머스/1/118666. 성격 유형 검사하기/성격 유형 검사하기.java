import java.util.*;

class Solution {
    public String solution(String[] survey, int[] choices) {
        // 각 성격 유형의 점수를 저장할 HashMap 초기화
        Map<Character, Integer> scoreMap = new HashMap<>();
        
        // 초기 점수를 0으로 설정 (문제 조건상 명시되지 않았지만 안전을 위해)
        char[] types = {'R', 'T', 'C', 'F', 'J', 'M', 'A', 'N'};
        for (char type : types) {
            scoreMap.put(type, 0);
        }

        // 질문과 선택지를 순회하며 점수 계산
        for (int i = 0; i < survey.length; i++) {
            String typePair = survey[i];
            int choice = choices[i];

            // '모르겠음' (4) 선택 시 점수 변동 없음
            if (choice == 4) {
                continue;
            }

            char type1 = typePair.charAt(0); // 비동의 관련 유형
            char type2 = typePair.charAt(1); // 동의 관련 유형

            int score = Math.abs(choice - 4); // 1,2,3 -> 3,2,1점 / 5,6,7 -> 1,2,3점

            if (choice < 4) { // 비동의 관련 (1, 2, 3)
                scoreMap.put(type1, scoreMap.get(type1) + score);
            } else { // 동의 관련 (5, 6, 7)
                scoreMap.put(type2, scoreMap.get(type2) + score);
            }
        }

        // 최종 성격 유형 결정
        StringBuilder answer = new StringBuilder();

        // 1번 지표: 라이언형(R) vs 튜브형(T)
        if (scoreMap.get('R') >= scoreMap.get('T')) {
            answer.append('R');
        } else {
            answer.append('T');
        }

        // 2번 지표: 콘형(C) vs 프로도형(F)
        if (scoreMap.get('C') >= scoreMap.get('F')) {
            answer.append('C');
        } else {
            answer.append('F');
        }

        // 3번 지표: 제이지형(J) vs 무지형(M)
        if (scoreMap.get('J') >= scoreMap.get('M')) {
            answer.append('J');
        } else {
            answer.append('M');
        }

        // 4번 지표: 어피치형(A) vs 네오형(N)
        if (scoreMap.get('A') >= scoreMap.get('N')) {
            answer.append('A');
        } else {
            answer.append('N');
        }

        return answer.toString();
    }
}