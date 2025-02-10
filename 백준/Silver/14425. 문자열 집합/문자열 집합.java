import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static int n, m;
    static ArrayList<String> input;
    static int sum = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        input = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            input.add(br.readLine());
        }

        for (int i = 0; i < m; i++) {
            String str = br.readLine();
            if (input.contains(str)) {
                sum++;
            }
        }

        System.out.println(sum);
    }
}
