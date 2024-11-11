import java.io.*;
import java.util.*;

public class Solution {
    static int N,B;
    static int[] num;
    static int Minsum;
    public static void main(String[] args) throws IOException{
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int t = Integer.parseInt(br.readLine());

        for(int tc=1; tc<t+1; tc++){
            StringTokenizer st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken());
            B = Integer.parseInt(st.nextToken());

            num = new int[N];
            Minsum = Integer.MAX_VALUE;
            st = new StringTokenizer(br.readLine());
            for(int i=0; i<N; i++){
                num[i] = Integer.parseInt(st.nextToken());
            }

            for(int i=1; i<N+1; i++){
                comb(0,0,i,new int[i]);
            }

            sb.append("#").append(tc).append(" ").append(Minsum).append("\n");

        }

        System.out.println(sb.toString());
    }

    static void comb(int start,int depth,int count,int[] arr){
        if(depth == count){
            int sum = 0;

            for(int i=0; i< count; i++){
                sum += arr[i];
            }
            if(sum >= B) {
                Minsum = Math.min(Minsum, (Math.abs(B - sum)));
            }
            return;
        }

        for(int i=start; i<N; i++){
            arr[depth] = num[i];
            comb(i+1,depth+1,count,arr);

        }

    }
}