import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;


/**
 * () = 2
 * [] = 3
 * (X) = 2 * X
 * [X] = 3 * X
 * (XY) = X + Y
 */
public class Main {
    static int result = 0;
    static int value = 1;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String input = br.readLine();

        Stack<Character> stack = new Stack<>();


        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == '(') {
                stack.push(input.charAt(i));
                value *= 2;
            } else if (input.charAt(i) == '[') {
                stack.push(input.charAt(i));
                value *= 3;
            } else if (input.charAt(i) == ')') {
                if (stack.isEmpty() || stack.peek() != '(') {
                    result = 0;
                    break;
                } else if (input.charAt(i - 1) == '(') {
                    result += value;
                }
                stack.pop();
                value /= 2;
            } else if (input.charAt(i) == ']') {
                if (stack.isEmpty() || stack.peek() != '[') {
                    result = 0;
                    break;
                } else if (input.charAt(i - 1) == '[') {
                    result += value;
                }
                stack.pop();
                value /= 3;
            }
        }
        if (!stack.isEmpty()) {
            System.out.println(0);
        } else {
            System.out.println(result);
        }
    }
}
