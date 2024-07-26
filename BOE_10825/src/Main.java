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
		
		ArrayList<Score> list_score = new ArrayList<>();
		
		for(int i=0; i<n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			String name = st.nextToken();
			int k = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			
			list_score.add(new Score(name, k, e, m));
		}
		
		Collections.sort(list_score,new Comparator<Score>() {
			@Override
			public int compare(Score s1,Score s2) {
				if(s1.kor< s2.kor) return 1;
				else if(s1.kor == s2.kor) {
					if(s1.eng>s2.eng) return 1;
					else if(s1.eng == s2.eng) {
						if(s1.math < s2.math) {
							return 1;
						}
						else if(s1.math ==s2.math){
							return s1.name.compareTo(s2.name);
					}
				}
				
			}
				return -1;
			}
		});
		
	
		for(Score score : list_score) {
			score.print();
		}
	}

}



class Score {
	
	String name;
	int kor;
	int eng;
	int math;
	
	public Score(String name, int kor, int eng, int math){
			this.name = name;
			this.kor = kor;
			this.eng = eng;
			this.math = math;
	}

	public int compareTo(Score s2) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public void print() {
		System.out.println(name);
	}
	
}