class Solution {
    public int[] solution(String[] wallpaper) {
        int[] answer = new int[4];
        
        int n = wallpaper.length;
        int m = wallpaper[0].length();
        
        int min_x = n;
        int min_y = m;
        int max_x = -1;
        int max_y = -1;
        
        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                if(wallpaper[i].charAt(j) =='#'){
                    min_x = Math.min(min_x,i);
                    min_y = Math.min(min_y,j);
                    max_x = Math.max(max_x,i);
                    max_y = Math.max(max_y,j);
                }
            }
        }
        
        answer[0] = min_x;
        answer[1] = min_y;
        answer[2] = max_x + 1;
        answer[3] = max_y + 1;
        
        return new int[]{min_x,min_y,max_x+1,max_y+1};
    }
}