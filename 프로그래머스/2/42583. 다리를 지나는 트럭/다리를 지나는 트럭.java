import java.util.Deque;
import java.util.LinkedList; // LinkedList는 Deque 인터페이스를 구현합니다.

class Solution {
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        // bridge: 다리 위 트럭들을 나타내는 덱. 각 요소는 해당 칸의 트럭 무게(0은 빈 공간).
        Deque<Integer> bridge = new LinkedList<>();

        int time = 0; // 경과 시간
        int currentBridgeWeight = 0; // 현재 다리 위에 있는 트럭들의 총 무게
        int truckIndex = 0; // 다음에 다리에 진입할 트럭의 인덱스

        // 다리를 초기화합니다: 다리 길이만큼 빈 공간(0)으로 채웁니다.
        for (int i = 0; i < bridge_length; i++) {
            bridge.offerLast(0); // 덱의 맨 뒤에 빈 공간 추가
        }

        // 다리 위에 트럭이 남아있거나, 대기 중인 트럭이 남아있을 때까지 시뮬레이션을 반복합니다.
        // 하지만 모든 트럭이 다리를 건너고 bridge 덱이 비어질 때까지 반복하는 것이 가장 확실합니다.
        while (!bridge.isEmpty()) {
            time++; // 1초 경과

            // 1. 다리 맨 앞에서 요소 제거 (가장 앞에 있던 트럭 또는 빈 공간이 다리를 건넘)
            // 제거된 요소의 무게만큼 현재 다리 총 무게에서 빼줍니다.
            currentBridgeWeight -= bridge.pollFirst();

            // 2. 새로운 트럭 다리에 진입 시도 (아직 대기 중인 트럭이 있다면)
            if (truckIndex < truck_weights.length) {
                int nextTruckWeight = truck_weights[truckIndex];

                // 현재 다리 무게 + 다음 트럭 무게가 다리의 최대 허용 무게를 초과하는지 확인
                if (currentBridgeWeight + nextTruckWeight <= weight) {
                    // 초과하지 않으면, 다음 트럭을 다리에 올립니다 (덱의 맨 뒤에 추가).
                    bridge.offerLast(nextTruckWeight);
                    currentBridgeWeight += nextTruckWeight; // 다리 총 무게에 추가
                    truckIndex++; // 다음 대기 트럭으로 인덱스 이동
                } else {
                    // 무게 초과로 트럭이 올라갈 수 없으므로, 해당 초에는 다리 입구에 빈 공간(0)을 추가합니다.
                    // 이는 다리 위의 기존 트럭들이 전진했지만, 새로운 트럭은 들어오지 못했음을 의미합니다.
                    bridge.offerLast(0);
                }
            }
            // 모든 트럭이 다리에 진입 시도를 마쳤다면, 더 이상 새로운 트럭을 추가하지 않고
            // 다리 위의 기존 트럭들만 계속해서 이동시킵니다.
        }

        return time; // 최종 경과 시간 반환
    }
}