import java.io.*;
import java.util.*;

public class Main {
    static int N,M;
    static char[] gen;
    static int[] parent; // 유니온 파인드
    static ArrayList<Edge> edges = new ArrayList<>();

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        gen = new char[N+1];
        parent = new int[N+1];


        //성별 입력
        st = new StringTokenizer(br.readLine());
        for(int i=1; i<N+1; i++){
            gen[i] = st.nextToken().charAt(0);
            parent[i] = i; // 처음부모는 자기자신
        }

        // 간선 정보 입력
        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());

            if(gen[u] != gen[v]){ // 성별이 다를 경우에만 간선 추가
                edges.add(new Edge(u,v,d));
            }
        }

        Collections.sort(edges);

        int total_dist = 0; // 모든 학교가 연결되었다면 최소 거리를 저장하는 함수
        int cnt =0; // 모든 학교가 연결되어 있는지 확인하는 변수

        for(Edge edge:edges){
            if(find(edge.u) != find(edge.v)){
                union(edge.u,edge.v);
                total_dist += edge.d;
                cnt++;
                if(cnt == N-1) break;
            }
        }

        if(cnt == N-1) System.out.println(total_dist);
        else System.out.println(-1);


    }

    // ------------ 크루스칼 유니온 파인드 ---------------

    static int find(int x){
        if(parent[x] == x) return x;
        return parent[x] = find(parent[x]);
    }

    static void union(int x, int y){
        int rootX = find(x);
        int rootY = find(y);
        if(rootX != rootY){
            parent[rootY] = rootX;
        }
    }

    static class Edge implements Comparable<Edge>{
        int u,v,d;

        public Edge(int u,int v,int d){
            this.u = u;
            this.v = v;
            this.d = d;
        }

        @Override // 최소 거리 순으로 정렬
        public int compareTo(Edge o) {
            return this.d - o.d;
        }
    }
}