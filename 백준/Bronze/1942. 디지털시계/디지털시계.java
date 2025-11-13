import java.io.*;
import java.util.*;

public class Main {
	static final int TEST_CASE = 3;
	static BufferedReader br;
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	public static int toInt(int time) {
        int sec = time % 60;
        time /= 60;
        int min = time % 60;
        time /= 60;
        int hour = time % 24;

        return hour * 10000 + min * 100 + sec;
    }

    public static int toSec(String time) {
        int h = Integer.parseInt(time.substring(0, 2));
        int m = Integer.parseInt(time.substring(3, 5));
        int sec = Integer.parseInt(time.substring(6, 8));
        return (h * 3600 + m * 60 + sec);
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 0; i < TEST_CASE; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String startTimeStr = st.nextToken();
            String endTimeStr = st.nextToken();

            int first = toSec(startTimeStr);
            int last = toSec(endTimeStr);
            int count = 0;

            while (true) {
                if (toInt(first) % 3 == 0) {
                    count++;
                }

                if (first == last) {
                    break;
                }

                first++;
                first %= (3600 * 24); 
            }
            System.out.println(count);
        }
    }
}