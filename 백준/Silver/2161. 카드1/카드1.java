import java.io.*;
import java.util.*;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        Deque<Integer> deque = new LinkedList<>();
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 1; i <= n; i++) {
            deque.addLast(i);
        }
        while (deque.size() > 1) {
            queue.add(deque.removeFirst());
            deque.addLast(deque.removeFirst());
        }
        StringBuilder sb = new StringBuilder();
        for (int i : queue) {
            sb.append(i + " ");
        }
        sb.append(deque.remove());
        System.out.print(sb);
    }
}