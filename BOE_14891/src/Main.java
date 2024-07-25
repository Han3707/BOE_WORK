import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;



public class Main {
    static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[][] arr = new int[4][8];
        int[][] arr2 = new int[4][8];
        

        for (int i = 0; i < 4; i++) {
            String s = br.readLine();
            for (int j = 0; j < 8; j++) {
                arr[i][j] = s.charAt(j) - '0';
                arr2[i][j] = s.charAt(j) - '0';
            }
        }
        
        

        int sum = 0;
        int t = Integer.parseInt(br.readLine());

        for (int i = 0; i < t; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            
            
            // arr = 값을 비교하는 배열 arr2 = 회전하는 배열 
            switch (n) {
            
                case 1:
                    if (v == 1) {
                    	right(arr2, 0);
                        if (arr[0][2] != arr[1][6]) left(arr2, 1);
                        else break;
                        if (arr[1][2] != arr[2][6]) right(arr2, 2);
                        else break;
                        if (arr[2][2] != arr[3][6]) left(arr2, 3);
                    }
                   
                    else {
                    	left(arr2, 0);
                        if (arr[0][2] != arr[1][6]) right(arr2, 1);
                        else break;
                        if (arr[1][2] != arr[2][6]) left(arr2, 2);
                        else break;
                        if (arr[2][2] != arr[3][6]) right(arr2, 3);      
                    }
                    break;
                case 2:
                    if (v == 1) {
                    	right(arr2,1);
                    	if(arr[0][2] != arr[1][6]) left(arr2,0);
                        if(arr[1][2] != arr[2][6]) left(arr2,2);
                        else break;
                        if(arr[2][2] != arr[3][6]) right(arr2, 3);
            
                        
                    } else {
                    	left(arr2, 1);
                    	if(arr[0][2] != arr[1][6]) right(arr2,0);
                        if(arr[1][2] != arr[2][6]) right(arr2,2);
                        else break;
                        if(arr[2][2] != arr[3][6]) left(arr2, 3);        
                    }
                    break;
                case 3:
                    if (v == 1) {
                    	right(arr2, 2);
                    	if(arr[2][2] != arr[3][6]) left(arr2,3);
                    	if(arr[2][6] !=arr[1][2] ) left(arr2,1);
                    	else break;
                    	if(arr[0][2] != arr[1][6]) right(arr2, 0);
                         
                    } else {
                    	left(arr2, 2);
                    	if(arr[2][2] != arr[3][6]) right(arr2,3);
                    	if(arr[2][6] !=arr[1][2] ) right(arr2,1);
                    	else break;
                    	if(arr[0][2] != arr[1][6]) left(arr2, 0);
                    }
                    break;
                case 4:
                    if (v == 1) {
                    	right(arr2, 3);
                        if(arr[3][6] != arr[2][2]) left(arr2, 2);
                        else break;
                        if(arr[2][6] != arr[1][2]) right(arr2,1);
                        else break;
                        if(arr[1][6] != arr[0][2]) left(arr2,0);
                    } else {
                    	left(arr2, 3);
                        if(arr[3][6] != arr[2][2]) right(arr2, 2);
                        else break;
                        if(arr[2][6] != arr[1][2]) left(arr2,1);
                        else break;
                        if(arr[1][6] != arr[0][2]) right(arr2,0);
                        
                    }
                    break;
                    
            }
            //  arr2 가 회전한 배열의 값을 arr 에 업데이트 시켜줌 
            for (int k = 0; k < 4; k++) {          	
            	for(int j=0; j<8; j++) {
            		arr[k][j] = arr2[k][j];
            	}
            }
           
        }
        for (int k = 0; k < 4; k++) {
            if (arr[k][0] == 1) {
                if (k == 0) sum += 1;
                else if (k == 1) sum += 2;
                else if (k == 2) sum += 4;
                else if (k == 3) sum += 8;
            }
        }

        System.out.println(sum);
    }

    // 시계방향으로 돌 때
    public static void right(int[][] arr, int num) {
        int tmp = arr[num][7];
        for (int i = 7; i > 0; i--) {
            arr[num][i] = arr[num][i - 1];
        }
        arr[num][0] = tmp;
    }

    // 반시계방향으로 돌 때
    public static void left(int[][] arr, int num) {
        int tmp = arr[num][0];
        for (int i = 0; i < 7; i++) {
            arr[num][i] = arr[num][i + 1];
        }
        arr[num][7] = tmp;
    }
}
