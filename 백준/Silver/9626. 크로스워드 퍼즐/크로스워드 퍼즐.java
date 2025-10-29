import java.io.*;
import java.util.*;

public class Main {
	// 클래스 선언부

	// 상수 선언부
	static final int MAX = Integer.MAX_VALUE;
	static final int MIN = Integer.MIN_VALUE;

	// 변수 선언부
	static int m, n;
	static int u, l, r, d;
	// 입출력 선언부
	static BufferedReader br;
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));

		// 1. m, n 입력
		st = new StringTokenizer(br.readLine());
		m = Integer.parseInt(st.nextToken());
		n = Integer.parseInt(st.nextToken());

		// 2. u, l, r, d 입력
		st = new StringTokenizer(br.readLine());
		u = Integer.parseInt(st.nextToken());
		l = Integer.parseInt(st.nextToken());
		r = Integer.parseInt(st.nextToken());
		d = Integer.parseInt(st.nextToken());

		// 3. 글자 입력
		char[][] words = new char[m][n];
		for (int i = 0; i < m; i++)
			words[i] = br.readLine().toCharArray();

		for (int i = 0; i < u + m + d; i++) {
			for (int j = 0; j < l + n + r; j++) {
				if (u <= i && i < u + m && l <= j && j < l + n) {
					sb.append(words[i - u][j - l]);
				} else {
					if((i + j) % 2 == 0) sb.append('#');
					else sb.append('.');
				}
			}
			sb.append('\n');
		}
		
		System.out.println(sb);
	}
}