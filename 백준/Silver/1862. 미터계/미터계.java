import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	//입출력 선언부
	static BufferedReader br;
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));

		String distString = br.readLine();
		long dist = Long.parseLong(distString);
		long result = 0;

		for (int i = distString.length() - 1; i >= 0; i--) {
			//현재 자리 숫자
			int digit = distString.charAt(i) - '0';
			
			if (digit > 4) {
				result += (digit - 1) * (long) Math.pow(9, distString.length() - 1 - i);
			} else {
				result += digit * (long) Math.pow(9, distString.length() - 1 - i);
			}
			
			for (int j = 0; j < i; j++) {
				if (distString.charAt(j) == '4') {
					result = Long.parseLong(distString.substring(0, i + 1));
					if (result > 4) result--;
					
					for(int k = 1; k < distString.length() - i; k++) {
						result -= Math.pow(10, k);
					}
					
					result = Long.parseLong(distString) - (dist - result);
					
					System.out.println(result);
					return;
				}
			}
		}

		System.out.println(result);
	}
}