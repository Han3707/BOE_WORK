import java.io.*;

public class Main {
    static int N;
    static String expression;
    static int max = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        expression = br.readLine();

        // 초기 값: 첫 번째 숫자부터 시작
        dfs(0, expression.charAt(0) - '0');
        System.out.println(max);
    }

    // 재귀 함수로 모든 경우의 수 탐색
    public static void dfs(int index, int currentValue) {
        // 수식 끝까지 왔을 때, 최대값 갱신
        if (index >= N - 1) {
            max = Math.max(max, currentValue);
            return;
        }

        // 괄호 없이 다음 연산을 진행하는 경우
        int nextValue = calculate(currentValue, expression.charAt(index + 1), expression.charAt(index + 2) - '0');
        dfs(index + 2, nextValue);

        // 괄호를 사용하는 경우 (현재 index + 2와 index + 4 범위 내에 있을 때)
        if (index + 4 < N) {
            int bracketValue = calculate(expression.charAt(index + 2) - '0', expression.charAt(index + 3), expression.charAt(index + 4) - '0');
            int newValue = calculate(currentValue, expression.charAt(index + 1), bracketValue);
            dfs(index + 4, newValue);
        }
    }

    // 두 숫자와 연산자를 받아 계산하는 함수
    public static int calculate(int a, char operator, int b) {
        switch (operator) {
            case '+':
                return a + b;
            case '-':
                return a - b;
            case '*':
                return a * b;
        }
        return 0;
    }
}
