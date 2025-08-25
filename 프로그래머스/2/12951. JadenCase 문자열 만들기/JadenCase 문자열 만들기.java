class Solution {
    public String solution(String s) {
        
        char[] arr = s.toCharArray();
        int check = 1;
        for(int i=0; i<s.length(); i++){
            if(arr[i] == ' '){
                check = 1;
            }else if(check == 1){
                if(arr[i] >= 'a' && arr[i] <= 'z'){
                    arr[i] = (char)(arr[i]-32);
                }
                check = 0;;
            }else{
                if(arr[i] >= 'A' && arr[i] <= 'Z'){
                    arr[i] = (char)(arr[i]+32);
                }
            }
        }
        
        return new String(arr);
    }
}