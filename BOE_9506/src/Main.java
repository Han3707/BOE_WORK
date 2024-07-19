import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader bf= new BufferedReader(new InputStreamReader(System.in));
		
		
		
		while(true) {
			int ans = 0;
			int cnt = 0;
			int n = Integer.parseInt(bf.readLine());
			
			if(n== -1) {
				break;
			}
			int[] arr = new int[n];
			for(int i=1; i<n; i++) {
				if(n%i == 0) {
					cnt++;
					ans += i;
					arr[cnt] = i;
				}
			}
			if(ans == n) { 
				System.out.print(ans+" = "+arr[1]);
				for(int i=2; i<cnt+1; i++) {
					System.out.print(" + "+arr[i]);
				}
				System.out.println();
			}else
				System.out.println(n+" is NOT perfect.");
		}
		
		
	}

}
