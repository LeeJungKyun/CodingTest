import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n, k;
    static int[] arr;
    static int cnt = 0;
    static int index;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        arr = new int[n + 1];

        for (int i = 2; i <= n; i++) {
            arr[i] = i;
        }

        for (int i = 2; i <= n; i++) {

            if (arr[i] == 0) {
                continue;
            }

            for (int j = i; j <= n; j += i) {
                if(arr[j] != 0){
                    arr[j] = 0;
                    cnt++;
                }

                if (cnt == k) {
                    System.out.println(j);
                    return;
                }
            }
        }
    }
}
