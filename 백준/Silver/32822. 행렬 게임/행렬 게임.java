import java.io.*;
import java.util.*;

public class Main {
	static int n, m;
	static int[][] hoon, chan;
	static int[] round;
	static BufferedReader br;
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());

		// 1. n, m 입력
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		// 2. 훈이 행렬 입력
		hoon = new int[n][n];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				hoon[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		// 3. 찬이 행렬 입력
		chan = new int[n][n];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				chan[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		//4. 라운드 번호 입력
		round = new int[m];
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < m; i++)
			round[i] = Integer.parseInt(st.nextToken());
		
		long[] maxDiff = new long[n];
		
		for(int i = 0; i < n; i++) {
			long maxVal = 0;
			for(int j = 0; j < n; j++) {
				long diff = Math.abs((long)hoon[j][i] - chan[j][i]);
				if(diff > maxVal)
					maxVal = diff;
			}
			maxDiff[i] = maxVal;
		}
		
		long totalDiff = 0;
		for(int num : round) {
			totalDiff += maxDiff[num - 1];
		}
		System.out.println(totalDiff);
	}
}