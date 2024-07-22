import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader bf= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(bf.readLine());
		
		int a = Integer.parseInt(st.nextToken());
		int b = Integer.parseInt(st.nextToken());
		int[] arr = new int[a];
		for(int i=0; i<a; i++) {
			arr[i] = Integer.parseInt(bf.readLine());
		}
		int cnt =a-1;
		int res =0;
		while(b != 0) {
	
			if(b >= arr[cnt]) {
				b -= arr[cnt];
				res++;
				if(b < 0){
					break;
					}
			}else {
				cnt--;
			}
		}
		System.out.println(res);
	}

}
