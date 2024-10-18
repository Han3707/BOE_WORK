import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

// 수빈이는 동생과 숨바꼭질을 하고 있다. 수빈이는 현재 점 N에 있고, 동생은 점 K에 있다.
// 수빈이는 걷거나 순간이동을 할 수 있다.
// 만약 수빈이의 위치가 X일 때 걷는다면 1초 후에 X-1 또는 X+1로 이동하게 된다.
// 순간이동을 하는 경우에는 1초 후에 2*X의 위치로 이동하게 된다.
// 수빈이와 동생의 위치가 주어졌을 때,
// 수빈이가 동생을 찾을 수 있는 가장 빠른 시간이 몇 초 후인지 구하는 프로그램을 작성하시오.


public class Main {

    static class Point {
        int val;
        int cnt;

        public Point(int val, int cnt) {
            this.val = val;
            this.cnt = cnt;
        }
    }
    static int min = Integer.MAX_VALUE;
    static boolean[] visited = new boolean[100001];
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String[] s = reader.readLine().split(" ");
        int N = Integer.parseInt(s[0]);
        int K = Integer.parseInt(s[1]);
        Point start = new Point(N, 0);
        Queue<Point> queue = new LinkedList<>();
        queue.add(start);

        while(!queue.isEmpty()) {
            Point poll = queue.poll();
            int curr = poll.val;
            int cnt = poll.cnt;

            if(curr < 0 && curr > 100001) {
                continue;
            }
            if(visited[curr]) {
                continue;
            }

            visited[curr] = true;

            if(curr == K && cnt < min) {
                min = cnt;
                System.out.println(min);
                return;
            }

            if(curr * 2 >= 0 && curr * 2 < 100001 ) {
                if(!visited[curr*2]) {
                    Point mul = new Point(curr * 2, poll.cnt + 1);
                    queue.add(mul);
                }
            }
            if(curr + 1 < 100001) {
                if(!visited[curr+1]) {
                    Point add = new Point(curr + 1, poll.cnt + 1);
                    queue.add(add);
                }
            }
            if(curr - 1 >= 0 && curr - 1 < 100001) {
                if(!visited[curr-1]) {
                    Point sub = new Point(curr - 1, poll.cnt + 1);
                    queue.add(sub);
                }
            }

        }
    }
}