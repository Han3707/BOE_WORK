import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		while (true) {
			
			st = new StringTokenizer(bf.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
		
			if(a>b) {
				if(a%b ==0) {
					System.out.println("multiple");
				}else System.out.println("neither");
			}else if(a<b) {
				if(b%a == 0) {
					System.out.println("factor");
				}else System.out.println("neither");
			}else if(a==0&&b==0) {
				break;
			}else {
				System.out.println("neither");
			}
		}
	}
}
