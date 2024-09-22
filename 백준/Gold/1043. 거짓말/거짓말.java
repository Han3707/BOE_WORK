import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static List<Integer> Tru; // 진실을 아는 사람 목록
    static List<List<Integer>> parties; // 각 파티에 참여하는 사람 목록
    static boolean[] knowsTruth; // 각 사람이 진실을 아는지 여부

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        Tru = new ArrayList<>();
        parties = new ArrayList<>();
        
        N = Integer.parseInt(st.nextToken()); // 사람의 수
        M = Integer.parseInt(st.nextToken()); // 파티의 수
        knowsTruth = new boolean[N + 1];

        // 진실을 아는 사람 입력받기
        st = new StringTokenizer(br.readLine());
        int num = Integer.parseInt(st.nextToken());
        for (int i = 0; i < num; i++) {
            int a = Integer.parseInt(st.nextToken());
            Tru.add(a);
            knowsTruth[a] = true; // 진실을 아는 사람 표시
        }

        // 파티 정보 입력받기
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int partySize = Integer.parseInt(st.nextToken());
            List<Integer> party = new ArrayList<>();
            for (int j = 0; j < partySize; j++) {
                party.add(Integer.parseInt(st.nextToken()));
            }
            parties.add(party);
        }

        // 진실을 아는 사람 전파 (파티 수 만큼 반복해서 전파)
        for (int i = 0; i < M; i++) {
            for (List<Integer> party : parties) {
                boolean hasTruth = false;
                // 현재 파티에 진실을 아는 사람이 있는지 확인
                for (int person : party) {
                    if (knowsTruth[person]) {
                        hasTruth = true;
                        break;
                    }
                }
                // 진실을 아는 사람이 있으면 파티의 모든 사람에게 전파
                if (hasTruth) {
                    for (int person : party) {
                        knowsTruth[person] = true;
                    }
                }
            }
        }

        // 과장된 이야기를 할 수 있는 파티의 수를 카운트
        int count = 0;
        for (List<Integer> party : parties) {
            boolean canLie = true;
            // 진실을 아는 사람이 있는지 확인
            for (int person : party) {
                if (knowsTruth[person]) {
                    canLie = false;
                    break;
                }
            }
            if (canLie) count++;
        }

        System.out.println(count);
    }
}
