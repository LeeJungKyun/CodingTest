/**
 * @author 이정균
 * K개의 랜선, N개의 같은 랜선
 * 만들 수 있는 최대 랜선의 길이
 * 
 * 이분탐색
 * 
 * 1. k, n입력
 * 2. 랜선 배열 입력
 * 3. 랜선의 길이를 정하기 위해 left = 0, right = 1_000_000 선언
 * 4. left <= right 까지 이분탐색하면서 n개 이상 만들 수 있는 랜선의 최대 길이 찾기
 */
import java.io.*;
import java.util.*;

public class Main {
	static int k, n;
	static int[] length;
	static long maxLength = 0;
	static BufferedReader br;
	static StringTokenizer st;
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		//1. k, n 입력
		st = new StringTokenizer(br.readLine());
		k = Integer.parseInt(st.nextToken());
		n = Integer.parseInt(st.nextToken());
		
		//2. 랜선 배열 입력
		length = new int[k];
		int longestInputLength = Integer.MIN_VALUE;
		for(int i = 0; i < k; i++) {
			length[i] = Integer.parseInt(br.readLine());
			longestInputLength = Math.max(longestInputLength, length[i]);
		}
			
		
		//3. 랜선의 길이를 정하기 위해 left = 1, right = 1_000_000 선언
		long left = 1;
		long right = longestInputLength;
		
		//4. left <= right 까지 이분탐색하면서 랜선의 최대 길이 찾기
		while(left <= right){
			long mid = (left + right) / 2;
			long count = 0;
			for(int i = 0; i < k ;i++) {
				count += length[i] / mid;
			}
			
			//최소 조건을 만족 했으면
			if(count >= n) {
				// 조건을 만족한 랜선 길이 중 최댓값 갱신
				maxLength = Math.max(maxLength, mid);
				//더 긴 범위 탐색
				left = mid + 1;
			}
			else right = mid - 1;
		}
		
		System.out.println(maxLength);
	}
}
