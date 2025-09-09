import java.io.*;
import java.util.*;

/**
 * @author 이정균
 * DP를 사용한 LIS 알고리즘
 *
 *
 * 1. 배열의 크기 입력
 * 2. 배열 입력
 * 3. LIS 길이 찾기 시작
 * 		3-1. DP테이블 선언
 * 		3-2. 1로 채우기
 * 		3-3. 2중 포문으로 채우는데 i는 내가 보고 있는 인덱스, j는 내 앞의 인덱스들을 탐색
 * 		3-4. j인덱스의 값이 내 값보다 작다? 그러면 걔가 갖고 있는 DP값에 내꺼 더해주고 max갱신
 */

public class Solution {
	//클래스 선언부

	//상수 선언부
	static final int MAX = Integer.MAX_VALUE;
	static final int MIN = Integer.MIN_VALUE;

	//변수 선언부
	static int testCase;
	static int elementCount;
	static int[] elementArr, dp;

	//입출력 선언부
	static BufferedReader br;
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));

		testCase = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= testCase; tc++) {
			//1. 배열의 크기 입력
			elementCount = Integer.parseInt(br.readLine());
			
			//2. 배열 입력
			elementArr = new int[elementCount];
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < elementCount; i++) {
				elementArr[i] = Integer.parseInt(st.nextToken());
			}
			
			//3. LIS 길이 찾기 시작
			//3-1. DP테이블 선언
			dp = new int[elementCount];
			Arrays.fill(dp, 1);
			//3-3. 2중 포문으로 채우는데 i는 내가 보고 있는 인덱스, j는 내 앞의 인덱스들을 탐색
			for(int selectedIndex = 1; selectedIndex < elementCount; selectedIndex++) {
				for(int searchIndex = 0; searchIndex < selectedIndex; searchIndex++) {
					////3-4. j인덱스의 값이 내 값보다 작다? 그러면 걔가 갖고 있는 DP값에 내꺼 더해주고 max갱신
					if(elementArr[searchIndex] < elementArr[selectedIndex]) {
						dp[selectedIndex] = Math.max(dp[selectedIndex], dp[searchIndex] + 1);
					}
				}
			}
			
			int maxLength = 0;
			
			for(int num : dp)
				maxLength = Math.max(maxLength, num);
			
			sb.append('#').append(tc).append(' ').append(maxLength).append('\n');
		}
		System.out.println(sb);
	}
}