import java.io.*;
import java.util.*;

public class Main {
    static class Book {
        int day, page;

        public Book(int day, int page) {
            this.day = day;
            this.page = page;
        }
    }
    static int n, m;
    static ArrayList<Book> list;
    static int[] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        list = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int day = Integer.parseInt(st.nextToken());
            int page = Integer.parseInt(st.nextToken());
            list.add(new Book(day, page));
        }

        dp = new int[n + 1];

        for (Book book : list) {
            for (int j = n; j >= book.day; j--) {
                dp[j] = Math.max(dp[j], dp[j - book.day] + book.page);
            }
        }
        System.out.println(dp[n]);
    }
}
