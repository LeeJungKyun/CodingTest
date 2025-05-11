import java.util.*;
import java.io.*;

public class Main {
    static int n;
    static ArrayList<Integer> list;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        n = Integer.parseInt(br.readLine());
        list = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            list.add(Integer.parseInt(br.readLine()));
        }

        Collections.sort(list, Collections.reverseOrder());

        for (int num : list) {
            sb.append(num).append('\n');
        }

        System.out.println(sb);
    }
}
