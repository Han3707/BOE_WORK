import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        int num =0;
        for(int i=0; i<3; i++) {
            String str = br.readLine();
            if(str.equals("Fizz") || str.equals("Buzz")|| str.equals("FizzBuzz")) continue;
            else {
                num = Integer.parseInt(str);
                if(i == 0){
                    num += 3;
                }else if(i == 1){
                    num += 2;
                }else num += 1;
            }
        }

        if(num % 3 == 0 && num % 5 == 0) System.out.println("FizzBuzz");
        else if(num % 3 == 0 && num % 5 != 0) System.out.println("Fizz");
        else if(num % 3 != 0 && num % 5 == 0) System.out.println("Buzz");
        else System.out.println(num);

    }
}