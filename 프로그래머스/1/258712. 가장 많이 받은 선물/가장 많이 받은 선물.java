import java.util.*;

class Solution {
    public int solution(String[] friends, String[] gifts) {
        // 1. 친구 이름을 정수 인덱스로 매핑
        Map<String, Integer> fMap = new HashMap<>(); // 친구 이름 -> 인덱스
        for (int i = 0; i < friends.length; i++) {
            fMap.put(friends[i], i);
        }

        int N = friends.length;
        // 2. 선물 기록 저장 (giverIndex -> receiverIndex)
        int[][] records = new int[N][N]; // records[i][j]는 i가 j에게 준 선물 수

        // 3. 선물 지수 계산 (준 선물 - 받은 선물)
        int[] scores = new int[N]; // scores[i]는 i의 선물 지수

        // gifts 배열을 순회하며 records와 scores 업데이트
        for (String gift : gifts) {
            String[] parts = gift.split(" ");
            String giver = parts[0];
            String receiver = parts[1];

            int giverIdx = fMap.get(giver);
            int receiverIdx = fMap.get(receiver);

            records[giverIdx][receiverIdx]++; // 준 선물 기록
            scores[giverIdx]++; // 준 선물에 대한 지수 증가
            scores[receiverIdx]--; // 받은 선물에 대한 지수 감소
        }

        // 4. 다음 달 예상 선물 수 계산
        int[] nextGifts = new int[N]; // nextGifts[i]는 i가 다음 달에 받을 선물 수

        // 모든 친구 쌍에 대해 계산 (중복 방지를 위해 i < j)
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                int iToJ = records[i][j]; // i가 j에게 준 선물
                int jToI = records[j][i]; // j가 i에게 준 선물

                if (iToJ > jToI) { // i가 j보다 선물을 더 많이 줬다면
                    nextGifts[i]++;
                } else if (jToI > iToJ) { // j가 i보다 선물을 더 많이 줬다면
                    nextGifts[j]++;
                } else { // 주고받은 기록이 없거나 같다면 선물 지수로 비교
                    if (scores[i] > scores[j]) { // i의 선물 지수가 더 크다면
                        nextGifts[i]++;
                    } else if (scores[j] > scores[i]) { // j의 선물 지수가 더 크다면
                        nextGifts[j]++;
                    }
                    // 선물 지수도 같으면 아무도 받지 않음
                }
            }
        }

        // 5. 가장 많은 선물을 받을 친구가 받을 선물의 수 반환
        int maxGifts = 0;
        for (int giftsCount : nextGifts) {
            if (giftsCount > maxGifts) {
                maxGifts = giftsCount;
            }
        }

        return maxGifts;
    }
}