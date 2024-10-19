import java.nio.Buffer;
import java.util.*;
import java.io.*;

public class Main {

  public static void main(String[] args) throws IOException{
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    StringBuilder sb = new StringBuilder();
    int n = Integer.parseInt(st.nextToken());
    int m = Integer.parseInt(st.nextToken());
    HashMap<Integer,String> str = new HashMap<>();
    HashMap<String,Integer> number = new HashMap<>();
    for(int i=1; i<n+1; i++){
      String a = br.readLine();
      str.put(i,a);
      number.put(a,i);
    }


    for(int i=0; i<m; i++){
      String line = br.readLine();
      if(check(line)){
        int num = Integer.parseInt(line);
        sb.append(str.get(num)).append("\n");
      }else{
        sb.append(number.get(line)).append("\n");
          
      }
      
      }
    System.out.println(sb);
    
    
    }
    

  static boolean check(String line){
    try{
      Integer.parseInt(line);
      return true;
    }catch(NumberFormatException ex){
      return false;
    }

  }
}