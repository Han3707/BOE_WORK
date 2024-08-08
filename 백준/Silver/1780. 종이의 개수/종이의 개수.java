import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLOutput;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int[][] arr;
    static int m_one,zero,one;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = null;

        arr = new int[n][n];

        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<n; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        countPaper(0,0,n);

        System.out.println(m_one);
        System.out.println(zero);
        System.out.println(one);


    }

    public static void countPaper(int i,int j,int n){
        if(check(i,j,n)){
            if(arr[i][j] == -1){
                m_one++;
            }else if(arr[i][j] == 0){
                zero++;
            }else one++;
            return;
        }

        int new_n = n/3;

        for(int x = 0; x<3; x++){
            for(int y =0; y<3; y++){
                countPaper(i+x*new_n,j+y*new_n,new_n);
            }
        }

    }

    public static boolean check(int i,int j,int n){
        int temp = arr[i][j];
        for(int x = i; x<i+n; x++){
            for(int y = j; y<j+n; y++){
                if(arr[x][y] != temp){
                    return false;
                }
            }
        }
        return true;
    }
}
