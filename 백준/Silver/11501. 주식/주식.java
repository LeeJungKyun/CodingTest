import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());

        while (t-- > 0) {
            int n = Integer.parseInt(br.readLine());
            int[] prices = new int[n];

            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                prices[i] = Integer.parseInt(st.nextToken());
            }

            long maxPrice = 0;
            long profit = 0;

            // 뒤에서부터 주가 확인
            for (int i = n - 1; i >= 0; i--) {
                if (prices[i] > maxPrice) {
                    maxPrice = prices[i];
                } else {
                    profit += (maxPrice - prices[i]);
                }
            }

            System.out.println(profit);
        }
    }
}