import java.io.*;
import java.util.*;



public class Main {
		

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		char[][] arr = new char[n][n];
		
		for(int i=0; i<n; i++) {
			String str = br.readLine();
			for(int j=0; j<n; j++) {
				arr[i][j] = str.charAt(j);
			}
		}
		int ans = 0;
		int cnt = 0;
		for(int i =0; i<n; i++) {
			for(int j=0; j<n; j++) {
				if(i+1<n) {
					char temp = arr[i][j];
					arr[i][j] = arr[i+1][j];
					arr[i+1][j] = temp;
					cnt = count(arr);
					temp = arr[i][j];
					arr[i][j] = arr[i+1][j];
					arr[i+1][j] = temp;
				}
				if(ans < cnt) ans = cnt;
				
				if(j+1<n) {
					char temp = arr[i][j];
					arr[i][j] = arr[i][j+1];
					arr[i][j+1] = temp;
					ans = count(arr);
					temp = arr[i][j];
					arr[i][j] = arr[i][j+1];
					arr[i][j+1] = temp;
				}
				
				if(ans < cnt) ans = cnt;
				
			}
		}
		System.out.println(ans);
	}
	
	static int count(char[][] arr) {
		int n = arr.length;
		int max =0;
		
		for(int i =0; i<n-1; i++) {
			int cnt =1;
			int cnt2 =1;
			for(int j=0; j<n-1; j++) {
				if(arr[i][j] == arr[i+1][j]) {
					cnt++;
				}
				if(arr[j][i] == arr[j+1][i]) {
					cnt2++;
				}
			}
			max = Math.max(max, cnt);
			max = Math.max(max, cnt2);
			
		}
		return max;
		
	}

}
