import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

  static int n,m;
  static int[] arr;
  static boolean[] isUsed;
  static StringBuilder sb = new StringBuilder();
  public static void main(String[] args) throws IOException{
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());


    n = Integer.parseInt(st.nextToken());
    m = Integer.parseInt(st.nextToken());

    arr = new int[m];
    isUsed = new boolean[n + 1];

    func(0);
    System.out.println(sb);
  }

  public static void func(int k) {
    if (k == m) {
      for (int i = 0; i < m; i++) {
        sb.append(arr[i]).append(' ');
      }
      sb.append('\n');

      return;
    }
    for (int i = 1; i <= n; i++) {
      arr[k] = i;
      isUsed[i] = true;
      func(k + 1);
      isUsed[i] = false;


    }

  }
}
