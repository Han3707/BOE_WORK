import java.util.*;
import java.io.*;

public class Main {
    static int V,E ; // 정잠의 개수 , 간선의 개수
    static int[] parents; // 유니온 파인드를 위한 부모 배열
    static List<Edge> edges = new ArrayList<>(); // 간선 리스트

    static class Edge implements Comparable<Edge> {
        int start,end,weight;

        Edge(int start,int end,int weight){
            this.start = start;
            this.end = end;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o){
            return Integer.compare(this.weight,o.weight);
        }
    }

    static int find(int a){
        if(parents[a] < 0 ) return a;
        return parents[a] = find(parents[a]);
    }

    static boolean union(int a, int b){
        int rootA = find(a);
        int rootB = find(b);
        if(rootA == rootB) return false;

        if(parents[rootA] < parents[rootB]){
            parents[rootA] += parents[rootB];
            parents[rootB] = rootA;
        }else{
            parents[rootB] += parents[rootA];
            parents[rootA] = rootB;
        }
        return true;
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        parents = new int[V+1];
        // 초기 부모 설정( 자기 자신을 부모로 설정 )
        Arrays.fill(parents,-1);

        for(int i=0; i<E; i++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            edges.add(new Edge(start,end,weight));
        }
        
        Collections.sort(edges);
        
        int cost = 0; // 가중치의 합
        int cnt =0; // 간선의 수
        
        for(Edge edge: edges){
            if(union(edge.start,edge.end)){
                cost += edge.weight;
                cnt++;
                
                if(cnt == V-1) break;
            }
        }

        System.out.println(cost);
    }
}