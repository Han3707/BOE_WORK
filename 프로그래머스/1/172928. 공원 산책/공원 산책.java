class Solution {
    public int[] solution(String[] park, String[] routes) {
        int[] answer = {};
        
        int n = park.length;
        int m = park[0].length();
        
        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};
        String dirs= "NSWE";
        
        int r = 0;
        int c = 0;
        int idx = 0;
        
        
        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                idx = park[i].indexOf('S');
                if(idx >= 0){
                    r = i;
                    c = idx;
                    break;
                }
            }
        }
        
        
        for(String s:routes){
            char dir = s.charAt(0);
            int cnt = Integer.parseInt(s.substring(2));
            
            int k = dirs.indexOf(dir);
            int nr = r, nc = c;
            boolean ok = true;
            
            for(int st = 0 ; st < cnt; st++){
                nr += dr[k];
                nc += dc[k];
                if(nr < 0 || nr >= n || nc < 0 || nc >= m || park[nr].charAt(nc) == 'X'){
                    ok = false;
                    break;
                }
            }
            
            if(ok) {r = nr; c = nc;}
            
                                                   
                                                   
        }
        
        return new int[]{r,c};
    }
}