import java.util.*;

class Solution {
    public int solution(int[][] land) {
        int n = land.length;
        int m = land[0].length;
        
        // 1. 석유 덩어리에 번호 매기고 크기 저장
        int chunkId = 2; // 1과 구분하기 위해 2부터 시작
        Map<Integer, Integer> chunkSizes = new HashMap<>();
        
        // 상하좌우 방향
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};
        
        // BFS로 모든 석유 덩어리 찾기
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (land[i][j] == 1) {
                    int size = bfs(land, i, j, chunkId, dx, dy, n, m);
                    chunkSizes.put(chunkId, size);
                    chunkId++;
                }
            }
        }
        
        // 2. 각 열마다 지나가는 덩어리들의 크기 합 계산
        int maxOil = 0;
        
        for (int col = 0; col < m; col++) {
            Set<Integer> visitedChunks = new HashSet<>();
            int oilAmount = 0;
            
            for (int row = 0; row < n; row++) {
                if (land[row][col] > 1) { // 석유가 있는 칸
                    int chunk = land[row][col];
                    if (!visitedChunks.contains(chunk)) {
                        visitedChunks.add(chunk);
                        oilAmount += chunkSizes.get(chunk);
                    }
                }
            }
            
            maxOil = Math.max(maxOil, oilAmount);
        }
        
        return maxOil;
    }
    
    private int bfs(int[][] land, int startX, int startY, int chunkId, 
                   int[] dx, int[] dy, int n, int m) {
        ArrayDeque<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{startX, startY});
        land[startX][startY] = chunkId;
        int size = 1;
        
        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int x = current[0];
            int y = current[1];
            
            // 상하좌우 탐색
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                
                if (nx >= 0 && nx < n && ny >= 0 && ny < m && land[nx][ny] == 1) {
                    land[nx][ny] = chunkId;
                    queue.offer(new int[]{nx, ny});
                    size++;
                }
            }
        }
        
        return size;
    }
}