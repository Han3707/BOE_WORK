import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[] population;
    static List<Integer>[] adj;
    static boolean[] selected;
    static int minDiff = Integer.MAX_VALUE;
    static boolean isPossible = false; // 두 그룹을 만들 수 있는지 여부를 저장

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine()); // 구역의 수
        population = new int[N + 1];
        adj = new ArrayList[N + 1];
        selected = new boolean[N + 1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            population[i] = Integer.parseInt(st.nextToken()); // 각 구역의 인구 수
            adj[i] = new ArrayList<>();
        }

        // 각 구역의 인접 구역 정보 읽기
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int count = Integer.parseInt(st.nextToken());
            for (int j = 0; j < count; j++) {
                adj[i].add(Integer.parseInt(st.nextToken()));
            }
        }

        findMinDiff(1); // 조합 탐색 시작
        
        // 두 그룹을 만들 수 있다면 최소 인구수 차이 출력, 그렇지 않으면 -1 출력
        System.out.println(isPossible ? minDiff : -1);
    }

    // 구역을 선택하여 최소 인구수 차이를 찾는 메서드
    static void findMinDiff(int index) {
        if (index == N + 1) {
            calculateDifference();
            return;
        }
        selected[index] = true;
        findMinDiff(index + 1);
        selected[index] = false;
        findMinDiff(index + 1);
    }

    // 선택된 구역들의 인구수 차이 계산
    static void calculateDifference() {
        List<Integer> groupA = new ArrayList<>();
        List<Integer> groupB = new ArrayList<>();
        int populationA = 0, populationB = 0;

        for (int i = 1; i <= N; i++) {
            if (selected[i]) {
                groupA.add(i);
                populationA += population[i];
            } else {
                groupB.add(i);
                populationB += population[i];
            }
        }

        // 두 그룹이 모두 비어있지 않고, 각각 연결되어 있는 경우만 인구수 차이를 계산
        if (!groupA.isEmpty() && !groupB.isEmpty() && isConnected(groupA) && isConnected(groupB)) {
            isPossible = true; // 두 그룹을 만들 수 있음을 표시
            minDiff = Math.min(minDiff, Math.abs(populationA - populationB));
        }
    }

    // 연결성 검사 메서드 (BFS 사용)
    static boolean isConnected(List<Integer> group) {
        boolean[] visited = new boolean[N + 1];
        Queue<Integer> queue = new LinkedList<>();
        queue.add(group.get(0));
        visited[group.get(0)] = true;
        int count = 1;

        while (!queue.isEmpty()) {
            int node = queue.poll();
            for (int neighbor : adj[node]) {
                if (group.contains(neighbor) && !visited[neighbor]) {
                    visited[neighbor] = true;
                    queue.add(neighbor);
                    count++;
                }
            }
        }

        return count == group.size();
    }
}
