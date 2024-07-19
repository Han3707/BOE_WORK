import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(bf.readLine());
		int cnt =1;
		int line =1;
		while(cnt < t) {
			line++;
			cnt = line + cnt;
		}
		int ans = cnt - t;
		if(line % 2 == 0) {
			System.out.println((line-ans)+"/"+(1+ans));
		}else {
			System.out.println((1+ans)+"/"+(line-ans));
		}
	}
}
