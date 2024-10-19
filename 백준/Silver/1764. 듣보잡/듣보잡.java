import java.io.*;
import java.util.*;

public class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int n = Integer.parseInt(st.nextToken());
    int m = Integer.parseInt(st.nextToken());

    HashSet<String> see = new HashSet<>();
    HashSet<String> hear = new HashSet<>();
    List<String> list = new ArrayList<>();


    for(int i=0; i<n; i++){
      String line = br.readLine();
      see.add(line);
    }

    for(int i=0; i<m; i++){
      String line = br.readLine();
      hear.add(line);
    }

    int cnt=0;
    for(String ch : see){
      if(hear.contains(ch)){
        cnt++;
        list.add(ch);
      }
    }
    Collections.sort(list);
    System.out.println(cnt);
    for(String a : list){
      System.out.println(a);
    }
  }

}