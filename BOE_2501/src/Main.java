import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in)); 
		StringTokenizer st = new StringTokenizer(bf.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		
		int cnt = 0;
		int i = 1;
		int[] arr = new int[k];
		while(cnt < k) {
			if(n%i ==0) {
				arr[cnt] = i;
				cnt++;
			}
			i++;
			if(i >= n ) break;
			
		}
		System.out.println(cnt+" "+i);
		if(cnt < k) {System.out.println(0);}else System.out.println(arr[k-1]);
	}

}
