import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static StringBuilder sb;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        sb = new StringBuilder();

        for (int i = 0; i < n; i++) {
            String str = br.readLine();
            sb.append(palindrome(0, str.length() - 1, str, 0)).append('\n');
        }

        System.out.println(sb);
    }

    public static int palindrome(int start, int end, String str, int count) {
        if(count >= 2)
            return 2;

        while(start < end){
            if(str.charAt(start) == str.charAt(end)){
                start++;
                end--;
            } else{
                return Math.min(palindrome(start + 1, end, str, count + 1), palindrome(start, end - 1, str, count + 1));
            }
        }
        return count;
    }
}
