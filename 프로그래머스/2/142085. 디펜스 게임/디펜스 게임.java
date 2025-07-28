import java.util.*;

class Solution {
    public int solution(int n, int k, int[] enemy) {
        int answer = 0;
        int left = 0;
        int right = enemy.length;
        
        while(left <= right){
            
            int mid = (left+right) / 2;
            
            if(simul(n,k,enemy,mid)){
                answer = mid;
                left = mid+1;
            }else{
                right = mid-1;
            }
        }
        
        return answer;
    }
    
    private boolean simul(int n,int k,int[] enemy,int r){
        if(r == 0) return true;
        if(r > enemy.length) return false;
        
        Integer[] e = new Integer[r];
        for(int i=0; i<r; i++){
            e[i] = enemy[i];
        }
        
        Arrays.sort(e,Collections.reverseOrder());
        
        long needpeople = 0;
        int idx = Math.min(k,r); 
        
        //k 로 피해지는 만큼 회피하고 남은 적들 내 병사로 처치하기
        for(int i=idx; i<r; i++){
            needpeople += e[i];
        }
        
        return needpeople <= n;
            
    }
}