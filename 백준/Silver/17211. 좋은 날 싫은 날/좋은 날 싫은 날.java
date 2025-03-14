import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int status;
    static double[] probability;
    static double[][] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        status = Integer.parseInt(st.nextToken());
        arr = new double[n + 1][2];
        st = new StringTokenizer(br.readLine());
        probability = new double[4];

        for (int i = 0; i < 4; i++) {
            probability[i] = Double.parseDouble(st.nextToken());
        }

        //싫은 날이면
        if (status == 1) {
            arr[0][0] = 0;
            arr[0][1] = 1;
        }
        //좋은 날이면
        else{
            arr[0][0] = 1;
            arr[0][1] = 0;
        }


        for (int i = 1; i <= n; i++) {
            //다음날도 좋을 확률
            arr[i][0] = arr[i - 1][0] * probability[0] + arr[i - 1][1] * probability[2];
            arr[i][1] = arr[i - 1][0] * probability[1] + arr[i - 1][1] * probability[3];
        }
        StringBuilder sb = new StringBuilder();
        sb.append(Math.round(arr[n][0] * 1000)).append('\n').append(Math.round(arr[n][1] * 1000));
        System.out.println(sb);
    }
}
