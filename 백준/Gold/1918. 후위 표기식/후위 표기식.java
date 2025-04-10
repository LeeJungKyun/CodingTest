import java.util.*;
import java.io.*;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String str = br.readLine();
    char[] arr = str.toCharArray();
    Stack<Character> stack = new Stack<>();
    StringBuilder sb = new StringBuilder();
    for (char ch : arr) {
      switch(ch) {
        //기본연산자에 대해서는 다 같은 연산
        case '+':
        case '-':
        case '*':
        case '/':
          //스택이 안 비고 스택에 있는 연산자의 우선순위가 현재 연산자의 우선순위보다 높으면
          while (!stack.isEmpty() && priority(ch) <= priority(stack.peek())) {
            sb.append(stack.pop());
          }
          stack.add(ch);
          break;

        case '(':
          stack.add(ch);
          break;

        case ')':
          while (!stack.isEmpty() && stack.peek() != '(') {
            sb.append(stack.pop());
          }
          //여는 괄호 없애주기
          stack.pop();
          break;

        default:
          sb.append(ch);
      }
    }
    //남은거 다 이어주기
    while (!stack.isEmpty()) {
      sb.append(stack.pop());
    }
    System.out.println(sb);
  }

  public static int priority(char operator) {
    if (operator == '+' || operator == '-') {
      return 1;
    } else if (operator == '*' || operator == '/') {
      return 2;
    }
    return -1;
  }
}
