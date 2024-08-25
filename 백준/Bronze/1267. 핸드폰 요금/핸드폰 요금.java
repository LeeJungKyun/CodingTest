import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    //영식(Y) 30초마다 10원씩
    //민식(M) 60초마다 15원씩
    static int n;
    static int[] arr;
    static int Y_sum = 0;
    static int M_sum = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        arr = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            Y_sum += (arr[i]/30 + 1) * 10;
            M_sum += (arr[i]/60 + 1) * 15;
        }
        if (Y_sum == M_sum) {
            System.out.println("Y M " + Y_sum);
        }
        else if (Y_sum < M_sum) {
            System.out.println("Y " + Y_sum);
        }
        else System.out.println("M " + M_sum);
    }
}
