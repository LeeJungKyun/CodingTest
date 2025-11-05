import java.io.*;
import java.util.*;

public class Main {
	static int n, t;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        n = Integer.parseInt(st.nextToken());
        t = Integer.parseInt(st.nextToken());
        
        // 1, 2, 3, 4, 5, 6, 5, 4, 3, 2, 1
        int result = 0;
        int num = 1;
        for(int i = 0; i < t; i++) {
        	result += num;
        	if(result == 2 * n)
        		num = -1;
        	if(result == 1) num = 1;
        }
        System.out.println(result);
    }
}
