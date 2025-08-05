import java.io.*;
import java.util.*;

public class Main {
    static class Pair {
        int height;
        int count;

        Pair(int height, int count) {
            this.height = height;
            this.count = count;
        }
    }
    static Stack<Pair> stack = new Stack<>();
    static int n;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        stack = new Stack<>();
        long result = 0;

        for (int i = 0; i < n; i++) {
            int height = Integer.parseInt(br.readLine());
            int count = 1;

            // 현재 키보다 작거나 같은 키를 가진 사람들과는 무조건 마주볼 수 있다.
            while (!stack.isEmpty() && stack.peek().height <= height) {
                Pair top = stack.pop();
                result += top.count;

                // 같은 키일 경우: 그룹 수를 누적해서 현재 사람과 함께 처리
                if (top.height == height) {
                    count += top.count;
                }
            }

            if (!stack.isEmpty()) {
                result += 1;
            }

            stack.push(new Pair(height, count));
        }

        System.out.println(result);
    }
}
