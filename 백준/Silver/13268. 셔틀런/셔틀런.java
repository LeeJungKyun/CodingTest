import java.io.*;
import java.util.*;

public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int n = Integer.parseInt(br.readLine()); // 지훈이의 체력
		int result = 0;
		
		n %= 100;
		
		if (n == 0 || n == 10 || n == 30 || n == 60) {
			result = 0;
		} else if ((0 < n && n < 10) || (10 < n && n <= 15) || (25 <= n && n < 30) || (30 < n && n <= 35) || (55 <= n && n < 60) || (60 < n && n <= 65) || (95 <= n && n < 100)) {
			result = 1;
		} else if ((15 < n && n < 25) || (35 < n && n <= 40) || (50 <= n && n < 55) || (65 < n && n <= 70) || (90 <= n && n < 95)) {
			result = 2;
		} else if ((40 < n && n < 50) || (70 < n && n <= 75) || (85 <= n && n < 90)) {
			result = 3;
		} else {
			result = 4;
		}
		
		bw.write(Integer.toString(result));
		bw.flush();
		bw.close();
		br.close();
	}
}
