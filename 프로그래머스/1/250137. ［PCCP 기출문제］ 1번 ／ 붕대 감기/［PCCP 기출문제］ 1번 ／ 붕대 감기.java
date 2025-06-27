import java.util.*;

class Solution {
    public int solution(int[] bandage, int health, int[][] attacks) {
        // 붕대 감기 기술 정보
        int skillCastingTime = bandage[0]; // 시전 시간 (t)
        int healPerSecond = bandage[1];    // 1초당 회복량 (x)
        int bonusHeal = bandage[2];      // 추가 회복량 (y)

        int currentHealth = health; // 현재 체력
        int maxHealth = health;     // 최대 체력

        int continuousSuccess = 0; // 붕대 감기 연속 성공 시간

        // 몬스터 공격 정보를 더 쉽게 접근하기 위해 Map으로 변환
        // 공격 시간을 키로, 피해량을 값으로 저장합니다.
        // 이는 특정 시간에 공격이 있는지 빠르게 확인하기 위함입니다.
        HashMap<Integer, Integer> attackMap = new HashMap<>();
        int lastAttackTime = 0; // 마지막 공격 시간
        for (int[] attack : attacks) {
            attackMap.put(attack[0], attack[1]);
            if (attack[0] > lastAttackTime) {
                lastAttackTime = attack[0];
            }
        }

        // 0초부터 마지막 공격 시간까지 1초씩 시뮬레이션
        for (int time = 1; time <= lastAttackTime; time++) {
            // 몬스터 공격이 있는 경우
            if (attackMap.containsKey(time)) {
                int damage = attackMap.get(time);
                currentHealth -= damage; // 피해량만큼 체력 감소
                continuousSuccess = 0;   // 연속 성공 시간 초기화

                // 체력이 0 이하가 되면 사망
                if (currentHealth <= 0) {
                    return -1;
                }
            }
            // 몬스터 공격이 없는 경우 (체력 회복 시도)
            else {
                continuousSuccess++; // 연속 성공 시간 증가
                currentHealth += healPerSecond; // 초당 회복량만큼 체력 회복

                // 시전 시간을 채웠다면 추가 회복
                if (continuousSuccess == skillCastingTime) {
                    currentHealth += bonusHeal; // 추가 회복량만큼 체력 회복
                    continuousSuccess = 0;       // 연속 성공 시간 초기화
                }

                // 체력이 최대 체력을 초과하지 않도록 조정
                if (currentHealth > maxHealth) {
                    currentHealth = maxHealth;
                }
            }
        }

        return currentHealth; // 모든 공격 후 남은 체력 반환
    }
}