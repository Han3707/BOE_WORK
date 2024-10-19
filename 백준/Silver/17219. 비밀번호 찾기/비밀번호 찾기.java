import java.io.*;
import java.util.*;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st= new StringTokenizer(br.readLine());

    int n = Integer.parseInt(st.nextToken());
    int m = Integer.parseInt(st.nextToken());

    HashMap<String,String> pass = new HashMap<>();

    for(int i=0; i<n; i++){
    st = new StringTokenizer(br.readLine());
      String a = st.nextToken();
      String b = st.nextToken();
      pass.put(a,b);
    }

    for(int i=0; i<m; i++){
      String line = br.readLine();

      System.out.println(pass.get(line));
    }
  }
}