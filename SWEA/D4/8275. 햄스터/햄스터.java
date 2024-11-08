import java.io.*;
import java.util.*;

class Pos {
    int l;
    int r;
    int cost;

    public Pos(int l, int r, int cost) {
        this.l = l;
        this.r = r;
        this.cost = cost;
    }
}

public class Solution {
    static int N, X, M, left, right, sum_ham;
    static int[] num;
    static List<Pos> list;
    static List<int[]> res;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int t = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= t; tc++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken()); // 햄스터 수
            X = Integer.parseInt(st.nextToken()); // 각 칸 최대 수용 햄스터 수
            M = Integer.parseInt(st.nextToken()); // 조건 수

            list = new ArrayList<>();
            num = new int[N + 1]; // 햄스터 배열 (1-index 사용)
            res = new ArrayList<>();

            // 조건 입력 받기
            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                left = Integer.parseInt(st.nextToken());
                right = Integer.parseInt(st.nextToken());
                sum_ham = Integer.parseInt(st.nextToken());
                list.add(new Pos(left, right, sum_ham));
            }

            // 조합 탐색 시작
            Combination(1);

            // 가능한 경우가 없는 경우 처리
            if (res.isEmpty()) {
                System.out.println("#" + tc + " -1");
            } else {
                // 정렬 기준 추가: 1. 총합이 큰 순서 2. 사전 순
                Collections.sort(res, (a, b) -> {
                    // 1. 총합 비교
                    int sumA = Arrays.stream(a).sum();
                    int sumB = Arrays.stream(b).sum();
                    if (sumA != sumB) return Integer.compare(sumB, sumA); // 총합이 큰 순서로 정렬

                    // 2. 사전 순 비교 (총합이 같을 때)
                    for (int i = 1; i <= N; i++) {
                        if (a[i] != b[i]) return Integer.compare(a[i], b[i]);
                    }
                    return 0;
                });

                int[] ans = res.get(0);
                System.out.print("#" + tc);
                for (int i = 1; i <= N; i++) { // 1부터 N까지 출력
                    System.out.print(" " + ans[i]);
                }
                System.out.println();
            }
        }
    }

    // 조합 생성 함수
    static void Combination(int depth) {
        if (depth == N + 1) { // 모든 칸을 다 채운 경우
            if (checkConditions()) {
                res.add(num.clone()); // 복사본 추가
            }
            return;
        }

        // 0부터 X까지 가능한 모든 햄스터 수 배치
        for (int i = 0; i <= X; i++) {
            num[depth] = i;
            Combination(depth + 1);
        }
    }

    // 조건 체크 함수
    static boolean checkConditions() {
        for (Pos p : list) {
            int s = 0;
            for (int i = p.l; i <= p.r; i++) {
                s += num[i];
            }
            if (s != p.cost) return false; // 하나라도 조건을 만족하지 않으면 false
        }
        return true;
    }
}
