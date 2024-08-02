import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader bf= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int n = Integer.parseInt(bf.readLine());
		int[][] arr = new int[0][n];
		int cnt = 0;
		for(int i=0; i<4; i++) {
			st = new StringTokenizer(bf.readLine());
			arr[0][i] = Integer.parseInt(st.nextToken());
		}
		for(int i=0; i<arr.length; i++) {
			System.out.println(arr[i]);
		}
	}

}
