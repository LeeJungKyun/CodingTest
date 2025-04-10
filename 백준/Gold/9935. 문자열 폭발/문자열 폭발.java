import java.util.*;
import java.io.*;

public class Main {
  static String str;
  static String target;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    str = br.readLine();
    target = br.readLine();
    int target_len = target.length();
    StringBuilder sb = new StringBuilder();
    Stack<Character> stack = new Stack<>();

    for (int i = 0; i < str.length(); i++) {
      int count = 0;
      stack.push(str.charAt(i));

      if(stack.size() >= target_len){
        for (int j = 0; j < target_len; j++) {
          if (stack.get(stack.size() - target_len + j) == target.charAt(j)) {
            count++;
          }

          if (count == target_len) {
            for (int k = 0; k < target_len; k++) {
              stack.pop();
            }
          }
        }
      }
    }

    for (char ch : stack) {
      sb.append(ch);
    }

    System.out.println(stack.isEmpty() ? "FRULA" : sb);
  }
}
