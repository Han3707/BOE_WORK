import java.util.*;

class Solution {
    public int[][] solution(int[][] data, String ext, int val_ext, String sort_by) {
        
        
        int idx_ext = getidx(ext);
        int idx_sort = getidx(sort_by);
        
        
        
        List<int[]> list = new ArrayList<>();
        
        for(int[] d:data){
            
            if(d[idx_ext] < val_ext){
                list.add(d);
            }
        }
        
        list.sort((a,b) -> a[idx_sort] - b[idx_sort]);
        
        int[][] answer = new int[list.size()][];
        
        for(int i=0; i<list.size(); i++){
            answer[i] = list.get(i); 
        }
        
        
        return answer;
    }
    
    
    private int getidx(String name){
        switch(name){
            case "code": return 0;
            case "date": return 1;
            case "maximum": return 2;
            case "remain": return 3;
        }
        return -1;
    }
}