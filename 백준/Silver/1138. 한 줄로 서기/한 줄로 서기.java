import java.io.*;
import java.util.*;
public class Main {
    static int n;
    static int[] arr;
    static int[] res;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        arr = new int[n + 1];
        res = new int[n + 1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 1; i <= n; i++) {
            int j = 1;

            while (true) {
                if(arr[i] == 0 && res[j] == 0){
                    res[j] = i;
                    break;
                } else if(res[j] == 0){
                    arr[i]--;
                }
                j++;
            }
        }

        for (int i = 1; i <= n; i++) {
            System.out.print(res[i] + " ");
        }
    }
}
