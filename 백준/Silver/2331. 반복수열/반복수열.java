import java.io.*;
import java.util.*;

public class Main {
    static int a, p;
    static boolean[] visited = new boolean[100000000];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        a = Integer.parseInt(st.nextToken());
        p = Integer.parseInt(st.nextToken());

        List<Integer> list = new ArrayList<>();
        list.add(a);

        while (true) {
            int size = list.get(list.size() - 1);

            int result = 0;
            while (size != 0) {
                result += (int) Math.pow(size % 10, (double) p);
                size /= 10;
            }

            if (list.contains(result)) {
                int ans = list.indexOf(result);
                System.out.println(ans);
                break;
            }

            list.add(result);
        }
    }
}
