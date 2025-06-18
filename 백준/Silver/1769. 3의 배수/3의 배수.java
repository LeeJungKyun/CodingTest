import java.io.*;

public class Main {
    static String input;
    static int count;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        input = br.readLine();

        while (input.length() != 1) {
            count++;
            char[] arr = input.toCharArray();
            int sum = 0;
            for (int i = 0; i < arr.length; i++) {
                int num = arr[i] - '0';
                sum += num;
            }
            input = sum + "";
        }
        sb.append(count).append('\n');
        sb.append(Integer.parseInt(input) % 3 == 0 ? "YES" : "NO").append('\n');
        System.out.print(sb);
    }
}
