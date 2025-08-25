class Solution {
    public int[] solution(String s) {
        int transCnt = 0;
        int zeroCnt = 0;
        
        
        
        while(!s.equals("1")){
            transCnt++;
            int cnt = 0;
            StringBuilder sb = new StringBuilder(); // 매 반복마다 생성해야함
            for(char c:s.toCharArray()){
                if(c == '0'){
                    cnt++;
                }else{
                    sb.append(c);
                }
            }
            zeroCnt += cnt;
            
            int len = sb.length();
            s = Integer.toBinaryString(len);
        }
        return new int[]{transCnt,zeroCnt};
    }
}