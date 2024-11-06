import java.util.*;
import java.io.*;

public class Solution {
    static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());

        for (int t = 0; t < n; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());

            int dx = Math.abs(x2 - x1);
            int dy = Math.abs(y2 - y1);

            int moves;
            if (dx == dy) {
                moves = 2 * dx;
            } else {
                moves = (dx + dy) % 2 == 0 ? 2 * Math.max(dx, dy) : 2 * Math.max(dx, dy) - 1;
            }

            System.out.println("#"+(t+1)+" "+moves);
        }
    }
}
