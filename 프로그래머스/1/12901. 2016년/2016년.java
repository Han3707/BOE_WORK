import java.time.LocalDate;
import java.time.DayOfWeek;

class Solution {
    public String solution(int a, int b) {
        // 1. 2016년 a월 b일 날짜 객체 생성
        LocalDate date = LocalDate.of(2016, a, b);

        // 2. 요일 구하기 (MONDAY~SUNDAY)
        DayOfWeek day = date.getDayOfWeek();

        // 3. 요일 이름을 배열에서 변환
        String[] days = {"SUN","MON","TUE","WED","THU","FRI","SAT"};

        // 4. getValue()는 월~일 -> 1~7 반환하므로 %7로 조정
        return days[day.getValue() % 7];
    }
}
