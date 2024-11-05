import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static int n,l;
    static int[] map;
    static int[] visit;
    static List<int[]> size;
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        l = Integer.parseInt(st.nextToken());

        size = new ArrayList<>();

        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken())-1; // 널판지의 덮는 범위가 b-1 까지 덮어야하므로
            size.add(new int[]{a,b});
        }

        size.sort((o1,o2)-> Integer.compare(o1[0],o2[0]));

        int cur = 0; // 현재위치
        int cnt = 0; // 바닥 갯수

        for(int[] s:size){
            if(cur <= s[0]){
                cur = s[0];
            }

            while(true){
                if(cur > s[1]) break; // 현재 위치가 웅덩이 위치를 벗어나면 break;

                cur += l; // 바닥 크기 만큼 더해주기
                cnt++;  // 바닥 갯수 증가
            }
        }
        System.out.println(cnt);


        }

    }
