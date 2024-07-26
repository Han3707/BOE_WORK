import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;


public class Main {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		
		int n = Integer.parseInt(br.readLine());
		ArrayList<nums> n_List = new ArrayList<>();
		
		for(int i=0; i<n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			n_List.add(new nums(x, y));
		}
		
		Collections.sort(n_List,new Comparator<nums>() {
			@Override
			public int compare(nums n1, nums n2) {
				if(n1.x > n2.x) return 1; // x 를 오름차순
				else if(n1.x == n2.x) {
					if(n1.y > n2.y) return 1; // x가 같으면 y 기준으로 오름차순
				}
				return -1;
			}
		});
		for(nums num:n_List) {
			System.out.println(num.x+" "+num.y);
		}
	}

}

class nums{
	int x;
	int y;
	
	
	
	public nums(int x,int y) {
		this.x = x;
		this.y = y;
	}
	

	

}
