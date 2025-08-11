import java.util.*;
import java.io.*;

class Solution {
    
    //노래의 재생횟수, 고유번호 묶을 클래스
    class Music{
        int play;
        int id;
        
        public Music(int play,int id){
            this.play = play;
            this.id = id;
        }
    }
    
    public int[] solution(String[] genres, int[] plays) {
    
        // 1. 장르별 총 재생 횟수 저장할 Map
        Map<String,Integer> genreAllCnt = new HashMap<>();
        // 2. 장르별 노래 리스트 저장할 Map (Music 객체)
        Map<String,List<Music>> genreList = new HashMap<>();
        
        for(int i=0; i<genres.length; i++){
            String genre = genres[i];
            int play = plays[i];
            
            //장르별 총 재생 횟수 
            // getOrDefalut -> 만약 gerne 가 없다면 0으로 값을 넣고 시작해줌. 있다면 그 장르의 값에 + play
            genreAllCnt.put(genre,genreAllCnt.getOrDefault(genre,0) + play);
            
            //장르별 노래 리스트에 Music 객체 추가
            genreList.putIfAbsent(genre,new ArrayList<>());
            genreList.get(genre).add(new Music(play,i));
        }
        
        // 3. 장르 정렬
        List<String> sortgenre = new ArrayList<>(genreAllCnt.keySet());
        
        sortgenre.sort((g1,g2) -> genreAllCnt.get(g2) - genreAllCnt.get(g1));
        
        List<Integer> res = new ArrayList<>();
        
        // 4. 노래 정렬 및 결과 생성
        for(String genre : sortgenre){
            List<Music> MusicList = genreList.get(genre);
            
            MusicList.sort((m1,m2) -> {
                if(m1.play == m2.play){
                    return m1.id - m2.id; // 재생횟수 같으면 고유번호 낮은 순
                }
                return m2.play - m1.play; // 재생횟수 높은 순 
            });
            
            int cnt = 0;
            for(Music music: MusicList){
                if(cnt >= 2){
                    break;
                }
                res.add(music.id);
                cnt++;
            }
        }
        
        int[] answer = new int[res.size()];
        for(int i=0; i<res.size(); i++){
            answer[i] = res.get(i);
        }
        return answer;
    }
}