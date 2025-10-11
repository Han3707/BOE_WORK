import java.util.*;

class Solution {
    public int solution(int k, int m, int[] score) {
        
        
        // k = 사과의 최고 점수 , m = 한 상자에 들어가는 사과 갯수
        // 1. score 의 수 / m = 몇박스 가능한지 구하기
        // 2. score 정렬해서 가능한 박스 수 x m 개 / k 보다 작은 수로 값 더하기
        // 3. 박스당  최저 사과 숫자 x m 개 (사과개수) x 박스 숫자  더하기
        
        
        
        
        int answer = 0;
        Arrays.sort(score);
       
        for(int i=score.length - m; i>= 0; i -= m){
            answer += score[i] * m;
        }
        
        
        return answer;
    }
}