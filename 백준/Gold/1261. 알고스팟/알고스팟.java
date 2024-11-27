import java.io.*;
import java.util.*;

public class Main {
	static int[][] map,copy;
	static boolean[][] visit;
	static int n,m;
	static int MinValue;
	static int[] dx = {1,0,-1,0};
	static int[] dy = {0,1,0,-1};
 	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		map = new int[m+2][n+2];
		copy = new int[m+2][n+2];
		visit = new boolean[m+2][n+2];
		
		for(int i=0; i<m+2; i++) {
			map[i][0] = -1;
			map[i][n+1] = -1;
			copy[i][0] = -1;
			copy[i][n+1] = -1;
		}
		
		for(int j=0; j<n+2; j++) {
			map[0][j] = -1;
			map[m+1][j] = -1;
			copy[0][j] = -1;
			copy[m+1][j] = -1;
		}
		
		for(int i=1; i<=m; i++) {
			String line = br.readLine();
			for(int j=1; j<=n; j++) {
				map[i][j] = line.charAt(j-1)-'0';
				copy[i][j] = Integer.MAX_VALUE;
			
			}
		}
		MinValue = Integer.MAX_VALUE;
		int res = bfs(1,1);
		
		System.out.println(res);
	}
	
	static int bfs(int x,int y) {
		PriorityQueue<int[]> pq = new PriorityQueue<>((o1,o2) -> o1[2]-o2[2]);
		
		pq.offer(new int[] {x,y,0});
		
		while(!pq.isEmpty()) {
			int[] cur = pq.poll();
			
			int cx = cur[0],cy = cur[1], dist=cur[2];
			
			if(cx == m && cy == n) {
				
				MinValue = Math.min(MinValue, dist);
				return MinValue;
			}
			
			for(int i=0; i<4; i++) {
				int nx = cx + dx[i];
				int ny = cy + dy[i];
				
				if(map[nx][ny] != -1 && map[nx][ny] == 0) {
					if(copy[nx][ny] > dist) {
						copy[nx][ny] = dist;
						pq.offer(new int[] {nx,ny,dist});
					}
				}
				
				if(map[nx][ny] != -1 && map[nx][ny] == 1) {
					int newdist = dist +1;
					if(copy[nx][ny] > newdist) {
						copy[nx][ny] = newdist;
						pq.offer(new int[] {nx,ny,newdist});
					}
				}
				
			}
			
		}
		return -1;
	}
}
