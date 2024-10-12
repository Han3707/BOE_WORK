import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

         Deque<Integer> dq = new ArrayDeque<>();


        for(int i=0; i<n; i++){
            int a = Integer.parseInt(br.readLine());
            if(a != 0){
                dq.offer(a);
            }else{
                dq.pollLast();
            }
        }

        int res =0;
        while(!dq.isEmpty()){
            res += dq.poll();
        }

        System.out.println(res);
    }
}