import java.io.*;
import java.util.*;

public class Main {
  static int n, m;
  static int[] arr;
  static int sum;
  static int max = 0;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    n = Integer.parseInt(br.readLine());

    arr = new int[n];
    StringTokenizer st = new StringTokenizer(br.readLine());
    for (int i = 0; i < n; i++) {
      arr[i] = Integer.parseInt(st.nextToken());
      sum += arr[i];
      max = Math.max(arr[i], max);
    }
    Arrays.sort(arr);

    m = Integer.parseInt(br.readLine());

    if (sum <= m) {
      System.out.println(max);
      return;
    }

    //이분탐색으로 적정값 찾기
    int left = 0;
    int right = 100_000;
    max = 0;

    while (left <= right) {
      sum = 0;
      int mid = (left + right) / 2;
      int[] temp = new int[n];
      //정수 상한액 반영 후 예산안의 합을 구함
      for (int i = 0; i < n; i++) {
        temp[i] = arr[i];
        //만약에 이분탐색 값보다 크다면
        if (temp[i] > mid) {
          temp[i] = mid;
        }
        sum += temp[i];
      }
      //만약 합이 예산안에 있으면 더 작은값이 있는지 확인
      if (sum <= m) {
        max = mid;
        left = mid + 1;
      }
      //조건을 초과하면 더 작은 값 시도
      else right = mid - 1;
    }
    System.out.println(max);
  }
}
