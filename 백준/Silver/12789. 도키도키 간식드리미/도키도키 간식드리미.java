import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int[] arr;
    static Stack<Integer> stack = new Stack<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String result = "Nice";
        n = Integer.parseInt(br.readLine());
        arr = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        int index = 1;

        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < n; i++) {
            int cur = arr[i];
            if (index != cur) {
                if (!stack.isEmpty() && index == stack.peek()) {
                    index++;
                    stack.pop();
                    i--;
                } else {
                    stack.push(cur);
                }
            } else {
                index++;
            }
        }

        while (!stack.isEmpty()) {
            int pop = stack.pop();
            if (pop == index) {
                index++;
            } else {
                result = "Sad";
                break;
            }
        }
        System.out.println(result);
    }
}