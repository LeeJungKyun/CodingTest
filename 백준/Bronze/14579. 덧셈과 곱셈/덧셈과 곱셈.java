import java.io.*;
import java.util.*;

public class Main {
	public static final int MOD = 14579;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        
        int temp = (a + 1) * a / 2;
        temp %= MOD;
        
        int result = temp;
        
        for(int i = a + 1; i <= b; i++) {
        	result *= (temp += i);
        	result %= MOD;
        }
        System.out.println(result);
    }
}