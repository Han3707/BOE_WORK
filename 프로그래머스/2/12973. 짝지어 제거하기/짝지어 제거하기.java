import java.util.*;

class Solution
{
    public int solution(String s)
    {
        ArrayDeque<Character> dq = new ArrayDeque<>();
        
        for(int i=0; i<s.length(); i++){
            char c= s.charAt(i);
            if(!dq.isEmpty() && dq.peekLast() == c){
                dq.pollLast();
            }else{
                dq.addLast(c);
            }
        }
                
        return dq.isEmpty() ? 1:0 ;
    }
}