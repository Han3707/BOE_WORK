

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int k = Integer.parseInt(br.readLine()); //친구의 정답의 수
		String a = br.readLine();
		String b = br.readLine();
		int yes = 0, no = 0, answer = 0;
		
		for(int i = 0; i < a.length(); i++) {
			if (a.charAt(i) == b.charAt(i)) {
				yes += 1;
			}
			else {
				no +=1;
			}
			
		}
		
		if (yes >= k) {
			answer = k + (a.length()-yes);
		}
		else {
			answer = yes + (a.length()-k);
		}
		
		System.out.println(answer);
		
	}
}
