import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int[] arr;
    static int[] operator;
    static int maxValue = Integer.MIN_VALUE;
    static int minValue = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        arr = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        operator = new int[4];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 4; i++) {
            //순서대로 + - * /
            operator[i] = Integer.parseInt(st.nextToken());
        }

        dfs(arr[0], 1);

        System.out.println(maxValue);
        System.out.println(minValue);
    }

    public static void dfs(int num, int index) {
        if (index == n) {
            maxValue = Math.max(maxValue, num);
            minValue = Math.min(minValue, num);
            return;
        }

        for (int i = 0; i < 4; i++) {
            if (operator[i] > 0) {
                operator[i] --;

                switch(i) {
                    //더하기
                    case 0:
                        dfs(num + arr[index], index + 1);
                        break;
                    //빼기
                    case 1:
                        dfs(num - arr[index], index + 1);
                        break;
                    //곱하기
                    case 2:
                        dfs(num * arr[index], index + 1);
                        break;
                    //나누기
                    case 3:
                        dfs(num / arr[index], index + 1);
                        break;
                }
                operator[i]++;
            }
        }
    }
}
