import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

  static int n,m;
  static int[] arr; // 숫자 조합을 저장할 배열
  static boolean[] isUsed; // 숫자가 사용됐는지 알아보는 방문처리 배열
  static StringBuilder sb = new StringBuilder(); // 결과저장 스트링 빌더
  public static void main(String[] args) throws IOException{
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());


    n = Integer.parseInt(st.nextToken());
    m = Integer.parseInt(st.nextToken());

    arr = new int[m]; // 배열 초기화
    isUsed = new boolean[n + 1]; // 1부터 n까지의 숫자 사용 여부를 저장하기 위한 배열 초기화

    func(0,1); // 조합을 생성하는 함수 호출
    System.out.println(sb); // 결과 출력
  }

  public static void func(int k,int start) {
    if (k == m) { // 조합의 길이가 m의 도달하면 조합을 sb 에 추가
      for (int i = 0; i < m; i++) {
        sb.append(arr[i]).append(' ');
      }
      sb.append('\n');

      return; // 함수 종료
    }
    for (int i = start; i <= n; i++) { // 1부터 n까지의 숫자를 반복
      arr[k] = i;
      func(k+1,i);
    }

  }
}
