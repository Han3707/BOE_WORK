import java.util.*;

class Solution {
    public int[] solution(int[] fees, String[] records) {
        Map<String, Integer> in = new HashMap<>();     // 입차 시간
        Map<String, Integer> total = new HashMap<>();  // 총 주차 시간
        
        // 입출차 기록 처리
        for (String record : records) {
            String[] parts = record.split(" ");
            int time = getMinutes(parts[0]);
            String car = parts[1];
            String type = parts[2];
            
            if (type.equals("IN")) {
                in.put(car, time);
            } else {
                int inTime = in.get(car);
                int parkTime = time - inTime;
                
                if (total.containsKey(car)) {
                    total.put(car, total.get(car) + parkTime);
                } else {
                    total.put(car, parkTime);
                }
                
                in.remove(car);
            }
        }
        
        // 미출차 차량 처리 (23:59에 출차)
        for (String car : in.keySet()) {
            int inTime = in.get(car);
            int parkTime = getMinutes("23:59") - inTime;
            
            if (total.containsKey(car)) {
                total.put(car, total.get(car) + parkTime);
            } else {
                total.put(car, parkTime);
            }
        }
        
        // 차량 번호 정렬
        List<String> cars = new ArrayList<>(total.keySet());
        Collections.sort(cars);
        
        // 요금 계산
        int[] result = new int[cars.size()];
        for (int i = 0; i < cars.size(); i++) {
            String car = cars.get(i);
            int time = total.get(car);
            result[i] = calculateFee(time, fees);
        }
        
        return result;
    }
    
    // 시간을 분으로 변환
    private int getMinutes(String time) {
        String[] parts = time.split(":");
        int hour = Integer.parseInt(parts[0]);
        int minute = Integer.parseInt(parts[1]);
        return hour * 60 + minute;
    }
    
    // 요금 계산
    private int calculateFee(int time, int[] fees) {
        int freeTime = fees[0];     // 무료 시간
        int freeFee = fees[1];      // 기본 요금
        int perTime = fees[2];      // 단위 시간
        int perFee = fees[3];       // 단위 요금
        
        if (time <= freeTime) {
            return freeFee;
        }
        
        int overTime = time - freeTime;
        int overUnits = (overTime + perTime - 1) / perTime;  // 올림 계산
        
        return freeFee + overUnits * perFee;
    }
}