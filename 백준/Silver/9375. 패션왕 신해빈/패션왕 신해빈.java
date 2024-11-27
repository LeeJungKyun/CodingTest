import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static HashMap<String, Integer> cloth;
    static int TestCase;
    static int num;
    static String type;
    static int result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        TestCase = Integer.parseInt(br.readLine());

        for (int testCase = 0; testCase < TestCase; testCase++) {
            cloth = new HashMap<String, Integer>();
            num = Integer.parseInt(br.readLine());
            result = 1;
            for (int i = 0; i < num; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());

                st.nextToken();
                type = st.nextToken();

                if (cloth.containsKey(type)) {
                    cloth.put(type, cloth.get(type) + 1);
                } else cloth.put(type, 1);
            }

            for (Integer value : cloth.values()) {
                result *= (value + 1);
            }

            System.out.println(result - 1);

        }
    }
}
