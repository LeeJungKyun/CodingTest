import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        String target = br.readLine();

        int count = 0;
        int i = 0;

        while (i <= str.length() - target.length()) {
            if (str.substring(i, i + target.length()).equals(target)) {
                count++;
                i += target.length();
            } else {
                i++;
            }
        }

        System.out.println(count);
    }
}