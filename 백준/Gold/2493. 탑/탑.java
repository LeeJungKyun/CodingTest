import java.io.*;
import java.util.*;

public class Main {

  static class Tower {
    int height;
    int index;

    public Tower(int height, int index) {
      this.height = height;
      this.index = index;
    }
  }

  static int n;
  static Stack<Tower> stack;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();

    n = Integer.parseInt(br.readLine());
    StringTokenizer st = new StringTokenizer(br.readLine());
    stack = new Stack<>();

    for (int i = 0; i < n; i++) {
      int curHeight = Integer.parseInt(st.nextToken());

      while (!stack.isEmpty() && stack.peek().height <= curHeight) {
        stack.pop();
      }

      if (stack.isEmpty()) {
        sb.append("0 ");
      } else {
        sb.append(stack.peek().index).append(" ");
      }

      stack.push(new Tower(curHeight, i + 1));
    }

    System.out.println(sb);
  }
}
