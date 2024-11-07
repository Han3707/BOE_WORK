import java.io.*;
import java.util.*;

public class Solution {
    static int t, n;
    static int[][] map;
    static int minDiff;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        t = Integer.parseInt(br.readLine());
        StringTokenizer st;

        for (int tc = 1; tc <= t; tc++) {
            n = Integer.parseInt(br.readLine());
            map = new int[n][n];
            minDiff = Integer.MAX_VALUE;

            // 시너지 맵 입력
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < n; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            // 조합 생성 및 최소 차이 계산
            generateGroups(new ArrayList<>(), 0, n / 2);
            System.out.println("#" + tc + " " + minDiff);
        }
    }

    // group1을 선택해두고 나머지를 group2로 계산
    static void generateGroups(List<Integer> group1, int start, int r) {
        if (group1.size() == r) {
            calculateMinDifference(group1);
            return;
        }

        for (int i = start; i < n; i++) {
            group1.add(i);
            generateGroups(group1, i + 1, r);
            group1.remove(group1.size() - 1);
        }
    }

    // 두 그룹의 시너지 합 차이 계산
    static void calculateMinDifference(List<Integer> group1) {
        List<Integer> group2 = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (!group1.contains(i)) group2.add(i);
        }

        int synergy1 = calculateSynergy(group1);
        int synergy2 = calculateSynergy(group2);
        minDiff = Math.min(minDiff, Math.abs(synergy1 - synergy2));
    }

    // 주어진 그룹의 시너지 합 계산
    static int calculateSynergy(List<Integer> group) {
        int sum = 0;
        for (int i = 0; i < group.size(); i++) {
            for (int j = i + 1; j < group.size(); j++) {
                sum += map[group.get(i)][group.get(j)] + map[group.get(j)][group.get(i)];
            }
        }
        return sum;
    }
}
