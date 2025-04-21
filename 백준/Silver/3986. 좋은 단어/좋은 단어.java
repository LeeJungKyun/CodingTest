import java.io.*;
import java.util.*;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int n = Integer.parseInt(br.readLine());
    int count = 0;

    while (n-- > 0) {
      String str = br.readLine();
      Stack<Character> stack = new Stack<>();

      for (int i = 0; i < str.length(); i++) {
        char ch = str.charAt(i);
        if (!stack.isEmpty() && stack.peek() == ch) {
          stack.pop();
        } else {
          stack.push(ch);
        }
      }
      if (stack.isEmpty()) {
        count++;
      }
    }

    System.out.println(count);
  }
}