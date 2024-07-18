import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(bf.readLine());
		int[] arr = {25,10,5,1};
		int res =0;
		for(int i=0; i<t; i++) {
			int num = Integer.parseInt(bf.readLine());
			for(int j=0; j<arr.length; j++) {
				res = num / arr[j];
				num = num % arr[j];
				System.out.print(res+" ");
			}
			System.out.println();
		}
		
		
		
		
	}

}
