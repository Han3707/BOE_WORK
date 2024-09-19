import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Main {

    static int[] W; //무게 배열
    static int[] V; //가치 배열
    static Integer[][] dp; //무게(열)와 물건(행)로 작성했던 그 테이블을 표현한 배열

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); //물건 개수
        int K = Integer.parseInt(st.nextToken()); //배낭에 들어갈 수 있는 최대 무게 값

        W = new int[N];
        V = new int[N];

        dp = new Integer[N][K+1];

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            W[i] = Integer.parseInt(st.nextToken());
            V[i] = Integer.parseInt(st.nextToken());
        }

        System.out.println(knapSack(N-1, K));
    }

    //최대 부분 문자열 길이 구하기(i : 물건 번호, k : 들어갈 최대 무게)
    static int knapSack(int i, int k){

        //물건 번호가 범위 밖일 때
        if(i < 0)
            return 0;

        if(dp[i][k] == null){ //탐색하지 않았다면,

            //현재 물건 i를 추가로 못담는 경우(무게 초과일 경우)
            if(W[i] > k) {
                //이전 값을 넣는다.
                dp[i][k] = knapSack(i - 1, k);
            }
            else{ // 현재 물건 i을 담을 수 았는 경우
                // 이전 i값과 이전 i값에 대해 가치 값 + 현재 물건 i의 가치 값 중 큰 값을 저장
                dp[i][k] = Math.max(knapSack(i-1, k), knapSack(i-1, k-W[i]) + V[i]);
            }
        }

        return dp[i][k];
    }
}