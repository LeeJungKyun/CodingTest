import java.io.*;
import java.util.*;

/**
 * @author 이정균
 *
 */

public class Main {
	static int n, m;
	static BufferedReader br;
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        int count = 0;
        
        if (n == 1) {
            count = 1;
        } else if (n == 2) {
            count = Math.min(4, ((m + 1) / 2));
        } else if (m < 7) {
            count = Math.min(4, m);
        } else if(m >= 7){
            count = m - 2;
        }

        System.out.println(count);

	}
}