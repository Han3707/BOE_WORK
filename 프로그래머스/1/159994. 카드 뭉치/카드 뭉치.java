import java.util.*;

public class Solution {
    public String solution(String[] cards1, String[] cards2, String[] goal) {
        Deque<String> deque1 = new ArrayDeque<>(Arrays.asList(cards1));
        Deque<String> deque2 = new ArrayDeque<>(Arrays.asList(cards2));

        for (String word : goal) {
            if (!deque1.isEmpty() && deque1.peekFirst().equals(word)) {
                deque1.removeFirst(); // cards1에서 단어 사용
            } else if (!deque2.isEmpty() && deque2.peekFirst().equals(word)) {
                deque2.removeFirst(); // cards2에서 단어 사용
            } else {
                return "No"; // 둘 다에서 해당 단어를 꺼낼 수 없음
            }
        }

        return "Yes"; // 모든 단어를 순서대로 꺼낼 수 있었음
    }
}