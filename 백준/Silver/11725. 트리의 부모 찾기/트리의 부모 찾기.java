import java.io.*;
import java.util.*;

public class Main {
	static int n;
	static int a,b;
	static boolean[] visit;
	static int[] p_node;
	static ArrayList<Integer>[] child;

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		n = Integer.parseInt(br.readLine());
		
		visit = new boolean[n+1];
		p_node = new int[n+1];
		child = new ArrayList[n+1];
		for(int i =0; i < n+1 ; i++) {
			child[i] = new ArrayList<>();
		}
		
		
		for(int i =1; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			child[a].add(b);
			child[b].add(a);
		}
		dfs(1);
		for(int i = 2; i < n+1; i++) {
			System.out.println(p_node[i]);
		}
	}
	
	static void dfs(int node) {
		
		visit[node] = true;
		for(int i = 0; i< child[node].size(); i++) {
			if(!visit[child[node].get(i)]) {
				p_node[child[node].get(i)] = node;
				dfs(child[node].get(i));
		}
		
	
	}

}
}