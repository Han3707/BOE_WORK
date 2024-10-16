import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int[] arr;
    static int[] operator = new int[4]; // +,-,*,/;
    static int Max_res = Integer.MIN_VALUE;
    static int Min_res = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());

        arr = new int[n];

        // 숫자 입력
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }


        // 연산자 입력
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<4; i++){
            operator[i] = Integer.parseInt(st.nextToken());
        }

        // 순열 dfs
        dfs(arr[0],1);
        System.out.println(Max_res);
        System.out.println(Min_res);

    }

    static void dfs(int first,int idx){
        if(idx == n){

            Max_res = Math.max(Max_res,first);
            Min_res = Math.min(Min_res,first);
            return;
        }

        for(int i=0; i<4; i++){
            if(operator[i] > 0){
                operator[i]--; // 해당 연산자 사용했으므로 --

                switch (i){
                    case 0:
                        dfs(first + arr[idx],idx+1);
                        break;
                    case 1:
                        dfs(first-arr[idx],idx+1);
                        break;
                    case 2:
                        dfs(first*arr[idx],idx+1);
                        break;
                    case 3:
                        dfs(first/arr[idx],idx+1);
                        break;
                }

                operator[i]++; // 연산자 복원
            }
        }

    }
}