import java.util.*;

class Solution {
    public String solution(String s) {
        
        String[] numbers = s.split(" ");
        List<Integer> list = new ArrayList<>();
        
        for(String num : numbers) {
            list.add(Integer.parseInt(num));
        }
        
        int min = Collections.min(list);
        int max = Collections.max(list);
        
        return min + " " + max;
    }
}