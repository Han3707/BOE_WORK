import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        ArrayList<numbers> n_list = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int num1 = Integer.parseInt(st.nextToken());
            int num2 = Integer.parseInt(st.nextToken());

            n_list.add(new numbers(num1, num2));
        }

        Collections.sort(n_list, new Comparator<numbers>() {
            @Override
            public int compare(numbers s1, numbers s2) {
                if (s1.num2 > s2.num2) return 1;
                else if (s1.num2 == s2.num2) {
                    if (s1.num1 > s2.num1) return 1;
                    else if (s1.num1 < s2.num1) return -1;
                }
                return -1;
            }
        });

        for (numbers num : n_list) {
            System.out.println(num);
        }
    }
}

class numbers {
    int num1;
    int num2;

    public numbers(int num1, int num2) {
        this.num1 = num1;
        this.num2 = num2;
    }

    @Override
    public String toString() {
        return num1 + " " + num2;
    }
}
