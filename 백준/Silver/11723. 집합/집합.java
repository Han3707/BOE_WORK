import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(br.readLine());


        int[] num = new int[21];
        int a = 0;
        for(int i=0; i<n; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            String command = st.nextToken();

            switch (command){
                case "add":
                    a = Integer.parseInt(st.nextToken());
                    num[a] = 1;
                    break;
                case "check":
                    a = Integer.parseInt(st.nextToken());
                    if(num[a] == 1) sb.append(1).append("\n");
                    else sb.append(0).append("\n");
                    break;
                case "remove":
                    a = Integer.parseInt(st.nextToken());
                    if(num[a] == 1) num[a] = 0;
                    break;
                case "toggle":
                    a = Integer.parseInt(st.nextToken());
                    if(num[a] == 1) num[a] = 0;
                    else num[a] = 1;
                    break;
                case "all":
                    Arrays.fill(num,1);
                    break;
                case "empty":
                    Arrays.fill(num,0);
                    break;
                default:
                    break;
            }
        }
        System.out.println(sb);
    }
}