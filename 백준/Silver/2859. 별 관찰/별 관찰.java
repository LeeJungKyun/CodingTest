import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[][] star;
    static String[] day = {"Saturday", "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday"};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        star = new int[2][2];
        for (int i = 0; i < 2; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), ":");
            int hour = Integer.parseInt(st.nextToken());
            int minute = Integer.parseInt(st.nextToken());
            star[i][0] = hour * 60 + minute;
        }

        for (int i = 0; i < 2; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), ":");
            int hour = Integer.parseInt(st.nextToken());
            int minute = Integer.parseInt(st.nextToken());
            star[i][1] = hour * 60 + minute;
        }

        for (int i = 0; i < 1440; i++) {
            int cur = star[0][0] + star[0][1] * i;
            if (cur - star[1][0] < 0 || ((cur - star[1][0]) % star[1][1]) != 0) {
                continue;
            }

            int min = cur % 60;
            cur /= 60;

            int hour = cur % 24;
            cur /= 24;

            System.out.println(day[cur % 7]);
            System.out.println(time(hour) + ":" + time(min));
            return;
        }
        System.out.println("Never");
    }

    public static String time(int num) {
        if (num < 10) {
            return "0" + num;
        } else return String.valueOf(num);
    }
}
