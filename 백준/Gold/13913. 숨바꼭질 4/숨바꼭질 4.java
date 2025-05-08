import java.io.*;
import java.util.*;

public class Main {
    static int n, k;
    static int[] check;
    static int[] path;
    static int result;
    static Queue<Integer> queue;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        check = new int[100001];
        path = new int[100001];

        Arrays.fill(check, -1);
        check[n] = 0;

        queue = new LinkedList<>();
        queue.add(n);

        while(!queue.isEmpty()){
            int cur = queue.poll();

            if (cur == k) {
                result = check[cur];
                break;
            }

            int minus = cur - 1;
            int plus = cur + 1;
            int teleport = cur * 2;

            if (isValid(minus) && check[minus] == -1) {
                check[minus] = check[cur] + 1;
                path[minus] = cur;
                queue.add(minus);
            }
            if (isValid(plus) && check[plus] == -1) {
                check[plus] = check[cur] + 1;
                path[plus] = cur;
                queue.add(plus);
            }
            if (isValid(teleport) && check[teleport] == -1) {
                check[teleport] = check[cur] + 1;
                path[teleport] = cur;
                queue.add(teleport);
            }
        }

        Stack<Integer> stack = new Stack<>();
        for (int at = k; at != n; at = path[at]) {
            stack.push(at);
        }
        stack.push(n);

        while (!stack.isEmpty()) {
            sb.append(stack.pop()).append(" ");
        }

        System.out.println(result);
        System.out.println(sb);
    }

    public static boolean isValid(int num) {
        return 0 <= num && num <= 100000;
    }
}
