import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    static int N;
    static int[] arr;
    static int result;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int t = 0; t < 10; t++) {
            //건물 개수
            N = Integer.parseInt(br.readLine());
            arr = new int[N + 4];
            result = 0;

            //건물 높이 입력
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }

            //로직
            for (int i = 2; i < N - 2; i++) {
                    int sum = 0;
                    //건물 높이 비교
                    if (arr[i] - arr[i - 1] > 0 && arr[i] - arr[i - 2] > 0 && arr[i] - arr[i + 1] > 0 && arr[i] - arr[i + 2] > 0 ) {
                        sum = Math.min((arr[i] - arr[i - 1]), (arr[i] - arr[i - 2]));
                        sum = Math.min(sum, arr[i] - arr[i + 1]);
                        sum = Math.min(sum, arr[i] - arr[i + 2]);
                    }
                    result += sum;
            }
            System.out.println("#" + (t + 1) + " " + result);
        }
    }
}
