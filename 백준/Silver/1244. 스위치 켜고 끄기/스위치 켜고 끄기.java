import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int studentNum;
    static int[] light;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        n = Integer.parseInt(br.readLine());
        light = new int[n + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 1; i <= n; i++) {
            light[i] = Integer.parseInt(st.nextToken());
        }

        studentNum = Integer.parseInt(br.readLine());
        for (int i = 0; i < studentNum; i++) {
            st = new StringTokenizer(br.readLine());
            int sex = Integer.parseInt(st.nextToken());
            int num = Integer.parseInt(st.nextToken());

            changeSwitch(sex, num);
        }

        for (int i = 1; i <= n; i++) {
            sb.append(light[i]).append(' ');
            if (i % 20 == 0 || i == n) {
                sb.append('\n');
            }
        }
        System.out.println(sb);
    }

    private static void changeSwitch(int sex, int num) {
        switch (sex) {
            //남자
            case 1:
                for (int i = num; i <= n; i += num) {
                    light[i] = (light[i] == 0) ? 1 : 0;
                }
                break;

            //여자
            case 2:
                int left = num - 1;
                int right = num + 1;

                while (left >= 1 && right <= n && light[left] == light[right]) {
                    left--;
                    right++;
                }
                left++;
                right--;

                for (int i = left; i <= right; i++) {
                    light[i] = (light[i] == 0) ? 1 : 0;
                }
                break;
        }
    }
}
