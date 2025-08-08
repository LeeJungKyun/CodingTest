/**
 * @author 이정균
 * 총 10개의 테스트케이스 -> static final int TEST_CASE
 * 8개의 숫자
 * 첫번째 숫자는 1을 감소하고 뒤로 보내고, 두번째 숫자는 2를 감소하고 뒤로, ...
 * 이 작업이 한 사이클
 * 0보다 작아질때 프로그램이 종료되며 그 값이 암호
 * 
 * ArrayDeque를 사용한 구현
 * 0이 되기전까지 0~9까지 pollFirst(), addLast()
 * 출력
 * 
 * 1. 테스트케이스 번호 입력
 * 2. ArrayDeque선언 후 들어오는 입력을 받기
 * 3. isEncrypted라는 boolean 변수를 선언해서 true가 될때까지 while 반복, , minusIndex 선언
 * 	3-1. deque에서 poll
 * 	3-2. poll한 값에서 minusIndex 빼기
 * 	3-3. 유효성 검사 -> 0 이하면 0으로 바꾸고 addLast하고 isEncrypted true변경 후 break
 * 	3-4. minusIndex++ 후에 5를 넘으면 다시 1로 초기화 -> 사이클 반복 유지
 * 5. deque 출력
 */
import java.io.*;
import java.util.*;

public class Solution {
	static final int TEST_CASE = 10;
	static ArrayDeque<Integer> deque;
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		for (int tc = 1; tc <= TEST_CASE; tc++) {
			// 1. 테스트케이스 번호 입력
			br.readLine();
			st = new StringTokenizer(br.readLine());

			// 2. ArrayDeque 선언 후 들어오는 입력을 받기
			deque = new ArrayDeque<>();
			for (int i = 0; i < 8; i++)
				deque.add(Integer.parseInt(st.nextToken()));

			// 3. isEncrypted라는 boolean 변수를 선언해서 true가 될때까지 while 반복, minusIndex 선언
			boolean isEncrypted = false;
			int minusIndex = 1;
			while (!isEncrypted) {
				// 4-1. deque에서 poll
				int curNum = deque.pollFirst();
				//3-2. poll한 값에서 minusIndex 빼기
				curNum -= minusIndex;
				// 3-3. 유효성 검사 -> 0 이하면 0으로 바꾸고 addLast하고 isEncrypted true변경 후 break
				if (curNum <= 0) {
					curNum = 0;
					deque.addLast(curNum);
					isEncrypted = true;
					break;
				} else
					deque.addLast(curNum);
				//3-4. minusIndex++후에 5를 넘으면 다시 1로 초기화 -> 사이클 반복 유지
				minusIndex++;
				if(minusIndex > 5)
					minusIndex = 1;

			}
			sb.append("#").append(tc).append(" ");
			for (int num : deque)
				sb.append(num).append(" ");
			sb.append('\n');
		}
		System.out.println(sb);
	}
}
