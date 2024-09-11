import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {

    static int n;
    static int left_index;
    static int right_index;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();

        n = Integer.parseInt(br.readLine());

        Deque<Character> left = new LinkedList<>();
        Deque<Character> right = new LinkedList<>();


        for (int i = 0; i < str.length(); i++) {
            left.add(str.charAt(i));
        }

        left_index = str.length();
        right_index = str.length();

        while (n-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String c = st.nextToken();

            switch (c) {
                case "L":
                    if (!left.isEmpty()) {
                        right.addFirst(left.removeLast());
                    }
                    break;

                case "D":
                    if (!right.isEmpty()) {
                        left.addLast(right.removeFirst());

                    }
                    break;

                case "B":
                    if (!left.isEmpty())
                        left.removeLast();
                    break;

                case "P":
                    String char_item = st.nextToken();
                    Character ch = char_item.charAt(0);
                    left.addLast(ch);
                    break;
            }
        }

        StringBuilder sb = new StringBuilder();

        for (Character c : left) {
            sb.append(c);
        }

        for (Character c : right) {
            sb.append(c);
        }

        System.out.println(sb);
    }
}
