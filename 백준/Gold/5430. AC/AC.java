import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {
    static int T, n;
    static String command;
    static ArrayDeque<Integer> deque;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            command = br.readLine();
            n = Integer.parseInt(br.readLine());

            StringTokenizer st = new StringTokenizer(br.readLine(), "[],");

            deque = new ArrayDeque<Integer>();

            for (int i = 0; i < n; i++) {
                deque.add(Integer.parseInt(st.nextToken()));
            }

            AC(command, deque);
        }

        System.out.println(sb);
    }

    public static void AC(String command, ArrayDeque<Integer> deque) {
        boolean isRight = true;

        for (char cmd : command.toCharArray()) {
            if (cmd == 'R') {
                isRight = !isRight;
                continue;
            }

            if (isRight) {
                if (deque.pollFirst() == null) {
                    sb.append("error\n");
                    return;
                }
            } else {
                if (deque.pollLast() == null) {
                    sb.append("error\n");
                    return;
                }
            }
        }
        makePrintString(deque, isRight);
    }

    private static void makePrintString(ArrayDeque<Integer> deque, boolean isRight) {
        sb.append('[');

        if (!deque.isEmpty()) {
            if (isRight) {
                sb.append(deque.pollFirst());

                while (!deque.isEmpty()) {
                    sb.append(',').append(deque.pollFirst());
                }
            } else {
                sb.append(deque.pollLast());

                while (!deque.isEmpty()) {
                    sb.append(',').append(deque.pollLast());
                }
            }
        }
        sb.append(']').append('\n');
    }


}
