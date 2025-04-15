import java.io.*;
import java.util.*;

public class Main {
  static int n, k;
  static int[] arr;
  static int[] prefixSum;
  static int result;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    n = Integer.parseInt(st.nextToken());
    k = Integer.parseInt(st.nextToken());

    arr = new int[n];
    prefixSum = new int[n];

    st = new StringTokenizer(br.readLine());
    for (int i = 0; i < n; i++) {
      arr[i] = Integer.parseInt(st.nextToken());
    }

    int sum = 0;
    //첫 k 일간의 합 구하기
    for (int i = 0; i < k; i++) {
      sum += arr[i];
    }

    result = sum;
    prefixSum[k - 1] = sum;

    for (int i = k; i < n; i++) {
      prefixSum[i] = prefixSum[i - 1] - arr[i - k] + arr[i];
      if (result < prefixSum[i]) {
        result = prefixSum[i];
      }
    }

    System.out.println(result);

  }
}
