import java.util.*;

class Solution {
    public int[] solution(int[] numbers) {
        
        TreeSet<Integer> treeset = new TreeSet<>();
        
        for(int i=0; i<numbers.length; i++){
            int n = 0;
            for(int j=i+1; j<numbers.length; j++){
                n = numbers[i] + numbers[j];
                treeset.add(n);
            }
        }
        
        int[] answer = new int[treeset.size()];
        int idx = 0;
        for(int t:treeset){
            answer[idx++] = t;
        }
        
        return answer;
    }
}