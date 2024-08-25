import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static String[] arr = {"D", "C", "B", "A", "E"};
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < 3; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int cnt = 0;
            while (st.hasMoreTokens()) {
                cnt += Integer.parseInt(st.nextToken());
            }
            sb.append(arr[cnt] + "\n");
        }
        System.out.println(sb);
    }
}
