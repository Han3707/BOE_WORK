import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		
		
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			// 종이컵의 수
			int n = Integer.parseInt(st.nextToken());
			// 왼쪽에서 몇번째 종이컵 ? 간식 위치 
			int x = Integer.parseInt(st.nextToken());
			// 컵의 위치를 바꾸는 횟 수
			int k = Integer.parseInt(st.nextToken());
			
			int[] arr = new int[n+1];
			int res = 0;
			for(int i=1; i<n+1; i++) {
				arr[i] = i;
				if(arr[i] == x) {
					res = arr[i];
				}
			}
			
			for(int j=0; j<k; j++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				int temp = arr[a];
				arr[a] = arr[b];
				arr[b] = temp;
				
			}
			
			for(int i=0; i<arr.length; i++) {
				if(arr[i]==res) {
					System.out.println(i);
				}
			}
		

	}

}
