class Solution {
    public int[] solution(String[][] places) {
        int[] answer = new int[places.length];
        
        for(int i = 0; i < places.length; i++){
            if(isTrue(places[i])){
                answer[i] = 1;
            } else {
                answer[i] = 0;
            }
        }
        return answer;
    }
    
    private boolean isTrue(String[] place){
        int n = 5;
        char[][] board = new char[n][n];
        for(int i = 0; i < n; i++){
            board[i] = place[i].toCharArray();
        }
        
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                if(board[i][j] == 'P'){
                    
                    // 인접한 칸 (맨해튼 거리 1)
                    int[][] directions = { {-1, 0}, {1, 0}, {0, -1}, {0, 1} };
                    for(int[] d : directions){
                        int ni = i + d[0], nj = j + d[1];
                        if(isInIndex(ni, nj) && board[ni][nj] == 'P'){
                            return false;
                        }
                    }
                    
                    // 동일 행/열에서 거리 2
                    int[][] directions2 = { {-2, 0}, {2, 0}, {0, -2}, {0, 2} };
                    for(int[] d : directions2){
                        int ni = i + d[0], nj = j + d[1];
                        if(isInIndex(ni, nj) && board[ni][nj] == 'P'){
                            int mi = i + d[0] / 2, mj = j + d[1] / 2;
                            if(board[mi][mj] != 'X'){
                                return false;
                            }
                        }
                    }
                    
                    // 대각선 검사
                    int[][] directions3 = { {-1, -1}, {-1, 1}, {1, -1}, {1, 1} };
                    for(int[] d : directions3){
                        int ni = i + d[0], nj = j + d[1];
                        if(isInIndex(ni, nj) && board[ni][nj] == 'P'){
                            // 대각선의 경우 (i, nj)와 (ni, j) 둘 중 하나라도 'X'가 아니면 위반
                            if (!(board[i][nj] == 'X' && board[ni][j] == 'X')){
                                return false;
                            }
                        }
                    }         
                }
            }
        }
        return true;
    }
    
    private boolean isInIndex(int i, int j){
        return i >= 0 && i < 5 && j >= 0 && j < 5;
    }
}
