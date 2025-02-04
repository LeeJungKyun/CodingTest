import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    static int m;
    static long sum = 0;
    static int xor = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        m = Integer.parseInt(br.readLine());

        while (m-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int command = Integer.parseInt(st.nextToken());

            switch(command)
            {
                case 1:
                    int x = Integer.parseInt(st.nextToken());
                    sum += x;
                    xor ^= x;
                    break;

                case 2:
                    int y = Integer.parseInt(st.nextToken());
                    sum -= y;
                    xor ^= y;
                    break;

                case 3:
                    System.out.println(sum);
                    break;

                case 4:
                    System.out.println(xor);
                    break;
            }
        }
    }
}
