import java.util.*;

class Solution {
    public int solution(int[] players, int m, int k) {
        int total_server = 0;
        int[] servers = new int[24]; // 각 시간대에 가동 중인 서버 수를 저장하는 배열

        for (int i = 0; i < 24; i++) {
            
            int required_servers = players[i] / m;

            if (required_servers > servers[i]) {
                int addserver = required_servers - servers[i];
                total_server += addserver;

                for (int j = i; j < i + k; j++) {
                    if (j < 24) { // 24시간을 넘지 않도록
                        servers[j] += addserver;
                    }
                }
            }
        }
        return total_server;
    }
}