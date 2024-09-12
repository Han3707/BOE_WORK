import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


        int n = Integer.parseInt(br.readLine());
        String str = br.readLine();

        int[] arr = new int[n];

        int ans =0;
        for(int i=0; i<n; i++){
            arr[i] = (str.charAt(i)-'0' -48);

            ans += arr[i] * Math.pow(31,i);
        }
        System.out.println(ans);

    }
}