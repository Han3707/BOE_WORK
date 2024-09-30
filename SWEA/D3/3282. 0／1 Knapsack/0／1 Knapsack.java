import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 1/0 Knapsack 문제 
// 한번만 선택가능한 문제
// 가방에 물건이 들어가는 경우와 들어가지 않는 경우로 나뉨
// 물건이 들어가는 경우 dp[i][j] 는 dp[i][j-1] 기존값과 dp[i-w[j]][j-1] + v[j] 값 중 최대값 
// 물건이 들어가지 않는 경우 dp[i][j] 는 dp[i][j-1]

// 물건을 반복선택 가능한 Unbounded Knapsack 에서는 순서가 의미없어지기 때문에 
// 물건이 들어가는 경우 dp[i] 는 dp[i] 와 dp[i-w[j]] + v[j] 값중 최대값

public class Solution {
	
	static int N;	//물건개수
	static int K;	//가방크기
	static int[] w; //무게
	static int[] v; //가치
	static int[][] dp; //크기,순서
	
	public static void test(int testNo) {
		
		//Knapsack 문제를 해결하기 위해서 dp 를 사용한다. 
		//dp[i][j] 는 i 크기의 가방에 j 번째 물건을 넣을 때의 가치이다.
		dp = new int[K+1][N+1];
		
		//가방 크기가 K 일때까지 증가
		for(int i = 1; i <= K; i++)
		{
			//물건이 N번째까지 증가
			for(int j = 1; j <= N; j++)
			{
				//물건의 무게가 i보다 작거나 같은 경우 담을 수 있다. 
				if(w[j] <= i)
				{
					//j번째 물건의 무게를 뺀 가방의 최대가치 + v[j] 와 j-1 번째 물건까지 넣은 상태의 가치 비교 
					dp[i][j] = Math.max(dp[i][j-1], dp[i-w[j]][j-1] + v[j]);
				}
				else
				{
					//물건이 안들어가는 경우에는 j-1번째까지의 최대가치와 동일
					dp[i][j] = dp[i][j-1];
				}				
			} 
		}		
		
		System.out.println("#" + testNo + " " + dp[K][N]);		
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		for(int tc = 1; tc <= T; tc++)
		{
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			//부피, 가치
			w = new int[N+1];
			v = new int[N+1];
						
			for(int i = 1; i <= N; i++)
			{
				st = new StringTokenizer(br.readLine());
				w[i] = Integer.parseInt(st.nextToken());
				v[i] = Integer.parseInt(st.nextToken());		 
			}
			
			test(tc);			
		}
	}
}