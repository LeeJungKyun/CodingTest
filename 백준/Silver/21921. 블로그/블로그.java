import java.util.*;
import java.io.*;

public class Main {
  static int n, x;
  static int[] arr;
  static int maxVisit = -1;
  static int maxVisitCount;
  static int sum;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    n = Integer.parseInt(st.nextToken());
    x = Integer.parseInt(st.nextToken());

    arr = new int[n];

    st = new StringTokenizer(br.readLine());
    for (int i = 0; i < n; i++) {
      arr[i] = Integer.parseInt(st.nextToken());
    }

    //처음 X일 동안의 합
    for (int i = 0; i < x; i++) {
      sum += arr[i];
    }
    
    maxVisit = sum;
    maxVisitCount = 1;

    //구간 합 구하기
    for (int i = x; i < n; i++) {
      sum = sum - arr[i - x] + arr[i];

      if (sum > maxVisit) {
        maxVisit = sum;
        maxVisitCount = 1;
      } else if (sum == maxVisit) {
        maxVisitCount++;
      }
    }

    if (maxVisit == 0) {
      System.out.println("SAD");
    } else {
      System.out.println(maxVisit);
      System.out.println(maxVisitCount);
    }

  }
}
