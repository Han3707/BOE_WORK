class Solution {
    public String solution(String new_id) {
        String answer = "";
        
        // 1. 소문자 치환
        new_id = new_id.toLowerCase();
        
        //2. 소문자,숫자,-,_,. 를 제외한 문자 제거
        new_id = new_id.replaceAll("[^a-z0-9-_.]","");
        
        //3. .이 두 번이상 연속된 부분 . 하나로
        new_id = new_id.replaceAll("\\.+",".");
        
        //4.  .이 처음이나 끝에 위치하면 제거
        new_id = new_id.replaceAll("^\\.|\\.$","");
        
        //5. 빈 문자열이면 a 대입
        if(new_id.isEmpty()) new_id = "a";
        
        //6. 길이가 16자 이상이면 처음부터 15개까지만 출력 그리고 마침표가 끝에 위치하면 마침표 제거
        if(new_id.length() > 15){
            new_id = new_id.substring(0,15);
            new_id = new_id.replaceAll("\\.$","");
        }
        //7. new_id 의 길이가 2자 이하면 new_id 의 마지막 문자를 3일 될때까지 반복
        while(new_id.length() < 3){
            new_id += new_id.charAt(new_id.length()-1);
        }
        
        answer = new_id;
        
        return answer;
    }
}