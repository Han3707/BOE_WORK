import java.util.*;

class Solution {
    public int solution(int n, int[][] computers) {
        boolean[] visited = new boolean[n];
        int networkcnt = 0;
        
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                bfs(i, computers, visited);
                networkcnt++;
            }
        }
        
        return networkcnt;
    }
    
    private void bfs(int start, int[][] computers, boolean[] visited) {
        Deque<Integer> queue = new ArrayDeque<>();
        queue.offer(start);
        visited[start] = true;
        
        while (!queue.isEmpty()) {
            int current = queue.poll();
            
            for (int i = 0; i < computers.length; i++) {
                if (computers[current][i] == 1 && !visited[i]) {
                    visited[i] = true;
                    queue.offer(i);
                }
            }
        }
    }
}