import java.util.*;
import java.io.*;

public class Main {
    static int N, M, K;
    static int[][] map;
    static int[][] pattern;
    static int[] dx = {-1, 1, 0, 0}; // 상하좌우
    static int[] dy = {0, 0, -1, 1};
    
    // 상태: x, y, 패턴 사용 횟수, 거점 방문 여부
    static class State {
        int x, y, patterns, visited;
        
        State(int x, int y, int patterns, int visited) {
            this.x = x;
            this.y = y;
            this.patterns = patterns;
            this.visited = visited;
        }
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        
        map = new int[N][M];
        pattern = new int[5][5];
        
        // 지도 입력
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        // 패턴 입력
        for (int i = 0; i < 5; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 5; j++) {
                pattern[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        System.out.println(bfs());
    }
    
    static int bfs() {
        // 방문 체크: [x][y][패턴사용횟수][거점방문여부]
        boolean[][][][] visited = new boolean[N][M][K + 1][2];
        Queue<State> queue = new LinkedList<>();
        
        // 시작점이 거점인지 확인
        int startVisited = (map[0][0] == 2) ? 1 : 0;
        queue.offer(new State(0, 0, 0, startVisited));
        visited[0][0][0][startVisited] = true;
        
        int distance = 0;
        
        while (!queue.isEmpty()) {
            int size = queue.size();
            
            for (int s = 0; s < size; s++) {
                State curr = queue.poll();
                
                // 목적지 도달 && 거점 방문했는지 확인
                if (curr.x == N - 1 && curr.y == M - 1 && curr.visited == 1) {
                    return distance;
                }
                
                // 1. 기본 이동 (상하좌우)
                for (int d = 0; d < 4; d++) {
                    int nx = curr.x + dx[d];
                    int ny = curr.y + dy[d];
                    
                    if (isValid(nx, ny)) {
                        int newVisited = curr.visited;
                        if (map[nx][ny] == 2) newVisited = 1;
                        
                        if (!visited[nx][ny][curr.patterns][newVisited]) {
                            visited[nx][ny][curr.patterns][newVisited] = true;
                            queue.offer(new State(nx, ny, curr.patterns, newVisited));
                        }
                    }
                }
                
                // 2. 패턴 이동 (패턴 사용 가능한 경우)
                if (curr.patterns < K) {
                    for (int i = 0; i < 5; i++) {
                        for (int j = 0; j < 5; j++) {
                            if (pattern[i][j] == 1 && !(i == 2 && j == 2)) {
                                // 패턴에서 (2,2)는 현재 위치이므로 제외
                                int nx = curr.x + (i - 2);
                                int ny = curr.y + (j - 2);
                                
                                if (isValid(nx, ny)) {
                                    int newVisited = curr.visited;
                                    if (map[nx][ny] == 2) newVisited = 1;
                                    
                                    if (!visited[nx][ny][curr.patterns + 1][newVisited]) {
                                        visited[nx][ny][curr.patterns + 1][newVisited] = true;
                                        queue.offer(new State(nx, ny, curr.patterns + 1, newVisited));
                                    }
                                }
                            }
                        }
                    }
                }
            }
            distance++;
        }
        
        return -1;
    }
    
    static boolean isValid(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < M && map[x][y] != 1;
    }
}