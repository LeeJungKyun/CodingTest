import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int m = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());

        boolean[] arr = new boolean[1000000];
        arr[0] = arr[1] = false;
        for (int i = 2; i <= n; i++)
            arr[i] = true;

        for (int i = 2; i * i <= n; i++) {
            for (int j = i * i; j <= n; j += i)
                arr[j] = false;
        }
        for (int i = m; i <= n; i++)
            if (arr[i])
                System.out.println(i);
    }
}