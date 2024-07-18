import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException { 
		// TODO Auto-generated method stub
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(bf.readLine());
		int res = 3;
		int cnt = 1;
		for(int i=1; i<n; i++) {
			cnt = cnt*2;
			res += cnt;
		}
		System.out.println(res*res);
	}

}
