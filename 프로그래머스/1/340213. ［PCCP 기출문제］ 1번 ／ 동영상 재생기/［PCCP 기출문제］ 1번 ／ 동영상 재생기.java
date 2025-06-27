class Solution {
    public String solution(String video_len, String pos, String op_start, String op_end, String[] commands) {
        int videoLen = toSeconds(video_len);
        int curpos = toSeconds(pos);
        int opstart = toSeconds(op_start);
        int opend = toSeconds(op_end);
        
        // 오프닝 구간이면 처음 위치도 오프닝 엔드 구간
        if(curpos >= opstart && curpos <= opend){
            curpos = opend;
        }
        
        
        // 여기서 prev 경우 -10 했을때 -가 되면 0이 더 크므로 0으로 되게 max 사용 , next 도 마찬가지로 + 로 넘치면 min 이용해서 더 작은 값으로 잡히게 min 사용
        for(String cmd: commands){
            if(cmd.equals("prev")){
                curpos = Math.max(0,curpos - 10);
            }else if(cmd.equals("next")){
                curpos = Math.min(videoLen,curpos +10);
            }
            
             if(curpos >= opstart && curpos <= opend){
            curpos = opend;
        }
        }
        
    
        return toMMSS(curpos);
    }
    
     int toSeconds(String time){
        String[] parts = time.split(":");
        return Integer.parseInt(parts[0]) * 60 + Integer.parseInt(parts[1]);
    }
    
    String toMMSS(int seconds){
        int mm = seconds / 60;
        int ss = seconds % 60;
        return String.format("%02d:%02d",mm,ss);
    }
}

