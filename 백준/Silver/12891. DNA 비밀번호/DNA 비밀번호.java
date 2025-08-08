/**
 * @author 이정균
 * 첫째줄에 문자열 길이 S와 부분문자열 길이 P가주어짐
 * 두번째줄에 DNA 문자열이 주어짐
 * 부분 문자열에 포함되어야 할 4 알파벳들의 최소 개수가 공백으로 주어짐
 * 만들 수 있는 비밀번호의 종류의 수
 * 
 * 투포인터로 만들 수 있는 문자열을 만들면서 유효성 검사?
 * 
 * 1. S와 P입력
 * 2. 문자열 입력
 * 3. 필요 알파벳 개수 배열 입력 (0: A, 1: C, 2: G, 3: T)
 * 4. 시작 인덱스 left = 0, right = p - 1
 * 5. currentAlphabetCount 배열 계산
 * 6. 초기 문자열에 대한 유효성검사
 * 7. 슬라이딩 윈도우 시작
 * 	7-1. left++, right++
 *  7-2. 오른쪽 끝 문자 추가
 * 	7-3. 왼쪽 끝 문자 제거
 * 	7-4. 유효성 검사

 * 	
 */
import java.io.*;
import java.util.*;

public class Main {
	static int s, p;
	static String originString;
	static int[] requiredAlphabetCount = new int[4], currentAlphabetCount = new int[4];
	static int left, right;
	static int answer = 0;
	static boolean isValid = false;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		// 1. S와 P 입력
		s = Integer.parseInt(st.nextToken());
		p = Integer.parseInt(st.nextToken());

		// 2. 문자열 입력
		originString = br.readLine();

		// 3. 필요 알파벳 개수 배열 입력
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < 4; i++)
			requiredAlphabetCount[i] = Integer.parseInt(st.nextToken());

		// 4. 시작 인덱스 left = 0, right = p - 1
		left = 0;
		right = p - 1;

		// 5. currentAlphabetCount 배열 계산
		for (int i = left; i <= right; i++) {
			char currentAlphabet = originString.charAt(i);

			switch (currentAlphabet) {
			case 'A':
				currentAlphabetCount[0]++;
				break;
			case 'C':
				currentAlphabetCount[1]++;
				break;
			case 'G':
				currentAlphabetCount[2]++;
				break;
			case 'T':
				currentAlphabetCount[3]++;
				break;
			}
		}
		
		//6. 초기 문자열에 대한 유효성검사
		isValid = true;
		for (int alphabetIndex = 0; alphabetIndex < 4; alphabetIndex++) {
			if (currentAlphabetCount[alphabetIndex] < requiredAlphabetCount[alphabetIndex]) {
				isValid = false;
				break;
			}
		}
		if (isValid)
			answer++;

		// 7. 슬라이딩 윈도우 시작
		for (int i = 0; i < s - p; i++) {
			// 7-1. left++, right++
			left++;
			right++;

			// 7-2. 오른쪽 끝 문자 추가
			char rightAlphabet = originString.charAt(right);
			switch (rightAlphabet) {
				case 'A':
					currentAlphabetCount[0]++;
					break;
				case 'C':
					currentAlphabetCount[1]++;
					break;
				case 'G':
					currentAlphabetCount[2]++;
					break;
				case 'T':
					currentAlphabetCount[3]++;
					break;
			}
			// 7-3. 왼쪽 끝 문자 제거
			char leftAlphabet = originString.charAt(left - 1);
			switch (leftAlphabet) {
				case 'A':
					currentAlphabetCount[0]--;
					break;
				case 'C':
					currentAlphabetCount[1]--;
					break;
				case 'G':
					currentAlphabetCount[2]--;
					break;
				case 'T':
					currentAlphabetCount[3]--;
					break;
			}
			
			// 7-4. 유효성 검사
			isValid = true;
			for (int alphabetIndex = 0; alphabetIndex < 4; alphabetIndex++) {
				if (currentAlphabetCount[alphabetIndex] < requiredAlphabetCount[alphabetIndex]) {
					isValid = false;
					break;
				}
			}
			if (isValid)
				answer++;
		}
		System.out.println(answer);
	}
}
