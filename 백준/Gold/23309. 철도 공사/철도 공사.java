import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int[] prevStation = new int[1000001];
    static int[] nextStation = new int[1000001];
    static int[] Station;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        Station = new int[N];  // 역의 개수만큼 배열 생성

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            Station[i] = Integer.parseInt(st.nextToken());
        }

        // 첫 역과 마지막 역의 전후 관계 설정
        prevStation[Station[0]] = Station[N - 1];
        nextStation[Station[0]] = Station[1];
        prevStation[Station[N - 1]] = Station[N - 2];
        nextStation[Station[N - 1]] = Station[0];

        // 중간 역들의 전후 관계 설정
        for (int i = 1; i < N - 1; i++) {
            prevStation[Station[i]] = Station[i - 1];
            nextStation[Station[i]] = Station[i + 1];
        }

        // 명령어 처리
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            String command = st.nextToken();
            int x = Integer.parseInt(st.nextToken());

            switch (command) {
                case "BN":
                    int y = Integer.parseInt(st.nextToken());
                    sb.append(nextStation[x]).append("\n");
                    BN(x, y);
                    break;
                case "BP":
                    y = Integer.parseInt(st.nextToken());
                    sb.append(prevStation[x]).append("\n");
                    BP(x, y);
                    break;
                case "CN":
                    sb.append(nextStation[x]).append("\n");
                    CN(x);
                    break;
                case "CP":
                    sb.append(prevStation[x]).append("\n");
                    CP(x);
                    break;
            }
        }

        System.out.println(sb);
    }

    static void BN(int x, int y) {
        int nextX = nextStation[x];
        nextStation[x] = y;
        prevStation[y] = x;
        nextStation[y] = nextX;
        prevStation[nextX] = y;
    }

    static void BP(int x, int y) {
        int prevX = prevStation[x];
        nextStation[prevX] = y;
        prevStation[y] = prevX;
        nextStation[y] = x;
        prevStation[x] = y;
    }

    static void CN(int x) {
        int temp = nextStation[x];
        int tempNext = nextStation[temp];

        nextStation[x] = tempNext;
        prevStation[tempNext] = x;
    }

    static void CP(int x) {
        int temp = prevStation[x];
        int tempPrev = prevStation[temp];

        prevStation[x] = tempPrev;
        nextStation[tempPrev] = x;
    }
}
