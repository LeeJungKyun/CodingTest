import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static int[] arr = new int[10000];
    static int idx = 0;
    public static void push(int x) {
            arr[idx] = x;
            idx++;

    }

    public static int pop() {
        if (idx == 0) {
            return -1;
        } else {
            idx--;
            return arr[idx];
        }
    }

    public static int size() {
        return idx;
    }

    public static int empty() {
        if (idx == 0)
            return 1;
        else return 0;
    }

    public static int top() {
        if (idx > 0)
            return arr[idx - 1];
        else{
            return -1;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(br.readLine());

        while (n-- > 0) {
            String s = br.readLine();
            if (s.contains("push")) {
                String intStr = s.replaceAll("[^0-9]", "");
                push(Integer.parseInt(intStr));
            } else if (s.contains("pop")) {
                sb.append(pop()).append('\n');
            } else if (s.contains("size")) {
                sb.append(size()).append('\n');
            } else if (s.contains("empty")) {
                sb.append(empty()).append('\n');
            } else if(s.contains("top")) {
                sb.append(top()).append('\n');
            }
        }
        System.out.println(sb);
    }
}