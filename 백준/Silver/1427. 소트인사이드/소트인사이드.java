import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;


public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        String n = bf.readLine();

        int[] arr = new int[n.length()];
        for(int i=0; i<n.length(); i++){
            arr[i] = Character.getNumericValue(n.charAt(i));
        }
        Arrays.sort(arr);
        for(int i=arr.length-1; i>=0; i--){
            System.out.print(arr[i]);
        }

    }
}