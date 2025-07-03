import java.util.*;

class Solution {
    
    private int[] topLevel; // board 에 각 라인에 인형이 위치한 최고 높이
    private int N;
    public int solution(int[][] board, int[] moves) {
        N = board.length;
        topLevel = new int[N];
        int answer = 0;
        
        //인형이 존재하는 높이 계산하는 로직
        Arrays.fill(topLevel,-1);
        initTop(board);
        
        //뽑은 인형 담을 바구니 deque
        Deque<Integer> basket = new ArrayDeque<>();
        
        for(int move:moves){
            // 0 부터 시작이니깐 -1 해줌
            int col = move -1;
            
            //뽑기 실행
            answer += UpdateMove(board,col,basket);
        }
        
        return answer;
    }
    
    
    // 인형이 위치한 각 라인의 최고 높이 찾기
    private void initTop(int[][] board){
        
        for(int j=0; j<N; j++){
            for(int i=0; i<N; i++){
                if(board[i][j] != 0){
                    topLevel[j] = i;
                    break;
                }
            }
        }
    }
    
    //인형 뽑기 하고 계산 하는 함수
    private int UpdateMove(int[][] board,int col,Deque<Integer> basket){
        
        // 해당 라인이 비어있는 것이므로 0 리턴
        if(topLevel[col] == -1){
            return 0;
        }
        
        int curRow = topLevel[col];
        int doll = board[curRow][col];
        board[curRow][col] = 0;
        
        topLevel[col]++; 

        //toplevel은 높이이므로 높이가 n을 넘거나 다음 위치가 0인 경우는 인형이 없다는 것이므로 -1로 설정
        if (topLevel[col] >= N || board[topLevel[col]][col] == 0) {
            topLevel[col] = -1; 
        }
        
        // 바구니가 비지 않고 전의 값과 현재 뽑은 인형의 값이 같으면 터트림 +2 
        if (!basket.isEmpty() && basket.peekLast() == doll) {
            basket.removeLast(); 
            return 2; 
        } else {
            basket.addLast(doll); 
            return 0; 
        }
        
        
    }
}