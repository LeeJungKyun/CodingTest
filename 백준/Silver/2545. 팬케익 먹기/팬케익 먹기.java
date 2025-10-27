import java.io.*;
import java.util.*;

/**
 * @author 이정균
 *
 */

public class Main {
	// 클래스 선언부

	// 상수 선언부
	static final long MAX = Long.MAX_VALUE;
	static final long MIN = Long.MIN_VALUE;

	// 변수 선언부
	static int testCase;
	static long a, b, c, d;
	static List<Long> list;

	// 입출력 선언부
	static BufferedReader br;
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));

		String line = br.readLine();
		if (line == null || line.isEmpty())
			return;

		// 테스트 케이스 수 처리
		try {
			testCase = Integer.parseInt(line);
		} catch (NumberFormatException e) {
			return; // 숫자가 아니면 종료
		}

		for (int tc = 1; tc <= testCase; tc++) {
			// 한 줄 제거 (문제 형식상 존재하는 공백 라인)
			br.readLine();

			// a, b, c, d 입력
			st = new StringTokenizer(br.readLine());

			a = Long.parseLong(st.nextToken());
			b = Long.parseLong(st.nextToken());
			c = Long.parseLong(st.nextToken());
			d = Long.parseLong(st.nextToken());

			list = new ArrayList<>();
			list.add(a);
			list.add(b);
			list.add(c);

			long sum = a + b + c;
			long finalSum = sum - d;

			Collections.sort(list);

			long first = list.get(0);
			long second = list.get(1);
			long third = list.get(2);

			long min = finalSum / 3;
			long finalA = Math.min(min, first);

			long remainSum = finalSum - finalA;

			long mid = remainSum / 2;
			long finalB = Math.min(mid, second);
			long finalC = remainSum - finalB;

			finalA = Math.max(0, finalA);
			finalB = Math.max(0, finalB);
			finalC = Math.max(0, finalC);

			System.out.println(finalA * finalB * finalC);
		}
	}
}