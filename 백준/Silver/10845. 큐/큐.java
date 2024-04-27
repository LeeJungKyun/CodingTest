import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static int[] arr = new int[10000];
    static int front = 0, rear = 0;
    public static void push(int x) {
            arr[rear] = x;
            rear++;

    }

    public static int pop() {
        if (front == rear) {
            return -1;
        } else {
            return arr[front++];
        }
    }

    public static int size() {
        return rear - front;
    }

    public static int empty() {
        if (front == rear)
            return 1;
        else return 0;
    }

    public static int front() {
        if (empty() == 1)
            return -1;
        else return arr[front];
    }

    public static int back() {
        if(empty() == 1)
            return -1;
        else return arr[rear-1];
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
            } else if(s.contains("front")) {
                sb.append(front()).append('\n');
            } else if(s.contains("back")){
                sb.append(back()).append('\n');
            }
        }
        System.out.println(sb);
    }
}