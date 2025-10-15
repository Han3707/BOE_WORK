class Solution {
    public String solution(String phone_number) {
        // 전체 길이
        int len = phone_number.length();

        // 앞부분 (가려질 부분)
        String star = phone_number.substring(0, len - 4);
        // 뒷부분 (보여질 4자리)
        String num = phone_number.substring(len - 4);

        // 앞부분을 *로 모두 치환
        star = star.replaceAll(".", "*");

        // 두 부분 합치기
        return star + num;
    }
}
