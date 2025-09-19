class Solution {
    public String solution(String new_id) {
        String answer = "";
        
        // 대문자 -> 소문자
        String s = new_id.toLowerCase();
        
        // 2. 알파벳 소문자, 숫자 , - , _ , . 제외한 문자 제거
        s = s.replaceAll("[^a-z0-9._-]","");
        
               
        // 3  . 이 연속되면 . 하나로 변환
        s = s.replaceAll("\\.+",".");
        
        // 4.   "." 이 처음이나 마지막에 위치한다면 제거
        s = s.replaceAll("^\\.|\\.$","");
        
        // 5. 빈문자열이면 a
        if(s.isEmpty()) s = "a";
        
        //6. 문자길이가 15자 이상이면 자르고 끝에 마침표면 제거
        if(s.length() > 15) s = s.substring(0,15);
        s = s.replaceAll("\\.$","");
        
        //7. 길이가 2 이하라면 마지막 문자를 길이가 3이 될때까지 추가
        while(s.length() <= 2){
            s += s.charAt(s.length() - 1);
        }
        return s;
    }
}