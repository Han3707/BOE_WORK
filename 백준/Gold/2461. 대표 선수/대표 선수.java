import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // 반의 수
        int M = Integer.parseInt(st.nextToken()); // 각 반의 학생 수

        int[][] scores = new int[N][M]; // 각 반의 점수를 저장하는 배열

        // 각 반의 점수를 입력받고 오름차순으로 정렬
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                scores[i][j] = Integer.parseInt(st.nextToken());
            }
            Arrays.sort(scores[i]); // 각 반의 점수를 오름차순으로 정렬
        }

        // 우선순위 큐를 이용해 최소값 관리
        PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> a.value - b.value);

        int maxValue = Integer.MIN_VALUE; // 현재 선택된 값들 중 가장 큰 값
        // 각 반의 첫 번째(최소값) 요소를 우선순위 큐에 삽입
        for (int i = 0; i < N; i++) {
            pq.offer(new Node(i, 0, scores[i][0]));
            maxValue = Math.max(maxValue, scores[i][0]); // 최대값 업데이트
        }

        int result = Integer.MAX_VALUE;

        // 우선순위 큐가 비지 않을 때까지
        while (!pq.isEmpty()) {
            Node current = pq.poll(); // 가장 작은 값을 가진 반의 정보를 꺼냄
            int minValue = current.value; // 가장 작은 값
            result = Math.min(result, maxValue - minValue); // 현재의 최소-최대 차이로 결과 갱신

            // 해당 반에서 더 이상 값을 뽑을 수 없으면 종료
            if (current.index + 1 >= M) {
                break;
            }

            // 해당 반에서 다음 학생의 점수를 우선순위 큐에 삽입
            int nextValue = scores[current.row][current.index + 1];
            pq.offer(new Node(current.row, current.index + 1, nextValue));
            maxValue = Math.max(maxValue, nextValue); // 최대값을 업데이트
        }

        System.out.println(result); // 결과 출력
    }

    // 반과 인덱스, 값을 저장하는 Node 클래스
    static class Node {
        int row;    // 반의 번호
        int index;  // 해당 반에서 몇 번째 학생인가
        int value;  // 그 학생의 점수

        public Node(int row, int index, int value) {
            this.row = row;
            this.index = index;
            this.value = value;
        }
    }
}
