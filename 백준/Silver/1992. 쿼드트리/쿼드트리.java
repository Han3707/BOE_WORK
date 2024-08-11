import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
  static int n;
  static int[][] arr;
  static StringBuilder sb = new StringBuilder();

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    n = Integer.parseInt(br.readLine().trim()); // 전역 변수 n에 값을 할당

    arr = new int[n][n];

    // 배열 입력 받기
    for (int i = 0; i < n; i++) {
      String line = br.readLine().trim(); // 줄 단위로 읽기
      for (int j = 0; j < n; j++) {
        arr[i][j] = line.charAt(j) - '0'; // 문자에서 정수로 변환하여 배열에 저장
      }
    }

    // 쿼드트리 압축 수행
    quadtree(0, 0, n);

    // 결과 출력
    System.out.println(sb);
  }

  public static void quadtree(int x, int y, int size) {
    if (check(x, y, size)) {
      // 모든 값이 같을 때
      if (arr[x][y] == 1) {
        sb.append(1);
      } else {
        sb.append(0);
      }
    } else {
      // 부분으로 나누어 쿼드트리 재귀 호출
      sb.append('('); // 괄호로 묶기
      int newSize = size / 2;
      for (int i = 0; i < 2; i++) {
        for (int j = 0; j < 2; j++) {
          quadtree(x + i * newSize, y + j * newSize, newSize);
        }
      }
      sb.append(')'); // 괄호 닫기
    }
  }

  public static boolean check(int x, int y, int size) {
    int temp = arr[x][y];
    for (int i = x; i < x + size; i++) {
      for (int j = y; j < y + size; j++) {
        if (arr[i][j] != temp) {
          return false;
        }
      }
    }
    return true;
  }
}
