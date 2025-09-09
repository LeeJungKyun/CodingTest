import java.io.*;
import java.util.*;

/**
 * @author 이정균
 * 전봇대
 * 1 2 3 4
 * 2 3 4 1
 *
 * 최장증가수열의 길이를 구해서 전체 길이에서 빼면 되나?
 * 음 근데 N이 10만이라 이분탐색으로 LIS길이를 구합시다
 */
public class Main {
	static int elementCount;
	static int[] elementArr;
	static BufferedReader br;
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		
		elementCount = Integer.parseInt(br.readLine());
		
		elementArr = new int[elementCount];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0 ; i < elementCount; i++) {
			elementArr[i] = Integer.parseInt(st.nextToken());
		}
		
		//이분탐색으로 LIS를 찾읍시다.
		//정보를 담을 ArrayList 선언
		ArrayList<Integer> lisArr = new ArrayList<>();
		
		for(int i = 0; i < elementCount; i++) {
			int currentNum = elementArr[i];
			
			//이분탐색으로 어디에 들어갈지 인덱스 뽑아내기
			int insertionIndex = Collections.binarySearch(lisArr, currentNum);
			
			//인덱스가 0보다 작으면
			if(insertionIndex < 0)
				insertionIndex = -(insertionIndex + 1);
			
			//같으면 LIS가 그냥 유지될 수 있습니다
			if(insertionIndex == lisArr.size()) {
				lisArr.add(currentNum);
			}
			//다르면 해당 인덱스에 넣어요
			else {
				lisArr.set(insertionIndex, currentNum);
			}
		}
		
		
//		시간초과 이슈로 날리는 코드
//		int[] dp = new int[elementCount];
//		Arrays.fill(dp, 1);
//		
//		for(int i = 1; i < elementCount; i++) {
//			for(int j = 0; j < i; j++) {
//				if(elementArr[i] > elementArr[j]) {
//					dp[i] = Math.max(dp[i], dp[j] + 1);
//				}
//			}
//		}
//		
//		int maxLength = 0;
//		for(int num : dp)
//			maxLength = Math.max(maxLength, num);
		
		System.out.println(elementCount - lisArr.size());
	}
}