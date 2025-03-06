import java.util.*;

class Solution {
    public int[] solution(String today, String[] terms, String[] privacies) {
        
        int todayDays = convertDateToDays(today);
        
        Map<String, Integer> termMap = new HashMap<>();
        for(String term : terms ){
            String[] parts = term.split(" ");
            termMap.put(parts[0], Integer.parseInt(parts[1]));
        }
        
        List<Integer> expiredList = new ArrayList<>();
        
        for(int i=0; i<privacies.length; i++){
            String[] parts = privacies[i].split(" ");
            int collectionDays = convertDateToDays(parts[0]);
            String termType = parts[1];
            int termMonths = termMap.get(termType);
            
            int expiryDays = collectionDays + termMonths * 28 - 1;
            
            if(todayDays > expiryDays){
                expiredList.add(i+1);
            }
        }
        
        int[] answer = new int[expiredList.size()];
        for(int i=0; i < expiredList.size(); i++){
            answer[i] = expiredList.get(i);
        }
        return answer;
    }
    
    private int convertDateToDays(String date){
        String[] parts = date.split("\\.");
        int year = Integer.parseInt(parts[0]);
        int month = Integer.parseInt(parts[1]);
        int day = Integer.parseInt(parts[2]);
        
        return year * 12 * 28 + month * 28 + day;
    }
}