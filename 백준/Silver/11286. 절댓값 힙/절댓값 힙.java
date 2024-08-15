import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Comparator;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        PriorityQueue<Integer> pq = new PriorityQueue<>(new absdown());

        int n = Integer.parseInt(bf.readLine());
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<n; i++){
            int num = Integer.parseInt(bf.readLine());
            if(num != 0){
                pq.offer(num);
            }
            else if(pq.isEmpty()) {
                sb.append(0).append('\n');
            }
            else {
                sb.append(pq.poll()).append('\n');

            }
        }
        System.out.println(sb);
    }

    private static class absdown implements Comparator<Integer>{

        @Override
        public int compare(Integer o1, Integer o2){

            if(Math.abs(o1) > Math.abs(o2)){
                return 1;
            }
            else if(Math.abs(o1) < Math.abs(o2)){
                return -1;
            }
            else{
                if(o1 > o2){
                    return 1;
                }
                else{
                    return -1;
                }
            }
        }
    }
}
