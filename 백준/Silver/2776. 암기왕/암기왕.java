import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {
    static int T;
    static int N, M;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            StringBuilder sb = new StringBuilder();
            N = Integer.parseInt(br.readLine());
            HashSet<Integer> aHashSet = new HashSet<>();
            StringTokenizer st = new StringTokenizer(br.readLine());

            for (int i = 0; i < N; i++) {
                aHashSet.add(Integer.parseInt(st.nextToken()));
            }

            M = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < M; i++) {
                Integer num = Integer.parseInt(st.nextToken());
                if (aHashSet.contains(num)) {
                    sb.append("1").append('\n');
                } else sb.append("0").append('\n');
            }
            System.out.print(sb);
        }
    }
}
