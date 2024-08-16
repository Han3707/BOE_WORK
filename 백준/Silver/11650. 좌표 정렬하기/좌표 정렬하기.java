import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;


public class Main {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st ;

        int N = Integer.parseInt(br.readLine());
        int[][] arrays = new int[N][2];

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            arrays[i][0] = Integer.parseInt(st.nextToken());
            arrays[i][1] = Integer.parseInt(st.nextToken());
        }
        br.close();

        Arrays.sort(arrays, (num1, num2) -> {
            return num1[0]!=num2[0] ? num1[0]-num2[0] : num1[1]-num2[1];
        });

        for(int i = 0; i < N; i++){
            bw.write(arrays[i][0] + " " + arrays[i][1] +"\n");
        }

        bw.flush();
        bw.close();


    }
}