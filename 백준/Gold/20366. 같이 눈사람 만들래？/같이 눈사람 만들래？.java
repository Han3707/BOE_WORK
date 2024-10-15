import java.io.*;
import java.util.*;


public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);

        int snow_King = 0;
        int res = Integer.MAX_VALUE;
        for(int i=0; i<N; i++){
            for(int j=i+1; j<N; j++){
                snow_King = arr[i]+arr[j];
                int left =0;
                int right = N-1;

                while(left < right){

                    if(left == i || left == j){
                        left++;
                        continue;
                    }

                    if(right ==i || right ==j){
                        right--;
                        continue;
                    }

                    int snow_man = arr[left] + arr[right];

                    res = Math.min(res,Math.abs(snow_King-snow_man));

                    if(snow_King > snow_man){
                        left++;
                    }else if(snow_King < snow_man){
                        right--;
                    }else{
                        res = 0;
                        break;
                    }
                }
            }
        }
        System.out.println(res);
    }
}