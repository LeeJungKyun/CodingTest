import java.io.*;
import java.util.*;

public class Main {
	//변수 선언부
	static int testCase, a, b;
	//입출력 선언부
	static BufferedReader br;
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));

		testCase = Integer.parseInt(br.readLine());
		while(testCase-- > 0) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			int sum = a+b;
			for(int i = 1; i <= (a + b); i++) {
				if(i != sum) {
					sb.append(i).append('\n');
					break;
				}
			}
		}
		System.out.println(sb);
	}
}