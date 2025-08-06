import java.util.*;
import java.io.*;

class Solution {
    
    class WordStep{
        String word;
        int step;
        
        WordStep(String word,int step){
            this.word = word;
            this.step = step;
        }
    }
    
    private boolean candiffone(String w1,String w2){
        int cnt = 0;
        for (int i=0; i<w1.length(); i++){
            if(w1.charAt(i) != w2.charAt(i)){
                cnt++;
                if(cnt > 1) return false;
            }
        }
        
        return cnt == 1;
    }
    
    public int solution(String begin, String target, String[] words) {
        //target 이 words 에 들어있지 않으면 0 반환
        
        boolean targetTF = false;
        
        for(String w:words){
            if(w.equals(target)){
                targetTF = true;
                break;
            }
        }
        
        if(!targetTF) return 0;
        
        //BFS 를 이용한 큐와 방문 체크 배열
        Deque<WordStep> dq = new ArrayDeque<>();
        boolean[] visited = new boolean[words.length];
        
        dq.offer(new WordStep(begin,0));
        
        while(!dq.isEmpty()){
            WordStep cur = dq.poll();
            String curword = cur.word;
            int curstep = cur.step;
            
            if(curword.equals(target)){
                return curstep;
            }
            
            for(int i=0; i<words.length; i++){
                
                //방문 했던 단어는 건너뛰기
                if(visited[i]) continue;
                
                if(candiffone(curword,words[i])){
                    visited[i] = true;
                    dq.offer(new WordStep(words[i],curstep+1));
                }
            }
        }
        
        return 0;
    }
    
    
}