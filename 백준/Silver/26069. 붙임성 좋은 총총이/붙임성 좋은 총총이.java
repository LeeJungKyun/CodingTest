import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int result = 1;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        HashSet<String> hashSet = new HashSet<>();
        hashSet.add("ChongChong");
        while (n-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            String A = st.nextToken();
            String B = st.nextToken();

            if (hashSet.contains(A) || hashSet.contains(B)) {
                hashSet.add(A);
                hashSet.add(B);
            }
        }
        System.out.println(hashSet.size());
    }
}
