class Solution {
    
    private int answer = 0;
    
    public int solution(int[] numbers, int target) {
        
        dfs(numbers, target, 0, 0);
        
        return answer;
    }
    
    private void dfs(int[] numbers, int target, int index, int curSum){
        
        if(index == numbers.length){
            //현재까지의 합이 타겟 넘버와 같으면
            if(curSum == target){
                answer++;
            }
            return;
        }
        // + 인경우
        dfs(numbers,target,index +1, curSum + numbers[index]);
        // - 인경우
        dfs(numbers,target,index +1, curSum - numbers[index]);
    }
}