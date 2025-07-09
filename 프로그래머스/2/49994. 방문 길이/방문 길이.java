import java.util.*;

class Solution {
    public int solution(String dirs) {
        // 게임 맵의 경계: -5 ~ 5
        final int MIN = -5;
        final int MAX = 5;

        // 현재 캐릭터의 좌표
        int x = 0;
        int y = 0;

        // 경로 저장할 set 중복 방지
        Set<String> paths = new HashSet<>();

        for (int i = 0; i < dirs.length(); i++) {
            int curX = x; // 이동 전 X 좌표
            int curY = y; // 이동 전 Y 좌표

            char command = dirs.charAt(i); // 현재 명령어

            // 이동
            int newX = x;
            int newY = y;

            if (command == 'U') {
                newY += 1;
            } else if (command == 'D') {
                newY -= 1;
            } else if (command == 'L') {
                newX -= 1;
            } else if (command == 'R') {
                newX += 1;
            }

            // 맵 경계를 벗어나는지 확인
            if (newX < MIN || newX > MAX || newY < MIN || newY > MAX) {
                // 경계를 벗어나면 이동하지 않고 다음 명령으로 넘어감
                continue;
            }

            // 맵 내에서 유효한 이동인 경우에만 좌표 업데이트
            x = newX;
            y = newY;

            String path1 = curX + "," + curY + "-" + newX + "," + newY;
            String path2 = newX + "," + newY + "-" + curX + "," + curY; // 역방향 경로

            
            if (!paths.contains(path1) && !paths.contains(path2)) {
                paths.add(path1); // 한 방향만 추가해도 됨, contains로 양방향 체크
                // 그리고 양방향을 추가하게 되면 경로를 카운트 할 때 x2 가 되므로 한 방향만 추가
                
            }
        }

        int answer = paths.size();
        return answer;
    }
}