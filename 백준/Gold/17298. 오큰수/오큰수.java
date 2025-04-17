import java.io.*;
import java.util.*;

public class Main {
  static int n;
  static int[] arr;
  static int[] result;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    n = Integer.parseInt(br.readLine());
    StringBuilder sb = new StringBuilder();
    arr = new int[n];
    result = new int[n];
    Stack<Integer> stack = new Stack<>();

    StringTokenizer st = new StringTokenizer(br.readLine());
    for (int i = 0; i < n; i++) {
      arr[i] = Integer.parseInt(st.nextToken());
    }

    for (int i = 0; i < n; i++) {
      while (!stack.isEmpty() && arr[stack.peek()] < arr[i]) {
        int idx = stack.pop();
        result[idx] = arr[i];
      }
      stack.push(i);
    }

    while (!stack.isEmpty()) {
      result[stack.pop()] = -1;
    }


    for (int i = 0; i < n; i++) {
      sb.append(result[i]).append(" ");
    }

    System.out.println(sb);
  }
}
