import java.io.*;
import java.util.*;

public class Main {
    static String str;
    static ArrayList<int[]> list;
    static int q;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        str = br.readLine();

        list = new ArrayList<>();

        for (int i = 0; i < str.length(); i++) {
            String subStr = str.substring(0, i + 1);
            list.add(new int[26]);
            for (char ch : subStr.toCharArray()) {
                list.get(i)[ch - 'a']++;
            }
        }

        q = Integer.parseInt(br.readLine());
        while (q-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            char target = st.nextToken().charAt(0);
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            if (start == 0) {
                sb.append(list.get(end)[target - 'a']).append('\n');
            } else {
                sb.append(list.get(end)[target - 'a'] - list.get(start - 1)[target - 'a']).append('\n');
            }
        }
        System.out.println(sb);
    }
}
