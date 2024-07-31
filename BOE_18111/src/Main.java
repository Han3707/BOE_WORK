import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;




public class Main {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int b = Integer.parseInt(st.nextToken());
		int[][] arr = new int[n][m];
		
		
		int max = 0;
		int min = 256;
		
		for(int i =0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j =0; j<m; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				if(max < arr[i][j]) {
					max = arr[i][j];
				}
				else if(min > arr[i][j]) {
					min = arr[i][j];
				}
			}
		}
		
	
		int m_time = 99999999;
		int max_stone = 0;
		
		
		for(int x=max; x>=min; x--) {
			int time = 0;
            int stone = b;
			for(int i=0; i<n; i++) {
				for(int j=0; j<m; j++) {
					if(arr[i][j] <= x) {
						time += (x-arr[i][j]);
						stone -= x-arr[i][j];
					}else if(arr[i][j]>x) {
						time += (arr[i][j] -x)*2;
						stone += arr[i][j] -x;
					}
				}
			}
			if(b < stone) break;
			if(time < m_time) {
				m_time = time;
				max_stone = x;
			}else { 
				if(time == m_time) {
					if(max_stone < x) {
						max_stone = x;
				}
			}
			}
		}
			
		System.out.println(m_time+" "+max_stone);
		
		
	}
}
