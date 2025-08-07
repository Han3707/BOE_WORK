import java.util.*;
import java.io.*;

public class Main{
    
    private static int cntdata(String data, String p){
        int cnt = 0;
        int idx = 0;
        
        while((idx = data.indexOf(p, idx)) != -1){
            cnt++;
            idx++;
        }
        
        return cnt;
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int B = Integer.parseInt(br.readLine());
        
        String[] lowp = new String[B];
        String[] highp = new String[B];
        
        // low
        for(int i = 0; i < B; i++){
            lowp[i] = br.readLine();
        }
        
        // high
        for(int i = 0; i < B; i++){
            highp[i] = br.readLine();
        }
        
        // 실시간 데이터
        int N = Integer.parseInt(br.readLine());
        
        for(int i = 0; i < N; i++){
            String data = br.readLine();
            
            int lowcnt = 0;
            int highcnt = 0;
            
            for(int j = 0; j < B; j++){
                lowcnt += cntdata(data, lowp[j]);
                highcnt += cntdata(data, highp[j]);
            }
            
            int C = highcnt - lowcnt;
            
            if(C > 0){
                System.out.println("LOW " + C);
            }else if(C < 0){
                System.out.println("HIGH " + Math.abs(C));
            }else{
                System.out.println("GOOD");
            }
        }
        
        br.close();
    }
}