import java.io.*;
import java.util.*;
public class Main {
    static int n, k, p, x;
    //0부터 9까지 비트로 표현
    static String[] digit = {"1111110", "0000110", "1011011", "1001111", "0100111", "1101101", "1111101", "1000110", "1111111", "1101111"};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        p = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());

        int[] digitNum = new int[10];
        for (int i = 0; i < 10; i++) {
            digitNum[i] = Integer.parseInt(digit[i], 2);
        }
        int count = 0;
        String base = String.format("%0" + k + "d", x);

        //n층이하로 X 층에서 최대 P개 반전해서 만들 수 있는 경우의 수
        for (int i = 1; i <= n; i++) {
            if(i == x) continue;

            String curFloor = String.format("%0" + k + "d", i);
            int diff = 0;

            for (int j = 0; j < k; j++) {
                int from = digitNum[base.charAt(j) - '0'];
                int to = digitNum[curFloor.charAt(j) - '0'];
                diff += Integer.bitCount(from ^ to);
            }

            if(diff <= p)
                count++;
        }
        System.out.println(count);
    }
}
