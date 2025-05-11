import java.io.*;
import java.util.*;

public class Main {
    static int n, m;
    static int[] problems;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        problems = new int[n];
        for (int i = 0; i < n; i++) {
            problems[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(problems);

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < m; i++) {
            int user = Integer.parseInt(st.nextToken());
            sb.append(solve(user)).append(' ');
        }
        System.out.println(sb);
    }

    public static int solve(int user) {
        int count = find(user);

        int left = 1;
        int right = 100000;
        int result = 0;

        while (left <= right) {
            int mid = (left + right) / 2;
            if(3L * mid * (mid - 1) + 1 <= count){
                result = mid;
                left = mid + 1;
            } else right = mid - 1;
        }
        return result;
    }

    public static int find(int user) {
        int left = 0;
        int right = problems.length;

        while(left < right){
            int mid = (left + right) / 2;
            if(problems[mid] <= user)
                left = mid + 1;
            else right = mid;
        }
        return left;
    }
}
