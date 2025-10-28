import java.io.*;
import java.util.*;

public class Main {
	//클래스 선언부

	//상수 선언부
	static final int MAX = Integer.MAX_VALUE;
	static final int MIN = Integer.MIN_VALUE;

	//변수 선언부
	static int testCase;
	static int n, m;
	static int[][] arr;
	//입출력 선언부
	static BufferedReader br;
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		
		testCase = Integer.parseInt(br.readLine());
		for(int tc = 1; tc <= testCase; tc++) {
			st = new StringTokenizer(br.readLine());
			//1. n, m 입력
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			
			//2. 배열 입력
			arr = new int[n][m];
			int count = 0;
			for(int i = 0; i < n; i++) {
				String line = br.readLine();
				for(int j = 0; j < m; j++) {
					arr[i][j] = line.charAt(j) == '.' ? 0 : 1;
					if(arr[i][j] == 0) count++;
				}
			}
			System.out.println(count % 2 == 0 ? "pizza" : "sewon");
		}
	}
}